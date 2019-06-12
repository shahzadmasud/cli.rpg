package sm.cli.rpg.common.di;

import sm.cli.rpg.adapters.outgoing.CliBeforeFightMenu;
import sm.cli.rpg.adapters.outgoing.CliExplorationMenu;
import sm.cli.rpg.adapters.outgoing.CliFightMenu;
import sm.cli.rpg.adapters.outgoing.CliMainMenu;
import sm.cli.rpg.adapters.outgoing.CliPlayerConfigurationMenu;
import sm.cli.rpg.adapters.outgoing.CliWorldConfigurationMenu;
import sm.cli.rpg.adapters.outgoing.SerializedGameStateProvider;
import sm.cli.rpg.adapters.outgoing.SerializedRealmConfigurationProvider;
import sm.cli.rpg.adapters.util.io.InputParser;
import sm.cli.rpg.adapters.util.io.OutputWriter;
import sm.cli.rpg.domain.exception.DIException;
import sm.cli.rpg.domain.menu.AllMenus;
import sm.cli.rpg.domain.menu.MainMenuManager;
import sm.cli.rpg.ports.outgoing.BeforeFightMenu;
import sm.cli.rpg.ports.outgoing.ExplorationMenu;
import sm.cli.rpg.ports.outgoing.FightMenu;
import sm.cli.rpg.ports.outgoing.GameStateProvider;
import sm.cli.rpg.ports.outgoing.MainMenu;
import sm.cli.rpg.ports.outgoing.PlayerConfigurationMenu;
import sm.cli.rpg.ports.outgoing.RealmConfigurationProvider;
import sm.cli.rpg.ports.outgoing.WorldConfigurationMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * Due to time shortage, using DI Cache - will later on move to Spring DI
 * 
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class DICacheOld {
    private static final Map<Class, Object> CACHE = new HashMap<>();

    static {
        consoleIo();
        configurationProviders();
        loadSaveGame();
        menus();
        managers();
    }

    private DICacheOld() {
    }

    private static void consoleIo() {
        OutputWriter outputWriter = new OutputWriter(System.out);
        extendContext(OutputWriter.class, outputWriter);

        InputParser inputParser = new InputParser(outputWriter, System.in);
        extendContext(InputParser.class, inputParser);
    }

    private static void configurationProviders() {
        RealmConfigurationProvider realmConfigurationProvider = new SerializedRealmConfigurationProvider();
        extendContext(RealmConfigurationProvider.class, realmConfigurationProvider);
    }

    private static void loadSaveGame() {
        GameStateProvider gameStateProvider = new SerializedGameStateProvider();
        extendContext(GameStateProvider.class, gameStateProvider);
    }

    private static void menus() {
        OutputWriter outputWriter = getBean(OutputWriter.class);
        InputParser inputParser = getBean(InputParser.class);


        PlayerConfigurationMenu playerConfigurationMenu = new CliPlayerConfigurationMenu(inputParser, outputWriter);
        extendContext(PlayerConfigurationMenu.class, playerConfigurationMenu);

        WorldConfigurationMenu worldConfigurationMenu = new CliWorldConfigurationMenu(inputParser, outputWriter);
        extendContext(WorldConfigurationMenu.class, worldConfigurationMenu);

        MainMenu mainMenu = new CliMainMenu(inputParser, outputWriter);
        extendContext(MainMenu.class, mainMenu);

        ExplorationMenu explorationMenu = new CliExplorationMenu(inputParser, outputWriter);
        extendContext(ExplorationMenu.class, explorationMenu);

        FightMenu fightMenu = new CliFightMenu(inputParser, outputWriter);
        extendContext(FightMenu.class, fightMenu);

        BeforeFightMenu beforeFightMenu = new CliBeforeFightMenu(inputParser, outputWriter);
        extendContext(BeforeFightMenu.class, beforeFightMenu);

        AllMenus allMenus = new AllMenus(mainMenu, playerConfigurationMenu, worldConfigurationMenu, explorationMenu, beforeFightMenu, fightMenu);
        extendContext(AllMenus.class, allMenus);
    }

    private static void managers() {
        AllMenus allMenus = getBean(AllMenus.class);
        GameStateProvider gameStateProvider = getBean(GameStateProvider.class);
        RealmConfigurationProvider realmConfigurationProvider = getBean(RealmConfigurationProvider.class);

        MainMenuManager mainMenuManager = new MainMenuManager(realmConfigurationProvider, allMenus, gameStateProvider);
        extendContext(MainMenuManager.class, mainMenuManager);
    }

    private static void extendContext(Class type, Object impl) {
        CACHE.put(impl.getClass(), impl);
        CACHE.put(type, impl);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        T bean;

        try {
            bean = (T) CACHE.get(clazz);
        } catch (Throwable t) {
            throw new DIException(t);
        }

        if (null == bean) {
            throw new DIException();
        }

        return bean;
    }
}
