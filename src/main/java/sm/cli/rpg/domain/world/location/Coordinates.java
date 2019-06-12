package sm.cli.rpg.domain.world.location;

import sm.cli.rpg.common.util.MapStringBuilder;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class Coordinates implements Serializable {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinates up() {
        return new Coordinates(x, y - 1);
    }

    public Coordinates down() {
        return new Coordinates(x, y + 1);
    }

    public Coordinates left() {
        return new Coordinates(x - 1, y);
    }

    public Coordinates right() {
        return new Coordinates(x + 1, y);
    }

    @Override
    public String toString() {
        return MapStringBuilder.defaultBuilder(this)
                .append("x", x)
                .append("y", y)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;

        Coordinates that = (Coordinates) o;

        return x == that.x && y == that.y;
    }

    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
