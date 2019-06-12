package sm.cli.rpg.domain.world.location;

import sm.cli.rpg.common.util.MapStringBuilder;
import sm.cli.rpg.domain.character.NPC;

import java.io.Serializable;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class Location implements Serializable {
    private final Coordinates coordinates;
    private LocationType type;
    private NPC npc;

    public Location(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.type = LocationType.EMPTY;
    }

    public Location(Coordinates coordinates, NPC npc) {
        this.coordinates = coordinates;
        this.type = npc.locationType();
        this.npc = npc;
    }

    public boolean isAnyoneThere() {
        return null != npc && npc.isAlive();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public NPC getNpc() {
        return npc;
    }

    public LocationType getType() {
        return relevantLocationType();
    }

    public String mapMark() {
        return relevantLocationType().getMapMark();
    }

    public String desc() {
        return relevantLocationType().getDescription();
    }

    private LocationType relevantLocationType() {
        if (null != npc) {
            return npc.locationType();
        }

        return type;
    }

    @Override
    public String toString() {
        return MapStringBuilder.defaultBuilder(this)
                .append("coordinates", coordinates.toString())
                .append("type", type.name())
                .append("mapMark", mapMark())
                .build();
    }
}
