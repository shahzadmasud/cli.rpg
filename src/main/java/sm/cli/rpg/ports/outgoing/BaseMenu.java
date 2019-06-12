package sm.cli.rpg.ports.outgoing;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public interface BaseMenu<Type> {
    void showMessage(String message);

    void printAllOptions(String message);

    void printAllOptions();

    Type selectOption();

    Type showMenu();
}
