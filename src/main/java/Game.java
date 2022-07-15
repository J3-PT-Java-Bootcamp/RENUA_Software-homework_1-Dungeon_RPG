import battleSystem.Battle;
import battleSystem.Party;
import entities.Character;
import utilities.Keyboard.ReadFromKeyboard;
import utilities.menu.Menu;
import utilities.menu.MenuOption;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

import static utilities.errors.Errors.logError;

enum MainMenuOption {
    GENERATE_BATTLE,
    DELETE_BATTLE,
    RUN,
    QUIT,
    EXPORT_PARTY,
    SHOW_GRAVEYARD,
    SIMULATE,
    CHOOSE_FIGHT_BY_FIGHTER,
    BLUE_TEAM,
    RED_TEAM
}

public class Game {

    private Battle battle = null;
    private Battle lastBattle = null;

    public Game() {

        MainMenuOption selected;

        do {
            final MenuOption<MainMenuOption>[] menuOptions = new MenuOption[] {
                    MenuOption.create("Generate battle", MainMenuOption.GENERATE_BATTLE),
                    MenuOption.create("Run battle", MainMenuOption.RUN, battle != null),
                    MenuOption.create("Delete battle", MainMenuOption.DELETE_BATTLE, battle != null),
                    MenuOption.create("Export party", MainMenuOption.EXPORT_PARTY, battle != null),
                    MenuOption.create("Show graveyard", MainMenuOption.SHOW_GRAVEYARD, lastBattle != null),
                    MenuOption.create("Quit game", MainMenuOption.QUIT)
            };

            var mainMenu = new Menu<>(menuOptions, "Main menu", "Choose an option");

            selected = mainMenu.display();

            switch (selected) {
                case GENERATE_BATTLE: generateBattle(); break;
                case RUN:
                    this.battle.begin();
                    this.lastBattle = this.battle;
                    this.battle = null;
                    break;
                case DELETE_BATTLE: this.battle = null; break;
                case EXPORT_PARTY: this.exportCSV(); break;
                case SHOW_GRAVEYARD: showGraveyard(); break;
            }

        } while(selected != MainMenuOption.QUIT);
    }

    private void generateBattle() {
        System.out.println("Number of fighters per party:");
        this.battle = new Battle(ReadFromKeyboard.readKeyboard());
    }

    private void exportCSV() {
        final MenuOption[] menuOptions = new MenuOption[] {
                MenuOption.create(this.battle.getBlueTeam().getName(), this.battle.getBlueTeam()),
                MenuOption.create(this.battle.getRedTeam().getName(), this.battle.getRedTeam())
        };

        var menu = new Menu<Party>(menuOptions, "Export party", "Choose a party");
        var selected = menu.display();


        try {
            var file = new File(selected.getName() + ".csv");
            var fw = new FileWriter(file);

            fw.write(selected.toCSV());

            fw.close();

            System.out.println("Party exported successfully on %s".formatted(file.getAbsoluteFile()));
        } catch (Exception e) {
            logError(e.getMessage());
        }
    }

    private void showGraveyard() {


        MainMenuOption selected;

        final MenuOption<MainMenuOption>[] menuOptions = new MenuOption[] {
                MenuOption.create("Show graveyard of team " + this.lastBattle.getBlueTeam().getName(), MainMenuOption.BLUE_TEAM),
                MenuOption.create("Show graveyard of team " + this.lastBattle.getRedTeam().getName(), MainMenuOption.RED_TEAM),
        };

        var mainMenu = new Menu<>(menuOptions, "Graveyard menu", "Choose an option");

        selected = mainMenu.display();

        switch (selected) {
            case BLUE_TEAM: printGraveyard(lastBattle.getBlueTeam().getDeadCharacters()); break;
            case RED_TEAM: printGraveyard(lastBattle.getRedTeam().getDeadCharacters()); break;
        }
    }

    private void printGraveyard(LinkedList<Character> deadCharacters) {
        final StringBuffer sb = new StringBuffer();
        for (Character deadCharacter : deadCharacters) {
            sb.append(deadCharacter + "\n");
        }
        System.out.println(sb);
    }

}