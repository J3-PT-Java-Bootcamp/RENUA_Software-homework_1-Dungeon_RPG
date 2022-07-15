import battleSystem.Battle;
import battleSystem.Party;
import com.github.javafaker.Faker;
import entities.Character;
import entities.Warrior;
import entities.Wizard;
import utilities.Keyboard.ReadFromKeyboard;
import utilities.menu.Menu;
import utilities.menu.MenuOption;

import java.io.*;
import java.util.ArrayList;

import static utilities.errors.Errors.logError;

enum MainMenuOption {
    GENERATE_BATTLE,
    DELETE_BATTLE,
    RUN,
    QUIT,
    EXPORT_PARTY,
    IMPORT_PARTY,
    RANDOM_PARTY,
    RANDOM_BATTLE
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

        MainMenuOption selected = this.getMainMenuOption(true);

        switch (selected) {

            case IMPORT_PARTY:
            case RANDOM_PARTY:
                Party blueTeam = null;
                Party redTeam = null;

                do {

                    if (blueTeam != null) selected = this.getMainMenuOption(false);

                    var position = blueTeam == null ? "first" : "second";
                    switch (selected) {
                        case IMPORT_PARTY:
                            if (blueTeam == null) blueTeam = importCSV(position);
                            else redTeam = importCSV(position);
                            break;
                        case RANDOM_PARTY:
                            if (blueTeam == null) blueTeam = randomParty(position);
                            else redTeam = randomParty(position);
                            break;
                    }

                } while(blueTeam == null || redTeam == null);

                this.battle = new Battle(blueTeam, redTeam);

                break;
            case RANDOM_BATTLE: this.randomBattle(); break;
        }
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

    private Party importCSV(String position) {

        System.out.println("File name (Without CSV extension) for " + position + " party:");
        String fileName = ReadFromKeyboard.readStringKeyboard();
        ArrayList<Character> characters = new ArrayList<>();

        try {
            File file=new File(fileName + ".csv");    //creates a new file instance
            FileReader fr=new FileReader(file);   //reads the file
            BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
            String line;

            br.readLine(); //skip first line

            while((line=br.readLine())!=null)
            {
                var lineSplit = line.split(";");

                if (lineSplit[0].equals("Wizard")) {
                    characters.add(new Wizard(lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[5])));
                } else {
                    characters.add(new Warrior(lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3]), Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[5])));
                }
            }

            fr.close();    //closes the stream and release the resources
        } catch (Exception e) {
            logError(e.getMessage());
        }

        return new Party(fileName, characters);
    }

    private Party randomParty(String position) {
        System.out.println("Number of combatants for the" + position + " match:");
        int numberOfFighters = ReadFromKeyboard.readIntKeyboard();

        return new Party(numberOfFighters,  new Faker().harryPotter().location());
    }

    private void randomBattle() {
        System.out.println("Number of fighters per party:");
        this.battle = new Battle(ReadFromKeyboard.readIntKeyboard());
    }

    private MainMenuOption getMainMenuOption(Boolean randomBattleAveilable) {

        final MenuOption<MainMenuOption>[] menuOptions = new MenuOption[] {
                MenuOption.create("Import party", MainMenuOption.IMPORT_PARTY),
                MenuOption.create("Random party", MainMenuOption.RANDOM_PARTY),
                MenuOption.create("Random Battle", MainMenuOption.RANDOM_BATTLE, randomBattleAveilable),
        };

        var mainMenu = new Menu<>(menuOptions, "Main menu", "Choose an option");

        return mainMenu.display();
    }
}