package sm.cli.rpg.ports.outgoing.dto;

import sm.cli.rpg.domain.character.Player;
import sm.cli.rpg.domain.world.World;

import java.io.Serializable;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */
public class GameState implements Serializable {
    private final World world;
    private final Player player;

    public GameState(World world, Player player) {
        this.world = world;
        this.player = player;
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }
}
