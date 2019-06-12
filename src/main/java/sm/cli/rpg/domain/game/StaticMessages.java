package sm.cli.rpg.domain.game;

import sm.cli.rpg.domain.character.Player;
import sm.cli.rpg.domain.world.World;
import sm.cli.rpg.ports.outgoing.dto.QuestionsToPlayer;

import static sm.cli.rpg.common.util.CliColorFormat.blue;
import static sm.cli.rpg.common.util.CliColorFormat.bold;
import static sm.cli.rpg.common.util.CliColorFormat.boldMagenta;
import static sm.cli.rpg.common.util.CliColorFormat.green;
import static sm.cli.rpg.common.util.CliColorFormat.red;
import static sm.cli.rpg.common.util.CliColorFormat.yellow;
import static sm.cli.rpg.domain.character.PlayerBaseStatistics.BASE_DMG;
import static sm.cli.rpg.domain.character.PlayerBaseStatistics.BASE_DMG_VARIATION;
import static sm.cli.rpg.domain.character.PlayerBaseStatistics.BASE_HP;
import static sm.cli.rpg.domain.builder.LegendBuilder.buildLegend;
import static sm.cli.rpg.domain.builder.WorldViewBuilder.buildWorldView;

/**
 * TODO: Change to configuration or database 
 * 
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public interface StaticMessages {
    int MAX_BONUS_POINTS = 5;
    int FIGHT_BONUS_ARMOR_FOR_DEFENCE = 2;
    int FIGHT_FLEEING_HP_REDUCTION = 5;
    int FIGHT_DAMAGE_VARIATION_MULTIPLIER = 2;

    
    int EXP_TO_FIRST_LEVEL_UP = 100;
    float NEXT_LEVEL_EXP_MULTIPLIER = 1.1f;

    String ENEMY_DEFEATED = "Defeating this enemy gave you %d xp. ";
    String LEVEL_UP = "You earned enough xp to " + boldMagenta("level up") + " your hero! ";

    String REALM_QUESTION = "In which realm would you like to begin your journey?";

    String REALM_CONFIRMATION_MESSAGE = boldMagenta("%s") + "? How exciting! Good luck!";

    String INTRODUCTION = "Hello, stranger.\n" +
            "Let me welcome you in our little town during those uneasy times.\n" +
            "Can you tell us something about yourself?";

    String NAME_QUESTION = "What's your name?";
    String DESC_QUESTION = "What's your story?";
    String BONUS_STATS_DESC = "\nWhat are your skills?\n" +
            "you may distribute " + yellow(MAX_BONUS_POINTS + " skill points") + " to 3 attributes: bonus hp, bonus dmg and bonus dmg variation\n" +
            "base statistics are: " + yellow("100 hp and 10-1" + FIGHT_DAMAGE_VARIATION_MULTIPLIER + " dmg")
            + " (10 base damage and 1 damage variation. every variation point gives 0-" + FIGHT_DAMAGE_VARIATION_MULTIPLIER + " dmg)";
    String BONUS_STATS_HP_QUESTION = "What is your " + yellow("bonus HP") + "? (base = " + BASE_HP + ")";
    String BONUS_STATS_DMG_QUESTION = "What is your " + yellow("bonus damage") + "? (base = " + BASE_DMG + ")";
    String BONUS_STATS_DMG_VAR_QUESTION = "What is your " + yellow("bonus damage variation") + "? (base = " + BASE_DMG_VARIATION + ")";

    String GREETING = boldMagenta("Wow, %s") + ", you have some amazing origin story. And those stats? Marvelous!\n" +
            "%s\n" +
            "You will fit perfectly in the world of " + boldMagenta("%s") +
            "\n\n" +
            "Here, have a " + blue("map") + ". You'll be able to use it whenever you feel lost!\n" +
            "%s\n" +
            "%s";

    String TRAVEL_INFO = "You just traveled and you find that...";
    String GET_AWAY_FROM_THE_FIGHT = "Ufff, was close!";

    String VICTORY = bold(green(("Congratulations, %s, you WON! Forces of evil perished and %s realm is safe again.")));
    String DEFEAT = bold(red("You died! Try harder next time!"));

    String GAME_SAVED = "Game successfully saved!";
    String GAME_LOADING_FAILED = "Could not load game state";
    String GAME_LOADED = "Welcome again, traveler! Here, let me remind you where you are";

    String ME = "Shahzad Masud";
    String CREDITS = green("Powered by:\t" + ME + "\n");

    static QuestionsToPlayer questionsToPlayer() {
        return QuestionsToPlayer.builder()
                .withMaxBonusPoints(MAX_BONUS_POINTS)
                .withNameQuestion(NAME_QUESTION)
                .withDescQuestion(DESC_QUESTION)
                .withBonusStatsDescription(BONUS_STATS_DESC)
                .withBonusStatsBonusHpQuestion(BONUS_STATS_HP_QUESTION)
                .withBonusStatsBonusDamageQuestion(BONUS_STATS_DMG_QUESTION)
                .withBonusStatsBonusDamageVariationQuestion(BONUS_STATS_DMG_VAR_QUESTION)
                .build();
    }

    static String greeting(Player player, World world) {
        return String.format(GREETING, player.getName(), player.toStringWithColors(), world.getName(), buildWorldView(world, player), buildLegend());
    }

    static String realmConfigDone(World world) {
        return String.format(REALM_CONFIRMATION_MESSAGE, world.getName());
    }
}
