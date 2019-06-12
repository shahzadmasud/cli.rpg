package sm.cli.rpg.domain.exception;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class ExplorationException extends Exception {
    public static void cannotGo(int index) throws ExplorationException {
        throw new ExplorationException("Cannot go to " + index + "! You can't just jump out of the reality!");
    }

    public ExplorationException(String message) {
        super(message);
    }
}
