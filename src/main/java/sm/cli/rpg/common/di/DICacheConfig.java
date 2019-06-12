/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.cli.rpg.common.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

/**
 * Spring DI
 *
 * @author shahzadmasud
 * @version 1.0
 * @since 11-Jun-2019
 */

@Configuration
public class DICacheConfig {

    private OutputWriter writer = null;
    private InputParser reader = null;

    @Bean
    public OutputWriter outputWriter() {
        if (writer == null) {
            writer = new OutputWriter(System.out);
        }
        return writer;
    }

    @Bean
    public InputParser inputParser() {
        if (reader == null) {
            reader = new InputParser(outputWriter(), System.in);
        }
        return reader;
    }

    @Bean
    public RealmConfigurationProvider realmConfigurationProvider() {
        return new SerializedRealmConfigurationProvider();
    }

    @Bean
    public GameStateProvider gameStateProvider() {
        return new SerializedGameStateProvider();
    }

    @Bean
    public PlayerConfigurationMenu playerConfigurationMenu() {
        return new CliPlayerConfigurationMenu(inputParser(), outputWriter());
    }

    @Bean
    public WorldConfigurationMenu worldConfigurationMenu() {
        return new CliWorldConfigurationMenu(inputParser(), outputWriter());
    }

    @Bean
    public MainMenu mainMenu() {
        return new CliMainMenu(inputParser(), outputWriter());
    }

    @Bean
    public ExplorationMenu explorationMenu() {
        return new CliExplorationMenu(reader, writer);
    }

    @Bean
    public FightMenu fightMenu(){
        return new CliFightMenu(reader, writer) ;
    }
    
    @Bean
    public BeforeFightMenu beforeFightMenu(){
        return new CliBeforeFightMenu(reader, writer) ;
    }
    
    @Bean 
    public AllMenus allMenus() {
        return new AllMenus(
                mainMenu(), 
                playerConfigurationMenu(), 
                worldConfigurationMenu(), 
                explorationMenu(), 
                beforeFightMenu(), 
                fightMenu()); 
    }

    @Bean
    public MainMenuManager managers() {
        return new MainMenuManager(realmConfigurationProvider(), allMenus(), gameStateProvider());
        
    }

}
