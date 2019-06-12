package sm.cli.rpg.domain.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sm.cli.rpg.domain.character.NPC;
import sm.cli.rpg.domain.character.Player;
import sm.cli.rpg.domain.exception.ExplorationException;
import sm.cli.rpg.domain.exception.ShouldNeverHappen;
import sm.cli.rpg.domain.menu.AllMenus;
import sm.cli.rpg.domain.builder.AsciiArtBuilder;
import sm.cli.rpg.domain.world.World;
import sm.cli.rpg.domain.world.location.Coordinates;
import sm.cli.rpg.domain.world.location.Location;
import sm.cli.rpg.ports.exception.ConfigurationException;
import sm.cli.rpg.ports.outgoing.ExplorationMenu;
import sm.cli.rpg.ports.outgoing.GameStateProvider;
import sm.cli.rpg.ports.outgoing.dto.ExplorationMenuItem;
import sm.cli.rpg.ports.outgoing.dto.GameState;

import static sm.cli.rpg.domain.exception.Victory.victory;
import static sm.cli.rpg.domain.game.StaticMessages.GAME_SAVED;
import static sm.cli.rpg.domain.game.StaticMessages.TRAVEL_INFO;
import static sm.cli.rpg.domain.builder.LegendBuilder.buildLegend;
import static sm.cli.rpg.domain.builder.StatisticsBuilder.buildStatistics;
import static sm.cli.rpg.domain.builder.WorldViewBuilder.buildWorldView;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class ExplorationManager {
    private static final Logger LOG = LogManager.getLogger(ExplorationManager.class);

    private final GameStateProvider gameStateProvider;
    private final ExplorationMenu explorationMenu;
    private final AllMenus allMenus;
    private final World world;
    private final Player player;


    public ExplorationManager(GameStateProvider gameStateProvider, AllMenus allMenus, World world, Player player) {
        this.gameStateProvider = gameStateProvider;
        this.explorationMenu = allMenus.explorationMenu();
        this.allMenus = allMenus;
        this.world = world;
        this.player = player;
    }

    public void startExploring() throws ConfigurationException {
        LOG.traceEntry();
        ExplorationMenuItem choice = explorationMenu.showMenu();

        while (ExplorationMenuItem.EXIT != choice) {
            LOG.debug("{} selected", choice);

            switch (choice) {
                case UP:
                    travel(player.up());
                    break;
                case DOWN:
                    travel(player.down());
                    break;
                case LEFT:
                    travel(player.left());
                    break;
                case RIGHT:
                    travel(player.right());
                    break;
                case COMMANDS:
                    explorationMenu.printAllOptions();
                    break;
                case MAP:
                    showMap();
                    break;
                case LEGEND:
                    showLegend();
                    break;
                case PLAYER:
                    explorationMenu.showMessage(player.toStringWithColors());
                    break;
                case STATS:
                    explorationMenu.showStatistics(buildStatistics(world, player));
                    break;
                case SAVE:
                    saveGame();
                    break;
                case EXIT:
                    throw new ShouldNeverHappen();
                default:
                    throw new ShouldNeverHappen();
            }

            choice = explorationMenu.selectOption();
        }

        //TODO: Consider asking to save game state, before leaving.

        LOG.traceExit();
    }

    void showMap() {
        LOG.traceEntry();
        explorationMenu.showMap(buildWorldView(world, player));
        LOG.traceExit();
    }
    
    void showLegend() {
        LOG.traceEntry();
        explorationMenu.showMessage(buildLegend());
        LOG.traceExit();
    }

    void travel(Coordinates coordinates) {
        LOG.traceEntry();
        try {
            Location newLocation = world.getLocation(coordinates);
            if (newLocation.isAnyoneThere()) {
                interactWithNpc(newLocation);
            } else {
                moveToEmptySpace(newLocation);
            }
        } catch (ExplorationException e) {
            explorationMenu.showMessage(e.getMessage());
        }
        showMap();
        LOG.traceExit();
    }

    void interactWithNpc(Location newLocation) {
        LOG.traceEntry();
        NPC npc = newLocation.getNpc();
        explorationMenu.showMessage(AsciiArtBuilder.loadIfPossible(npc.getName()));
        explorationMenu.showMessage(npc.toStringWithColors());
        explorationMenu.showMessage(npc.greeting());
        if (npc.isEnemy()) {
            fight(newLocation);

            if (world.allEnemiesDead()){
                victory(world, player);
            }
        }
        LOG.traceExit();
    }

    void fight(Location newLocation) {
        new FightManager(allMenus, player, newLocation).fight();
    }

    void moveToEmptySpace(Location newLocation) {
        LOG.traceEntry();
        player.setCoordinates(newLocation.getCoordinates());
        explorationMenu.showMessage(TRAVEL_INFO + newLocation.desc());
        LOG.traceExit();
    }

    void saveGame() throws ConfigurationException {
        LOG.traceEntry();
        gameStateProvider.saveGame(new GameState(world, player));
        explorationMenu.showMessage(GAME_SAVED);
        LOG.traceExit();
    }

}
