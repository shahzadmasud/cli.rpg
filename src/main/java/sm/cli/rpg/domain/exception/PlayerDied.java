package sm.cli.rpg.domain.exception;

import static sm.cli.rpg.domain.game.StaticMessages.DEFEAT;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class PlayerDied extends GameException {
    public PlayerDied() {
        super(DEFEAT);
    }
}
