package sm.cli.rpg.ports.outgoing.dto;

import sm.cli.rpg.common.util.MapStringBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */
public class RealmConfiguration implements Serializable {
    private final String name;
    private final int realmSize;
    private final List<EnemyConfiguration> enemies;

    public RealmConfiguration(String name, int realmSize, List<EnemyConfiguration> enemies) {
        this.name = name;
        this.realmSize = realmSize;
        this.enemies = enemies;
    }

    public String getName() {
        return name;
    }

    public int getRealmSize() {
        return realmSize;
    }

    public List<EnemyConfiguration> getEnemyConfiguration() {
        return enemies;
    }

    @Override
    public String toString() {
        return name;
    }

    public String detailedToString() {
        return MapStringBuilder.defaultBuilder(this)
                .append("name", name)
                .append("realmSize", realmSize)
                .append("enemies", buildEnemiesToString())
                .buildWithClassName();
    }

    String buildEnemiesToString() {
        if (null == enemies || enemies.isEmpty()) {
            return "no enemies defined";
        }

        return enemies.stream()
                .map(enemy -> "\n\t" + enemy.toString())
                .collect(Collectors.joining());
    }

    public String shortDesc() {
        return String.format("%s where hero slashes through enemies on a %dx%d map", name, realmSize, realmSize);
    }
}
