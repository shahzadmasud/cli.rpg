package sm.cli.rpg.adapters.util.menu;

import sm.cli.rpg.ports.outgoing.dto.ExplorationMenuItem;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class ExplorationMenuToStringFormatter extends MenuToStringFormatter<ExplorationMenuItem> {
    public ExplorationMenuToStringFormatter() {
    }

    public String format(ExplorationMenuItem item, int itemNumberInList) {
        return format(item.getDescription(), item.getKeyBinding());
    }
}
