package sm.cli.rpg.adapters.util.io;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class UserInputParseException extends RuntimeException {

    private static final String MSG = "After many attempts user could not enter any valid input. Game will shut down. Good luck next time!";

    public UserInputParseException(String message) {
        super(message);
    }

    public UserInputParseException() {
        super(MSG);
    }

    public UserInputParseException(Throwable cause) {
        super(MSG, cause);
    }
}
