package sm.cli.rpg.domain.exception;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class GameException extends RuntimeException {
    public GameException() {
    }

    public GameException(String message) {
        super(message);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameException(Throwable cause) {
        super(cause);
    }

    public GameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
