package sm.cli.rpg.domain.exception;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class WrongParameterException extends GameException {
    public WrongParameterException(String valueType, String value) {
        super("Provided " + valueType + " value (" + value + ") is not valid");
    }
}
