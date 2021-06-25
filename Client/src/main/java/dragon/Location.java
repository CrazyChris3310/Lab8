package dragon;

import java.io.Serializable;
import java.util.Objects;

/**
 * Defines Location of the killer.
 */
public class Location implements Serializable {
    private int x;
    private Long y; //Поле не может быть null
    private long z;

    private static final long serialVersionUID = 203L;

    public Location(int x, Long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     *
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return y coordinate
     */
    public Long getY() {
        return y;
    }

    /**
     * @return z coordinate.
     */
    public long getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public void setZ(long z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return  String.format("x = %d, y = %d, z = %d", x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                z == location.z &&
                y.equals(location.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}