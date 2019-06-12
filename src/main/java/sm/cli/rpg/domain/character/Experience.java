package sm.cli.rpg.domain.character;

import sm.cli.rpg.common.util.MapStringBuilder;

import java.io.Serializable;

import static sm.cli.rpg.domain.game.StaticMessages.EXP_TO_FIRST_LEVEL_UP;
import static sm.cli.rpg.domain.game.StaticMessages.NEXT_LEVEL_EXP_MULTIPLIER;

/**
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class Experience implements Serializable {
    private int level;
    private int currentExp;
    private int lastLevelUpExp;

    public Experience() {
        this.currentExp = 0;
        this.level = 1;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public int getLevel() {
        return level;
    }

    public ExperienceStatus addKillReward(int expReward) {
        currentExp += expReward;
        int startingLevel = level;
        while (currentExp >= getExpRequiredToLevelUp()) {
            levelUp();
        }

        return ExperienceStatus.fromLevelDiff(level - startingLevel);

    }

    int getExpRequiredToLevelUp() {
        if (1 == level) {
            return EXP_TO_FIRST_LEVEL_UP;
        } else {
            return (int) (lastLevelUpExp + lastLevelUpExp * NEXT_LEVEL_EXP_MULTIPLIER);
        }
    }

    private void levelUp() {
        lastLevelUpExp = getExpRequiredToLevelUp();
        level++;
    }

    @Override
    public String toString() {
        return MapStringBuilder.defaultBuilderWithoutBrackets(this)
                .append("level", level)
                .append("currentExp", currentExp + "/" + getExpRequiredToLevelUp())
                .build();
    }
}
