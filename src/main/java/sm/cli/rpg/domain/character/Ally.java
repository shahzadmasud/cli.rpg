package sm.cli.rpg.domain.character;

import sm.cli.rpg.common.util.Color;
import sm.cli.rpg.domain.world.location.LocationType;

/**
 * TODO: Hard coding the ally character, will be using database or configuration 
 * 
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class Ally extends NPC {
    public Ally(String name, String description, String greeting, int maxHp, int damage, int damageVariation) {
        super(name, description, greeting, maxHp, damage, damageVariation);
    }

    @Override
    protected LocationType locationTypeSpecificForNpc() {
        return LocationType.ALLY;
    }

    @Override
    protected Color greetingColor() {
        return Color.GREEN;
    }
}
