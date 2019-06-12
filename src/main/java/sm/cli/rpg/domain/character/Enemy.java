package sm.cli.rpg.domain.character;

import sm.cli.rpg.common.util.Color;
import sm.cli.rpg.domain.world.location.LocationType;
import sm.cli.rpg.ports.outgoing.dto.EnemyConfiguration;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class Enemy extends NPC {
    public Enemy(String name, String description, String greeting, int maxHp, int damage, int damageVariation) {
        super(name, description, greeting, maxHp, damage, damageVariation);
    }

    public Enemy(EnemyConfiguration conf) {
        this(conf.getName(), conf.getDescription(), conf.getGreeting(), conf.getMaxHp(), conf.getDamage(), conf.getDamageVariation());
    }

    @Override
    protected LocationType locationTypeSpecificForNpc() {
        return LocationType.ENEMY;
    }

    @Override
    protected Color greetingColor() {
        return Color.RED;
    }
}
