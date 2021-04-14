package utilities;

import com.opencsv.exceptions.CsvValidationException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;
import dragon.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.time.ZonedDateTime;

/**
 * Class {@code DragonCollection} defines the collection and methods for managing it.
 */
public class DragonCollection {
    /**
     * The collection of dragons.
     */
    private PriorityQueue<Dragon> collection;
    /**
     * History of commands.
     */
    private LinkedList<String> history;
    /**
     * Date and time when collection was initialized.
     */
    final private LocalDateTime initDate;
    /**
     * Path to a file with data.
     */
    private File path;

    private HashSet<Path> scripts;

    /**
     * Constructs collection and fills it from given file.
     * @param path path to file with collection.
     */
    public DragonCollection(File path) throws CsvValidationException, IOException{
        this.path = path;
        initDate = LocalDateTime.now();
        collection = new PriorityQueue<Dragon>();
        history = new LinkedList<>();
        scripts = new HashSet<Path>();
        parseFrom(path);
    }

    public void addToPathSet(Path way) {
        scripts.add(way);
    }

    public HashSet<Path> getScripts() {
        return scripts;
    }

    public Long getMaxId() {
        try {
            return Collections.max(collection, Comparator.comparingLong(Dragon::getId)).getId();
        } catch (NoSuchElementException e) {
            return 0L;
        }
    }

    /**
     * Method parses data from file into collection.
     * @param path path where collection is stored.
     */
    public void parseFrom(File path) throws CsvValidationException, IOException{
        Parser pars = new Parser();
        ArrayList<String> fileLines;

        fileLines = pars.parseFromFile(path);

        ArrayList<String> columnList;
        for (String fileLine : fileLines) {
            columnList = pars.getItems(fileLine);

            Long id = Long.parseLong(columnList.get(0));
            String dragName = columnList.get(1);                         // including nulls
            Long cordX = Long.parseLong(columnList.get(2));
            float cordY = Float.parseFloat(columnList.get(3));
            String creationTime = columnList.get(4);
            int dragAge = Integer.parseInt(columnList.get(5));
            String description = columnList.get(6).equals("") ? null : columnList.get(6);
            Long wingspan = columnList.get(7).equals("") ? null : Long.parseLong(columnList.get(7));
            DragonType type = columnList.get(8).equals("") ? null : DragonType.valueOf(columnList.get(8));
            String personName = columnList.get(9).equals("") ? null : columnList.get(9) ;
            String date = columnList.get(10).equals("") ? null : columnList.get(10);
            Color eye = columnList.get(11).equals("") ? null : Color.valueOf(columnList.get(11));
            Color hair = columnList.get(12).equals("") ? null : Color.valueOf(columnList.get(12));
            Country nation = columnList.get(13).equals("") ? null : Country.valueOf(columnList.get(13));
            int locX = columnList.get(14).equals("") ? 0 : Integer.parseInt(columnList.get(14));
            long locY = columnList.get(15).equals("") ? 0 : Long.parseLong(columnList.get(15));
            long locZ = columnList.get(16).equals("") ? 0 : Long.parseLong(columnList.get(16));


            Person killer;
            if (personName == null)
                killer = null;
            else
                killer = new Person(personName, date, eye, hair, nation, new Location(locX, locY, locZ));

            collection.add(new Dragon(id, dragName, new Coordinates(cordX, cordY),creationTime, dragAge, description,
                    wingspan, type, killer));
        }
    }

    /**
     *
     * @return path to csv file with data.
     */
    public File getFile() {
        return path;
    }

    /**
     *
     * @return date and time of collection's initialization.
     */
    public LocalDateTime getInitDate() {
        return initDate;
    }

    /**
     *
     * @return size of the collection.
     */
    public int getSize() {
        return collection.size();
    }

    /**
     *
     * @return collection of dragons.
     */
    public PriorityQueue<Dragon> getCollection() {
        return collection;
    }

    /**
     * Inserts element into PriorityQueue.
     * @param dragon element to insert.
     */
    public void add(Dragon dragon) {
        collection.add(dragon);
    }

    /**
     * Removes element that id equals ident and returns creation date of that dragon.
     * @param ident id of element to delete.
     * @return date and time when dragon was created.
     * @throws NoSuchIdException if element with such id is not in collection.
     */
    public ZonedDateTime removeFromQueue(Long ident) throws NoSuchIdException {
        for (Dragon i : collection) {
            if (i.getId().equals(ident)) {
                ZonedDateTime creation = i.getCreationDate();
                collection.remove(i);
                return creation;
            }
        }

        throw new NoSuchIdException("There is no Dragon with such id in this collection");
    }

    /**
     * Removes all element from the collection.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Method adds to history new command.
     * @param command command to add in history.
     */
    public void updateHistory(String command) {
        if (history.size() >= 14)
            history.removeFirst();
        history.add(command);
    }

    /**
     * Method prints history in console.
     */
    public void showHistory() {
        if (history.size() == 0) {
            System.out.println("History is empty");
        }
        history.forEach(System.out::println);
    }

    /**
     * Method removes element with the same killer as given.
     * @param killer given killer.
     * @throws NoSuchKillerException if element with such killer is not in collection.
     */
    public void removeByKiller(Person killer) throws NoSuchKillerException{
        for (Dragon dragon : collection) {
            if (killer.equals(dragon.getKiller())) {
                collection.remove(dragon);
                return;
            }
        }
        throw new NoSuchKillerException();
    }

    /**
     * Removes first element in collection.
     */
    public void removeFirst() {
        collection.poll();
    }

    /**
     * Removes all elements that are greater than given dragon.
     * @param dragon dragon to be compared with.
     */
    public void removeGreater(Dragon dragon) {
        collection.removeIf(drag -> drag.getName().compareTo(dragon.getName()) > 0);
    }

}
