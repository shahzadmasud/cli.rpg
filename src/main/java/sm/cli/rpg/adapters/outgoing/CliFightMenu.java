package sm.cli.rpg.adapters.outgoing;

import sm.cli.rpg.adapters.util.io.InputParser;
import sm.cli.rpg.adapters.util.io.OutputWriter;
import sm.cli.rpg.adapters.util.menu.CliEnumMenuBase;
import sm.cli.rpg.ports.outgoing.FightMenu;
import sm.cli.rpg.ports.outgoing.dto.FightMenuItem;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class CliFightMenu extends CliEnumMenuBase<FightMenuItem> implements FightMenu {
    public CliFightMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter, FightMenuItem.values());
    }
}
