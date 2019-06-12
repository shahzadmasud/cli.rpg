package sm.cli.rpg.ports.outgoing;

import sm.cli.rpg.ports.outgoing.dto.RealmConfiguration;

import java.util.List;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public interface WorldConfigurationMenu {
    RealmConfiguration chooseConfiguration(String realmQuestion, List<RealmConfiguration> realmConfigs);

    void confirmRealmConfiguration(String realmConfirmationMessage);
}
