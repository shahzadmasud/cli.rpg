package sm.cli.rpg.ports.outgoing.dto;

import static sm.cli.rpg.common.util.CliColorFormat.blue;
import static sm.cli.rpg.common.util.CliColorFormat.red;
import static sm.cli.rpg.common.util.CliColorFormat.yellow;
import static sm.cli.rpg.domain.game.StaticMessages.FIGHT_BONUS_ARMOR_FOR_DEFENCE;
import static sm.cli.rpg.domain.game.StaticMessages.FIGHT_FLEEING_HP_REDUCTION;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */
public enum FightMenuItem {
    ATTACK(red("Attack") + " the enemy with your weapon"),
    DEFEND(blue("Raise defences") + ", which will reduce any future damage taken in that skirmish by " + FIGHT_BONUS_ARMOR_FOR_DEFENCE),
    FLEE(yellow("Flee like a coward.") + " This will save your life, but reduce maxHp by " + FIGHT_FLEEING_HP_REDUCTION);

    private final String description;

    FightMenuItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
