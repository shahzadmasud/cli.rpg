package sm.cli.rpg.adapters.util.io;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public abstract class IOBase {

    protected static File createFileIfDoesNotExist(String absolutePath) throws IOException {
        File file = new File(absolutePath);
        if (file.exists()) {
            return file;
        }

        file.getParentFile().mkdirs();
        file.createNewFile();
        return file;
    }
}
