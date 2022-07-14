import battleSystem.Battle;
import utilities.Keyboard.ReadFromKeyboard;
import utilities.menu.Menu;
import utilities.menu.MenuOption;

enum MainMenuOption {
    GENERATE_BATTLE,
    RUN,
    QUIT,
}

public class Game {

    private Battle battle = null;

    public Game() {

        final MenuOption<MainMenuOption>[] menuOptions = new MenuOption[] {
            MenuOption.create("Generate battle", MainMenuOption.GENERATE_BATTLE),
            MenuOption.create("Run battle", MainMenuOption.RUN, battle != null)
        };

        var mainMenu = new Menu<MainMenuOption>(menuOptions, "Main menu","Choose an option");
        MainMenuOption selected;

        do {
            selected = mainMenu.display();

            switch (selected) {
                case GENERATE_BATTLE -> generateBattle();
                case RUN -> this.battle.begin();
            }

        } while(selected != MainMenuOption.QUIT);
    }

    private void generateBattle() {
        this.battle = new Battle(ReadFromKeyboard.readNumber(5, 10));
    }
}