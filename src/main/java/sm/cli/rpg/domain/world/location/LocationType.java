package sm.cli.rpg.domain.world.location;

import sm.cli.rpg.common.util.MapStringBuilder;
import static sm.cli.rpg.common.util.CliColorFormat.boldMagenta;
import static sm.cli.rpg.common.util.CliColorFormat.green;
import static sm.cli.rpg.common.util.CliColorFormat.red;
import static sm.cli.rpg.common.util.CliColorFormat.underlinedBlue;

/**
 * TODO: Addition of more location types (JUNGLE, DESERT, STREET, OBSTACLE)
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public enum LocationType {

    EMPTY("Nothing is here", " "),
    ALLY("A friend looks gently at you", green("A")),
    ENEMY("Enemy sighted!", red("E")),
    NPC_DEAD("Someone died here", boldMagenta("X")),
    PLAYER("This is you, my dear Player", underlinedBlue("P")) ;

    private final String description;
    private final String mapMark;

    LocationType(String description, String mapMark) {
        this.description = description;
        this.mapMark = mapMark;
    }

    public String getDescription() {
        return description;
    }

    public String getMapMark() {
        return mapMark;
    }

    @Override
    public String toString() {
        return MapStringBuilder.defaultBuilder(this)
                .append("name", name())
                .append("mapMark", mapMark)
                .append("description", description)
                .build();
    }
}
