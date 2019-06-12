package sm.cli.rpg.domain.exception;

import sm.cli.rpg.domain.character.Player;
import sm.cli.rpg.domain.world.World;

import static sm.cli.rpg.domain.game.StaticMessages.VICTORY;

/**
 * Using exception handling for winning logic too.
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class Victory extends GameException {
    public Victory(String worldName, String playerName) {
        super(String.format(VICTORY, playerName, worldName));
    }

    public static void victory(World world, Player player) {
        throw new Victory(world.getName(), player.getName());
    }
}
