package sm.cli.rpg.adapters.outgoing;

import sm.cli.rpg.adapters.util.io.InputParser;
import sm.cli.rpg.adapters.util.io.OutputWriter;
import sm.cli.rpg.adapters.util.menu.CliMenuBase;
import sm.cli.rpg.ports.outgoing.WorldConfigurationMenu;
import sm.cli.rpg.ports.outgoing.dto.RealmConfiguration;

import java.util.List;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class CliWorldConfigurationMenu extends CliMenuBase<RealmConfiguration> implements WorldConfigurationMenu {

    public CliWorldConfigurationMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter);
    }

    @Override
    public RealmConfiguration chooseConfiguration(String realmQuestion, List<RealmConfiguration> realmConfigs) {
        setMenuItems(realmConfigs);

        printAllOptions(realmQuestion);
        return selectOption();
    }

    @Override
    public void confirmRealmConfiguration(String realmConfirmationMessage) {
        outputWriter.showMessageWithSpace(realmConfirmationMessage);
    }
}
