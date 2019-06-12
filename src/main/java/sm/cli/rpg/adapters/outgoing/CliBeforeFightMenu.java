package sm.cli.rpg.adapters.outgoing;

import sm.cli.rpg.adapters.util.io.InputParser;
import sm.cli.rpg.adapters.util.io.OutputWriter;
import sm.cli.rpg.adapters.util.menu.CliEnumMenuBase;
import sm.cli.rpg.ports.outgoing.BeforeFightMenu;
import sm.cli.rpg.ports.outgoing.dto.BeforeFightMenuItem;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class CliBeforeFightMenu extends CliEnumMenuBase<BeforeFightMenuItem> implements BeforeFightMenu {
    public CliBeforeFightMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter, BeforeFightMenuItem.values());
    }
}
