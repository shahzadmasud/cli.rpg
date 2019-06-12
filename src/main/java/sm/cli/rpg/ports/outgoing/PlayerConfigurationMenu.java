package sm.cli.rpg.ports.outgoing;

import sm.cli.rpg.ports.outgoing.dto.PlayerConfiguration;
import sm.cli.rpg.ports.outgoing.dto.QuestionsToPlayer;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public interface PlayerConfigurationMenu {
 
    PlayerConfiguration askForPlayerConfig(QuestionsToPlayer questionsToPlayer);

    void showIntroduction(String introduction);

    void greetPlayer(String greetingMessage);
}
