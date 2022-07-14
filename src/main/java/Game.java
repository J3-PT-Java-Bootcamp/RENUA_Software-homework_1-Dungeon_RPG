import battleSystem.Battle;
import utilities.Keyboard.ReadFromKeyboard;
import utilities.menu.Menu;
import utilities.menu.MenuOption;

enum MainMenuOption {
    GENERATE_BATTLE,
    DELETE_BATTLE,
    RUN,
    QUIT,
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
                    MenuOption.create("Quit game", MainMenuOption.QUIT)
            };

            var mainMenu = new Menu<>(menuOptions, "Main menu", "Choose an option");

            selected = mainMenu.display();

            switch (selected) {
                case GENERATE_BATTLE -> generateBattle();
                case RUN -> this.battle.begin();
                case DELETE_BATTLE -> this.battle = null;
            }

        } while(selected != MainMenuOption.QUIT);
    }

    private void generateBattle() {
        // TODO: change numbers (min and max party size)
        this.battle = new Battle(ReadFromKeyboard.readNumber(5, 10));
    }
}