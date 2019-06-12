package sm.cli.rpg.domain.game;

import sm.cli.rpg.domain.character.Character;
import sm.cli.rpg.domain.character.NPC;
import sm.cli.rpg.domain.character.Player;
import sm.cli.rpg.domain.exception.ShouldNeverHappen;
import sm.cli.rpg.domain.menu.AllMenus;
import sm.cli.rpg.domain.world.location.Coordinates;
import sm.cli.rpg.domain.world.location.Location;
import sm.cli.rpg.ports.outgoing.BeforeFightMenu;
import sm.cli.rpg.ports.outgoing.FightMenu;
import sm.cli.rpg.ports.outgoing.dto.BeforeFightMenuItem;
import sm.cli.rpg.ports.outgoing.dto.FightMenuItem;

import static sm.cli.rpg.common.util.CliColorFormat.red;
import static sm.cli.rpg.common.util.CliColorFormat.yellow;
import static sm.cli.rpg.domain.game.StaticMessages.GET_AWAY_FROM_THE_FIGHT;
import static sm.cli.rpg.ports.outgoing.dto.FightMenuItem.FLEE;

/**
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 09-Jun-2019
 */

public class FightManager {
    private static final String ATTACK_MESSAGE = "%s attacked for %s damage.";

    private final FightMenu fightMenu;
    private final BeforeFightMenu beforeFightMenu;
    private final Player player;
    private final Coordinates newCoordinates;
    private final NPC npc;

    public FightManager(AllMenus allMenus, Player player, Location fightLocation) {
        this.fightMenu = allMenus.fightMenu();
        this.beforeFightMenu = allMenus.beforeFightMenu();
        this.player = player;
        this.newCoordinates = fightLocation.getCoordinates();
        this.npc = fightLocation.getNpc();
    }

    public void fight() {
        if (userWantsToFight()) {
            startTheBrawl();
        } else {
            beforeFightMenu.showMessage(GET_AWAY_FROM_THE_FIGHT);
        }
    }

    void startTheBrawl() {
        FightMenuItem fightMenuItem = fightMenu.showMenu();
        do {
            switch (fightMenuItem) {
                case ATTACK:
                    attack(player, npc);
                    if (npc.isDead()) {
                        fightMenu.showMessage(player.killed(npc));
                        player.setCoordinates(newCoordinates);
                        fightMenu.showMessage(player.currentStatus());
                        break;
                    }

                    attack(npc, player);
                    fightMenu.showMessage(player.currentStatus());
                    fightMenu.showMessage(npc.currentStatus());
                    break;
                case DEFEND:
                    fightMenu.showMessage(player.armorUp());
                    attack(npc, player);
                    fightMenu.showMessage(player.currentStatus());
                    break;
                case FLEE:
                    fightMenu.showMessage(player.flee());
                    break;
                default:
                    throw new ShouldNeverHappen();
            }

            if (npc.isAlive()){
                fightMenuItem = fightMenu.showMenu();
            }

        } while (fightGoesOn(npc, fightMenuItem));
    }

    boolean userWantsToFight() {
        BeforeFightMenuItem item = beforeFightMenu.showMenu();
        switch (item) {
            case ATTACK:
                return true;
            case FALL_BACK:
                return false;
            default:
                throw new ShouldNeverHappen();
        }
    }

    int attack(Character attacker, Character defender) {
        int damageDealt = attacker.attack(defender);
        showAttackMessage(attacker, damageDealt);
        return damageDealt;
    }

    private void showAttackMessage(Character attacker, int attackedFor) {
        fightMenu.showMessage(String.format(ATTACK_MESSAGE, yellow(attacker.getClass().getSimpleName()), red(String.valueOf(attackedFor))));
    }

    boolean fightGoesOn(NPC npc, FightMenuItem fightMenuItem) {
        return npc.isAlive() && player.isAlive() && null != fightMenuItem && !FLEE.equals(fightMenuItem);
    }
}
