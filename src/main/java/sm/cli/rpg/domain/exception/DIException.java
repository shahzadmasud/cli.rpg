package sm.cli.rpg.domain.exception;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class DIException extends RuntimeException {
    private static String MSG = "All hell broke loose! Shutting everything down...";

    public DIException() {
        super(MSG);
    }

    public DIException(Throwable cause) {
        super(MSG, cause);
    }
}
