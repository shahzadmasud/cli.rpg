package sm.cli.rpg.domain.character;

import sm.cli.rpg.common.util.MapStringBuilder;
import sm.cli.rpg.domain.exception.PlayerDied;
import sm.cli.rpg.domain.game.StaticMessages;
import sm.cli.rpg.domain.world.location.Coordinates;
import sm.cli.rpg.ports.outgoing.dto.PlayerConfiguration;

import static sm.cli.rpg.common.util.CliColorFormat.red;
import static sm.cli.rpg.domain.character.PlayerBaseStatistics.BASE_DMG;
import static sm.cli.rpg.domain.character.PlayerBaseStatistics.BASE_DMG_VARIATION;
import static sm.cli.rpg.domain.character.PlayerBaseStatistics.BASE_HP;
import static sm.cli.rpg.domain.game.StaticMessages.FIGHT_FLEEING_HP_REDUCTION;

/**
 * A playable character, our protagonist, our avatar in this magical world
 * has some more characteristics to be set than NPC, user will have to provide some data in here
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class Player extends Character {
    private Coordinates coordinates;
    private Experience experience;
    private int armorUpTimesUsed;

    public Player(String name, String description, int hpBonus, int damageBonus, int damageVariationBonus, Coordinates initialCoordinates) {
        super(name, description, BASE_HP + hpBonus, BASE_DMG + damageBonus, BASE_DMG_VARIATION + damageVariationBonus);
        this.coordinates = initialCoordinates;

        this.experience = new Experience();
        this.armorUpTimesUsed = 0;
    }

    public Player(PlayerConfiguration conf, Coordinates initialCoordinates) {
        this(conf.getName(), conf.getDesc(), conf.getHpBonus(), conf.getDamageBonus(), conf.getDamageVariationBonus(), initialCoordinates);
    }

    public Coordinates up() {
        return coordinates.up();
    }

    public Coordinates down() {
        return coordinates.down();
    }

    public Coordinates left() {
        return coordinates.left();
    }

    public Coordinates right() {
        return coordinates.right();
    }

    // TODO: Refactor
    public String armorUp() {
        if (armorUpTimesUsed == experience.getLevel()) {
            return red("Cannot armor up more times than what your current level is. Turn lost");
        } else if (armorUpTimesUsed == experience.getLevel() - 1) {
            armorUpTimesUsed++;
            return "Your hardiness raises and your opponent attacks." + red("This was the last time you could use armor up during this fight");
        } else {
            armorUpTimesUsed++;
            return "Your hardiness raises and your opponent attacks.";
        }
    }

    public String killed(NPC npc) {
        ExperienceStatus experienceStatus = experience.addKillReward(npc.getExpReward());
        switch (experienceStatus) {
            case DIDNT_LEVEL_UP:
                break;
            case LEVELED_UP:
                playerLevelUp();
                break;
            case DOUBLE_LEVELED_UP:
                playerLevelUp();
                playerLevelUp();
                break;
        }

        resetFightStatus();
        return String.format(experienceStatus.toString(), npc.getExpReward());
    }

    private void playerLevelUp() {
        maxHp += FIGHT_FLEEING_HP_REDUCTION;
        damage += 2;
        damageVariation++;
    }

    public String flee() {
        maxHp -= FIGHT_FLEEING_HP_REDUCTION;
        resetFightStatus();
        return "You fled from the battlefield and received a penalty.";
    }

    private void resetFightStatus() {
        armorUpTimesUsed = 0;
        currentHp = maxHp;
    }

    @Override
    protected int calculateDamageReceived(int damage) {
        int damageReceived = damage - calculateDamageMitigation();
        if (damageReceived < 0) {
            return 0;
        } else {
            return damageReceived;
        }
    }

    @Override
    public void die() {
        super.die();
        throw new PlayerDied();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Experience getExperience() {
        return experience;
    }

    public int calculateDamageMitigation() {
        return armorUpTimesUsed * StaticMessages.FIGHT_BONUS_ARMOR_FOR_DEFENCE;
    }

    @Override
    public String toString() {
        return super.toString() + toStringCommon().build();
    }

    @Override
    public String toStringWithColors() {
        return super.toStringWithColors() + toStringCommon().build(true);
    }

    private MapStringBuilder toStringCommon() {
        return MapStringBuilder.fieldsWithNewlinesAndTabs(this)
                .append("damageMitigation", calculateDamageMitigation())
                .append("coordinates", coordinates.toString())
                .append("experience", experience.toString());
    }
}
