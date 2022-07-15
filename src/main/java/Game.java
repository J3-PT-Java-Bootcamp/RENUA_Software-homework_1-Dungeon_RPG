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
                    MenuOption.create("Quit game", MainMenuOption.QUIT)
            };

            var mainMenu = new Menu<>(menuOptions, "Main menu", "Choose an option");

            selected = mainMenu.display();

            switch (selected) {
                case GENERATE_BATTLE: generateBattle(); break;
                case RUN:
                    this.battle.begin();
                    this.battle = null;
                    break;
                case DELETE_BATTLE: this.battle = null; break;
                case EXPORT_PARTY: this.exportCSV(); break;
            }

        } while(selected != MainMenuOption.QUIT);
    }

    private void generateBattle() {
        // TODO: change numbers (min and max party size)
        this.battle = new Battle(ReadFromKeyboard.readNumber(5, 10));
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
}