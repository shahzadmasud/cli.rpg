package sm.cli.rpg.domain.exception;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class NullParameterException extends GameException {
    public NullParameterException(String valueType) {
        super(valueType + " cannot be null");
    }
}
