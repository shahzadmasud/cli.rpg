package sm.cli.rpg.adapters.util.menu;

import sm.cli.rpg.adapters.util.io.InputParser;
import sm.cli.rpg.adapters.util.io.OutputWriter;

import java.util.Arrays;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class CliEnumMenuBase<ItemType extends Enum> extends CliMenuBase<ItemType> {
    protected CliEnumMenuBase(InputParser inputParser, OutputWriter outputWriter, ItemType[] menuItems) {
        super(inputParser, outputWriter, Arrays.asList(menuItems));
    }
}
