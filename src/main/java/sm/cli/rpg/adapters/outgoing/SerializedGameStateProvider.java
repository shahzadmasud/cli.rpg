package sm.cli.rpg.adapters.outgoing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sm.cli.rpg.domain.exception.LoadGameException;
import sm.cli.rpg.ports.exception.ConfigurationException;
import sm.cli.rpg.ports.outgoing.GameStateProvider;
import sm.cli.rpg.ports.outgoing.dto.GameState;

import java.util.List;

import static sm.cli.rpg.domain.game.StaticMessages.GAME_LOADING_FAILED;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 * 
 * TODO: Modify this one to JSON
 */
public class SerializedGameStateProvider extends SerializedResourceProvider<GameState> implements GameStateProvider {
    private static final Logger LOG = LogManager.getLogger(SerializedGameStateProvider.class);

    private static final String DEFAULT_SAVE_FILENAME = "save.ser";

    @Override
    public List<GameState> load() throws ConfigurationException {
        LOG.traceEntry();
        try {
            return super.load();
        } catch (ConfigurationException e) {
            throw new LoadGameException(e);
        }
    }

    @Override
    protected List<GameState> handleException(Exception e) throws ConfigurationException {
        throw new ConfigurationException(GAME_LOADING_FAILED, e);
    }

    @Override
    protected String getFilename() {
        return DEFAULT_SAVE_FILENAME;
    }

    @Override
    protected String basePath() {
        return savePath();
    }

    @Override
    protected boolean isLoadExternal() {
        return true;
    }
}
