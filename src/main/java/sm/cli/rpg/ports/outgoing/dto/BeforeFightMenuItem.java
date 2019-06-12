package sm.cli.rpg.ports.outgoing.dto;

import static sm.cli.rpg.common.util.CliColorFormat.blue;
import static sm.cli.rpg.common.util.CliColorFormat.red;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public enum BeforeFightMenuItem {
    ATTACK(red("Attack!!!")),
    FALL_BACK(blue("Fall back from this fight."));
    private final String description;

    BeforeFightMenuItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
