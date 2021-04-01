package dragon;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Defines a person who killed the dragon.
 */
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDateTime birthday; //Поле не может быть null
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле не может быть null

    /**
     * Constructs a killer with given parameters.
     * @param n name of the killer.
     * @param time date and time of birth.
     * @param eye eye color.
     * @param hair hair color.
     * @param country country of birth.
     * @param loc location of killer.
     */
    public Person(String n, String time, Color eye, Color hair, Country country, Location loc) {
        name = n;
        eyeColor = eye;
        birthday = LocalDateTime.parse(time.replace(" ", "T"));
        hairColor = hair;
        nationality = country;
        location = loc;
    }

    /**
     *
     * @return time and date of birth.
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     *
     * @return country of birth.
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     *
     * @return hair color.
     */
    public Color getHairColor() {
        return hairColor;
    }

    /**
     *
     * @return eye color
     */
    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     *
     * @return name of the killer.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return location of the killer.
     */
    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return "\n  name: " + name + "\n" +
                "  birthday: " + fmt.format(birthday) + "\n" +
                "  eyeColor: " + eyeColor + "\n" +
                "  hairColor: " + hairColor + "\n" +
                "  nationality: " + nationality + "\n" +
                "  location: " + location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return name.equals(person.name) &&
                birthday.equals(person.birthday) &&
                eyeColor == person.eyeColor &&
                hairColor == person.hairColor &&
                nationality == person.nationality &&
                location.equals(person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, eyeColor, hairColor, nationality, location);
    }
}