package sm.cli.rpg.ports.outgoing;

import sm.cli.rpg.ports.exception.ConfigurationException;
import sm.cli.rpg.ports.outgoing.dto.GameState;

/**
 * TODO: extend to support loading/saving multiple game states
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public interface GameStateProvider extends ResourceProvider<GameState> {
    default GameState loadGame() throws ConfigurationException {
        return loadOne();
    }

    default void saveGame(GameState gameState) throws ConfigurationException {
        saveOne(gameState);
    }
}
