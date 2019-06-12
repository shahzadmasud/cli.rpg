package sm.cli.rpg.domain.builder;

import sm.cli.rpg.common.util.MapStringBuilder;
import sm.cli.rpg.domain.character.Player;
import sm.cli.rpg.domain.world.World;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class StatisticsBuilder extends GameInfoBuilder<StatisticsBuilder> {
    public static String buildStatistics(World world, Player player) {
        return statistics().withWorld(world).withPlayer(player).build();
    }

    public static StatisticsBuilder statistics() {
        return new StatisticsBuilder();
    }

    @Override
    protected StatisticsBuilder that() {
        return this;
    }

    @Override
    protected String buildInner() {
        return MapStringBuilder.fieldsWithNewlinesAndTabs(this)
                .append("enemies left", world.aliveEnemiesLeft() + "/" + world.getEnemies().size())
                .append("", player.toStringWithColors())
                .build();
    }
}
