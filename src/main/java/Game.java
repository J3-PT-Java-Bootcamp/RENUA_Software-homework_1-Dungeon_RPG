import battleSystem.Battle;
import battleSystem.Party;
import utilities.Keyboard.ReadFromKeyboard;
import utilities.menu.Menu;
import utilities.menu.MenuOption;

import java.io.File;
import java.io.FileWriter;

import static utilities.errors.Errors.logError;

enum MainMenuOption {
    GENERATE_BATTLE,
    DELETE_BATTLE,
    RUN,
    QUIT,
    EXPORT_PARTY,
    SHOW_GRAVEYARD,
    SIMULATE,
    CHOOSE_FIGHT_BY_FIGHTER
}

public class Game {

    private Battle battle = null;

    public Game() {

        MainMenuOption selected;

        do {
            final MenuOption<MainMenuOption>[] menuOptions = new MenuOption[] {
                    MenuOption.create("Generate battle", MainMenuOption.GENERATE_BATTLE),
                    MenuOption.create("Run battle", MainMenuOption.RUN, battle != null),
                    MenuOption.create("Delete battle", MainMenuOption.DELETE_BATTLE, battle != null),
                    MenuOption.create("Export party", MainMenuOption.EXPORT_PARTY, battle != null),
                    MenuOption.create("Show graveyard", MainMenuOption.SHOW_GRAVEYARD, battle != null),
                    MenuOption.create("Quit game", MainMenuOption.QUIT)
            };

            var mainMenu = new Menu<>(menuOptions, "Main menu", "Choose an option");

            selected = mainMenu.display();

            switch (selected) {
                case GENERATE_BATTLE: generateBattle(); break;
                case RUN:
                    this.runBattleOptions();
                    this.battle = null;
                    break;
                case DELETE_BATTLE: this.battle = null; break;
                case EXPORT_PARTY: this.exportCSV(); break;
                case SHOW_GRAVEYARD:
                    // TODO: Call method to show graveyard;
                    System.out.println("This option is not available for now");
                    break;
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

    private void runBattleOptions() {

        MainMenuOption selected;

        final MenuOption<MainMenuOption>[] menuOptions = new MenuOption[] {
                MenuOption.create("Simulate battle", MainMenuOption.SIMULATE),
                MenuOption.create("Run battle choosing fighters", MainMenuOption.CHOOSE_FIGHT_BY_FIGHTER, battle != null),
        };

        var mainMenu = new Menu<>(menuOptions, "Main menu", "Choose an option");

        selected = mainMenu.display();

        switch (selected) {
            case SIMULATE: this.battle.begin(); break;
            case CHOOSE_FIGHT_BY_FIGHTER:
                // TODO: IMPLEMENT THE SELECTION METHOD
                System.out.println("This option is not available for now");
                break;
        }
    }

}