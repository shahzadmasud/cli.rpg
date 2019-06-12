package sm.cli.rpg.adapters.outgoing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sm.cli.rpg.common.generator.SerializationRealmConfigurationGenerator;
import sm.cli.rpg.ports.outgoing.RealmConfigurationProvider;
import sm.cli.rpg.ports.outgoing.dto.RealmConfiguration;

import java.util.List;

import static sm.cli.rpg.adapters.util.io.ExternalIO.resourcesPath;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class SerializedRealmConfigurationProvider extends SerializedResourceProvider<RealmConfiguration> implements RealmConfigurationProvider {
    private static final Logger LOG = LogManager.getLogger(SerializedRealmConfigurationProvider.class);

    public static final String FILENAME = "realm_configuration.ser";

    @Override
    protected String getFilename() {
        return FILENAME;
    }

    @Override
    protected String basePath() {
        return resourcesPath() + configPath();
    }

    @Override
    protected boolean isLoadExternal() {
        return false;
    }

    @Override
    protected List<RealmConfiguration> handleException(Exception e) {
        return rollbackToBuiltInDefault();
    }

    private List<RealmConfiguration> rollbackToBuiltInDefault() {
        LOG.error("could not load realm configuration from a file, rolling back to defaults");
        return SerializationRealmConfigurationGenerator.realms();
    }
}
