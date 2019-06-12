package sm.cli.rpg.adapters.outgoing;

import sm.cli.rpg.adapters.util.io.InputParser;
import sm.cli.rpg.adapters.util.io.OutputWriter;
import sm.cli.rpg.adapters.util.menu.CliEnumMenuBase;
import sm.cli.rpg.adapters.util.menu.ExplorationMenuToStringFormatter;
import sm.cli.rpg.adapters.util.menu.MenuToStringFormatter;
import sm.cli.rpg.ports.outgoing.ExplorationMenu;
import sm.cli.rpg.ports.outgoing.dto.ExplorationMenuItem;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class CliExplorationMenu extends CliEnumMenuBase<ExplorationMenuItem> implements ExplorationMenu {
    public CliExplorationMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter, ExplorationMenuItem.values());
    }

    @Override
    public void showMap(String map) {
        outputWriter.showMessageWithSpace(map);
    }

    @Override
    public void showStatistics(String statistics) {
        outputWriter.showMessageWithSpace(statistics);
    }

    @Override
    protected MenuToStringFormatter<ExplorationMenuItem> menuFormatter() {
        return new ExplorationMenuToStringFormatter();
    }

    @Override
    protected ExplorationMenuItem readUserChoice() {
        ExplorationMenuItem choice = ExplorationMenuItem.fromString(inputParser.readUserInputAsString());
        if (null == choice) {
            showMessage("You have chosen a wrong option, please try again");
            return ExplorationMenuItem.COMMANDS;
        }

        return choice;
    }

}
