package sm.cli.rpg.domain.exception;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class ShouldNeverHappen extends GameException {
    public ShouldNeverHappen() {
        super("Should never happen");
    }
}
