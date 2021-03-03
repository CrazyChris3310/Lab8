package Dragon;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Class {@code Dragon} defines a dragon with its characteristics
 */
public class Dragon implements Comparable{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private String description; //Поле может быть null
    private Long wingspan; //Значение поля должно быть больше 0, Поле может быть null
    private DragonType type; //Поле может быть null
    private Person killer; //Поле может быть null

    private static Long nextDragonId = 1L;


    /**
     * Construct dragon with given parameters from the csv file.
     * @param id dragon's id.
     * @param name dragon's name.
     * @param coordinates dragon's coordinates.
     * @param creationTime dragon's creation time.
     * @param age dragon's age.
     * @param description dragon's description.
     * @param wingspan dragon's wingspan.
     * @param type dragon's type.
     * @param killer dragon's killer.
     */
    public Dragon(Long id, String name, Coordinates coordinates, String creationTime, int age, String description,
                  Long wingspan, DragonType type, Person killer) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = defineDateTime(creationTime);
        this.age = age;
        this.description = description;
        this.wingspan = wingspan;
        this.type = type;
        this.killer = killer;
        if (id >= nextDragonId)
            nextDragonId = id + 1;
    }

    /**
     * Construct dragon with given parameters when it is created by commands.
     * @param name dragon's name.
     * @param coordinates dragon's coordinates.
     * @param age dragon's age.
     * @param description dragon's description.
     * @param wingspan dragon's wingspan.
     * @param type dragon's type.
     * @param killer dragon's killer.
     */
    public Dragon(String name, Coordinates coordinates, int age, String description,
                  Long wingspan, DragonType type, Person killer) {
        this.id = nextDragonId++;
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.description = description;
        this.wingspan = wingspan;
        this.type = type;
        this.killer = killer;
        this.creationDate = ZonedDateTime.now();
    }

    /**
     *
     * @return dragon's id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @return dragon's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * @return dragon's killer.
     */
    public Person getKiller() {
        return killer;
    }

    /**
     * @return dragon's name.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return dragon's creation date and time.
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return dragon's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return dragon's coordinates.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return dragon's type.
     */
    public DragonType getType() {
        return type;
    }

    /**
     * @return dragon's wingspan.
     */
    public Long getWingspan() {
        return wingspan;
    }

    /**
     * Sets id to this dragon.
     * @param id id of the dragon.
     */
    public void setId(Long id) {
        this.id = id;
    }

    private ZonedDateTime defineDateTime(String dt) {
        return ZonedDateTime.parse(dt.replace(" +", "+").replace(" -", "-")
                .replace(" ", "T"));
    }

    /**
     * Sets given creation date and time.
     * @param zdt date and time to be set.
     */
    public void setCreationDate(ZonedDateTime zdt) {
        creationDate = zdt;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss xxxxx");
        return  "id: " + id + "\n" +
                "name: " + name + "\n" +
                "coordinates: " + coordinates + "\n" +
                "creationDate: " + fmt.format(creationDate) + "\n" +
                "age: " + age + "\n" +
                "description: " + description + "\n" +
                "wingspan: " + wingspan + "\n" +
                "type: " + type + "\n" +
                "killer: " + killer + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dragon dragon = (Dragon) o;
        return age == dragon.age &&
                name.equals(dragon.name) &&
                coordinates.equals(dragon.coordinates) &&
                Objects.equals(description, dragon.description) &&
                Objects.equals(wingspan, dragon.wingspan) &&
                type == dragon.type &&
                Objects.equals(killer, dragon.killer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, age, description, wingspan, type, killer);
    }

    @Override
    public int compareTo(Object o) {
        return name.compareTo(((Dragon)o).getName());
    }
}
