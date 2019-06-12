package sm.cli.rpg.common.util;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class StringUtils {

    private StringUtils() {
    }

    public static boolean isBlank(String key) {
        return null == key || key.trim().length() == 0;
    }

}
