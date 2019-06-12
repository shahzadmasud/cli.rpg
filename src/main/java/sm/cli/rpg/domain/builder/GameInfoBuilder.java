package sm.cli.rpg.domain.builder;

import sm.cli.rpg.domain.character.Player;
import sm.cli.rpg.domain.world.World;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

abstract class GameInfoBuilder<BuilderType extends GameInfoBuilder<BuilderType>> extends OutputBuilderBase {
    protected World world;
    protected Player player;

    public BuilderType withWorld(World world) {
        this.world = world;
        return that();
    }

    public BuilderType withPlayer(Player player) {
        this.player = player;
        return that();
    }

    protected abstract BuilderType that();

    public String build() {
        if (null == world) {
            return errorOccurred();
        }

        return buildInner();
    }

    protected String errorOccurred() {
        return "Cannot build request information, an error occurred";
    }

    protected abstract String buildInner();
}
