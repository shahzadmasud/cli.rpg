package sm.cli.rpg.ports.outgoing;

import sm.cli.rpg.ports.outgoing.dto.ExplorationMenuItem;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public interface ExplorationMenu extends BaseMenu<ExplorationMenuItem> {

    void showMap(String map);

    void showStatistics(String statistics);
}
