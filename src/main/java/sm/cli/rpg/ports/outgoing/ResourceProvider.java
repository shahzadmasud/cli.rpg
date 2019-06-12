package sm.cli.rpg.ports.outgoing;

import sm.cli.rpg.ports.exception.ConfigurationException;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @param <T>
 * @since 09-Jun-2019
 */

public interface ResourceProvider<T> {
    List<T> load() throws ConfigurationException;

    void save(List<T> resource) throws ConfigurationException;

    default T loadOne() throws ConfigurationException {
        List<T> load = load();
        if (load.size() > 1) {
            throw new ConfigurationException("Only one entry permitted for this resource");
        }

        return load.get(0);
    }

    default void saveOne(T resource) throws ConfigurationException {
        List<T> listWithOneResource = Collections.singletonList(resource);
        save(listWithOneResource);
    }
}
