package dragon;

import java.util.Objects;

/**
 * Class {@code Coordinates} defines dragon's coordinates on x an y
 */
public class Coordinates {
    private Long x; //Максимальное значение поля: 302, Поле не может быть null
    private float y;

    /**
     * Defines coordinates of dragon.
     * @param x coordinate on x.
     * @param y coordinate on y.
     */
    public Coordinates(Long x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return value of x coordinate.
     */
    public Long getX() {
        return x;
    }

    /**
     *
     * @return value of x coordinate.
     */
    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return  "x = " + x + ", y = " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.y, y) == 0 &&
                x.equals(that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}