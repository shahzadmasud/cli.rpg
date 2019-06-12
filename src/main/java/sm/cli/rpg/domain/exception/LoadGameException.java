package sm.cli.rpg.domain.exception;

import sm.cli.rpg.ports.exception.ConfigurationException;

import static sm.cli.rpg.common.util.CliColorFormat.bold;
import static sm.cli.rpg.common.util.CliColorFormat.red;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class LoadGameException extends ConfigurationException {
    public LoadGameException(Throwable cause) {
        super(bold(red("Failed to load game, please start a new one")), cause);
    }
}