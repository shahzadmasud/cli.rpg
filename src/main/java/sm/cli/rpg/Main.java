package sm.cli.rpg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sm.cli.rpg.adapters.util.io.UserInputParseException;
import sm.cli.rpg.common.di.DICache;
import sm.cli.rpg.domain.menu.MainMenuManager;
import sm.cli.rpg.ports.exception.ConfigurationException;

/**
 * Main class for starting the CLI
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */
public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    private static MainMenuManager mainMenuManager = DICache.getBean(MainMenuManager.class);

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        try {
            LOG.debug("CLI calling MainMenuManager...");
            mainMenuManager.showMenu();
        } catch (UserInputParseException e) {
            shutdown(e.getMessage(), e);
        } catch (ConfigurationException e) {
            if (null != e.getMessage()) {
                shutdown(e.getMessage(), e);
            } else {
                String msg = "There was a problem with the configuration.";
                shutdown(msg, e);
            }

        } catch (Throwable t) {
            String msg = "There was a general problem with the game.";
            shutdown(msg, t);
        }
    }

    private static void shutdown(String msg, Throwable e) {
        System.out.println(msg);
        LOG.fatal(msg, e);
        System.exit(1);
    }
}
