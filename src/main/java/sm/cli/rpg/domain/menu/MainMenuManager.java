package sm.cli.rpg.domain.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sm.cli.rpg.domain.exception.ShouldNeverHappen;
import sm.cli.rpg.domain.game.GameManager;
import sm.cli.rpg.ports.exception.ConfigurationException;
import sm.cli.rpg.ports.outgoing.GameStateProvider;
import sm.cli.rpg.ports.outgoing.MainMenu;
import sm.cli.rpg.ports.outgoing.RealmConfigurationProvider;
import sm.cli.rpg.ports.outgoing.dto.MainMenuItem;

import static sm.cli.rpg.common.util.CliColorFormat.boldMagenta;
import static sm.cli.rpg.ports.outgoing.dto.MainMenuItem.EXIT;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class MainMenuManager {
    private static final Logger LOG = LogManager.getLogger(MainMenuManager.class);

    private final RealmConfigurationProvider configurationProvider;
    private final GameStateProvider gameStateProvider;
    private final AllMenus allMenus;
    private final MainMenu mainMenu;

    public MainMenuManager(RealmConfigurationProvider configurationProvider, AllMenus allMenus, GameStateProvider gameStateProvider) {
        this.allMenus = allMenus;
        this.mainMenu = allMenus.mainMenu();
        this.configurationProvider = configurationProvider;
        this.gameStateProvider = gameStateProvider;
    }

    public void showMenu() throws ConfigurationException {
        LOG.traceEntry();

        MainMenuItem choice;
        do {
            mainMenu.showMessage(boldMagenta("\nWelcome to Main Menu"));
            choice = mainMenu.showMenu();

            switch (choice) {
                case START:
                    GameManager.newGame(gameStateProvider, allMenus, configurationProvider.load());
                    break;
                case LOAD:
                    GameManager.loadGame(gameStateProvider, allMenus);
                    break;
                case EXIT:
                    mainMenu.showMessage("Come back soon! :)");
                    break;
                default:
                    throw new ShouldNeverHappen();
            }
        } while (EXIT != choice);


        LOG.traceExit();
    }


}
