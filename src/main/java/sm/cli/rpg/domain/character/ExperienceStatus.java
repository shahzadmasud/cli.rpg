package sm.cli.rpg.domain.character;

import static sm.cli.rpg.domain.game.StaticMessages.ENEMY_DEFEATED;
import static sm.cli.rpg.domain.game.StaticMessages.LEVEL_UP;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public enum ExperienceStatus {
    DIDNT_LEVEL_UP(ENEMY_DEFEATED),
    LEVELED_UP(ENEMY_DEFEATED + LEVEL_UP),
    DOUBLE_LEVELED_UP(ENEMY_DEFEATED + LEVEL_UP + "Twice!"),
    TRIPLE_LEVELED_UP(ENEMY_DEFEATED + LEVEL_UP + "Three times!");

    private final String desc;

    private ExperienceStatus(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }

    public static ExperienceStatus fromLevelDiff(int levelDiff) {
        for (ExperienceStatus experienceStatus : ExperienceStatus.values()) {
            if (experienceStatus.ordinal() == levelDiff) {
                return experienceStatus;
            }
        }

        return DIDNT_LEVEL_UP;
    }
}
