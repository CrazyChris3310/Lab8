package utilities;

import exceptions.NoRightsException;
import exceptions.NoSuchIdException;
import exceptions.NoSuchKillerException;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import dragon.*;
import utilities.dataBase.DataBaseConnection;
import utilities.dataBase.DataBaseRequests;

/**
 * Class {@code utilities.DragonCollection} defines the collection and methods for managing it.
 */
public class DragonCollection {

    private PriorityQueue<Dragon> collection;
    private LinkedList<String> history;
    final private LocalDateTime initDate;

    private HashSet<Path> scripts;

    DataBaseRequests requests;

    /**
     * Constructs collection and fills it from given file.
     */
    public DragonCollection(DataBaseConnection dbc) throws IOException, SQLException {
        initDate = LocalDateTime.now();
        collection = new PriorityQueue<Dragon>();
        history = new LinkedList<>();
        scripts = new HashSet<Path>();
        requests = new DataBaseRequests(dbc);
        requests.parseIntoCollection(collection);
    }

    /**
     * @return date and time of collection's initialization.
     */
    public LocalDateTime getInitDate() {
        return initDate;
    }

    /**
     * @return size of the collection.
     */
    public int getSize() {
        return collection.size();
    }

    /**
     * @return collection of dragons.
     */
    public PriorityQueue<Dragon> getCollection() {
        return collection;
    }

    /**
     * Inserts element into PriorityQueue.
     *
     * @param dragon element to insert.
     */
    public void add(Dragon dragon, String login) throws SQLException {
        long id = requests.insertIntoCollection(dragon, login);
        dragon.setId(id);
        dragon.setOwner(login);
        collection.add(dragon);
    }

    public void updateId(Long id, Dragon dragon, String login) throws NoSuchIdException,
            SQLException, NoRightsException {
        boolean idExists = collection.stream()
                .anyMatch(dr -> dr.getId().equals(id));
        boolean dragonExists = collection.stream()
                .anyMatch(dr -> dr.getId().equals(id) && dr.getOwner().equals(login));

        if (dragonExists) {
            requests.updateId(id, dragon, login);
            collection.clear();
            requests.parseIntoCollection(collection);
        } else if (idExists) {
            throw new NoRightsException();
        } else {
            throw new NoSuchIdException("No such Id");
        }
    }

    /**
     * Removes element that id equals ident and returns creation date of that dragon.
     *
     * @param ident id of element to delete.
     * @throws NoSuchIdException if element with such id is not in collection.
     */
    public void removeFromQueue(Long ident, String login) throws NoSuchIdException,
            SQLException, NoRightsException {

        Dragon toRemove = collection.stream()
                .filter(dr -> dr.getId().equals(ident))
                .findFirst().orElse(null);

        if (toRemove != null) {
            requests.removeById(ident, login);
            collection.remove(toRemove);
            return;
        }

        throw new NoSuchIdException("There is no Dragon with such id in this collection");
    }

    /**
     * Removes all element from the collection.
     */
    public void clear(String login) throws SQLException {
        requests.clear(login);
        collection = collection.stream()
                .filter(dr -> !dr.getOwner().equals(login))
                .collect(Collectors.toCollection(PriorityQueue::new));
    }

    /**
     * Method adds to history new command.
     *
     * @param command command name to add in history.
     */
    public void updateHistory(String command) {
        if (history.size() >= 14)
            history.removeFirst();
        history.add(command);
    }

    /**
     * Method prints history in console.
     */
    public ArrayList<String> getHistory() {
        ArrayList<String> list = new ArrayList<>();
        if (history.size() == 0)
            list.add("The history is empty");
        else
            list = new ArrayList<>(history);
        return list;
    }

    /**
     * Method removes element with the same killer as given.
     *
     * @param killer given killer.
     * @throws NoSuchKillerException if element with such killer is not in collection.
     */
    public void removeByKiller(Person killer, String login) throws NoSuchKillerException,
            NoRightsException, SQLException {

        ArrayList<Dragon> potentialRemoval = collection.stream()
                .filter(dr -> killer.equals(dr.getKiller()))
                .collect(Collectors.toCollection(ArrayList::new));
        if (potentialRemoval.isEmpty())
            throw new NoSuchKillerException();

        Dragon toRemove = potentialRemoval.stream()
                .filter(dr -> dr.getOwner().equals(login))
                .findFirst().orElse(null);

        if (toRemove == null)
            throw new NoRightsException();

        requests.removeById(toRemove.getId(), login);
        collection.remove(toRemove);
    }

    /**
     * Removes first element in collection.
     */
    public void removeFirst(String login) throws SQLException, NoRightsException {
        Dragon toRemove = collection.peek();
        if (toRemove != null) {
            requests.removeById(toRemove.getId(), login);
            collection.poll();
        }
    }

    /**
     * Removes all elements that are greater than given dragon.
     *
     * @param dragon dragon to be compared with.
     */
    public void removeGreater(Dragon dragon, String login) throws SQLException, NoRightsException {
        ArrayList<Dragon> toRemove = collection.stream()
                .filter(dr -> dr.compareTo(dragon) > 0 && dr.getOwner().equals(login))
                .collect(Collectors.toCollection(ArrayList::new));

        for (Dragon dr : toRemove) {
            requests.removeById(dr.getId(), login);
            collection.remove(dr);
        }
    }
}
