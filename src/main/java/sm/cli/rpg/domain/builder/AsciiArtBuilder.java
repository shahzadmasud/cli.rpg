package sm.cli.rpg.domain.builder;

import sm.cli.rpg.adapters.util.io.InternalIO;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class AsciiArtBuilder {
    public static final String ASCII_ART_FOLDER = "ascii_art";

    public static String loadIfPossible(String characterName) {
        try {
            return InternalIO.readAsString(ASCII_ART_FOLDER, characterName);
        } catch (NullPointerException e) {
            return "";
        }
    }

    private AsciiArtBuilder() {
    }
}
