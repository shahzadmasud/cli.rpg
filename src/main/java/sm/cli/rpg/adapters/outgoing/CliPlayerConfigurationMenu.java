package sm.cli.rpg.adapters.outgoing;

import sm.cli.rpg.adapters.util.io.InputParser;
import sm.cli.rpg.adapters.util.io.OutputWriter;
import sm.cli.rpg.adapters.util.menu.CliMenuBase;
import sm.cli.rpg.domain.exception.PlayerValidationException;
import sm.cli.rpg.ports.outgoing.PlayerConfigurationMenu;
import sm.cli.rpg.ports.outgoing.dto.PlayerConfiguration;
import sm.cli.rpg.ports.outgoing.dto.QuestionsToPlayer;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class CliPlayerConfigurationMenu extends CliMenuBase<String> implements PlayerConfigurationMenu {

    public CliPlayerConfigurationMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter);
    }

    @Override
    public PlayerConfiguration askForPlayerConfig(QuestionsToPlayer questions) {
        PlayerConfiguration playerConfiguration = null;

        PlayerConfiguration.PlayerConfigurationBuilder playerConfigurationBuilder = PlayerConfiguration.builder(questions.getMaxBonusPoints());
        playerConfigurationBuilder.withName(inputParser.readUserInputAsString(questions.getNameQuestion()));
        playerConfigurationBuilder.withDesc(inputParser.readUserInputAsString(questions.getDescQuestion()));

        do {
            try {
                showMessage(questions.getBonusStatsDescription());
                playerConfigurationBuilder.withHpBonus(inputParser.tryReadingInputAsInt(questions.getBonusStatsBonusHpQuestion()));
                playerConfigurationBuilder.withDamageBonus(inputParser.tryReadingInputAsInt(questions.getBonusStatsBonusDanageQuestion()));
                playerConfigurationBuilder.withDamageVariationBonus(inputParser.tryReadingInputAsInt(questions.getBonusStatsBonusDanageVariationQuestion()));

                playerConfiguration = playerConfigurationBuilder.build();
            } catch (PlayerValidationException e) {
                showMessage(e.getMessage());
            }
        } while (playerConfiguration == null);

        return playerConfiguration;
    }

    @Override
    public void showIntroduction(String introduction) {
        outputWriter.showMessageWithSpace(introduction);
    }

    @Override
    public void greetPlayer(String greetingMessage) {
        outputWriter.showMessageWithSpace(greetingMessage);
    }
}
