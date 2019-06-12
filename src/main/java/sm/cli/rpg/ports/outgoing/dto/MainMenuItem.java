package sm.cli.rpg.ports.outgoing.dto;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */
public enum MainMenuItem {
    START("Start the game"),
    LOAD("Load a saved status of the game"),
    EXIT("Leave the game");

    private final String description;

    MainMenuItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
