package sm.cli.rpg.adapters.outgoing;

import sm.cli.rpg.adapters.util.io.InputParser;
import sm.cli.rpg.adapters.util.io.OutputWriter;
import sm.cli.rpg.adapters.util.menu.CliEnumMenuBase;
import sm.cli.rpg.ports.outgoing.MainMenu;
import sm.cli.rpg.ports.outgoing.dto.MainMenuItem;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class CliMainMenu extends CliEnumMenuBase<MainMenuItem> implements MainMenu {

    public CliMainMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter, MainMenuItem.values());
    }

    @Override
    public MainMenuItem showMenu() {
        printAllOptions();
        return selectOption();
    }
}
