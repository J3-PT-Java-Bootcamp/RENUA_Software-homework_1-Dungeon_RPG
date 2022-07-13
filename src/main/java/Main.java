import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static int counter = 1;
    static List<Warrior> warriorsParty = new ArrayList<>();
    static List<Wizard> wizardsParty = new ArrayList<>();

    public static void main(String[] args) {

        int randomNumWarriorsAndWizards = new Random().nextInt(4,8);

        //Requirement 1
        /*var mainMenu = new Menu<Runnable>(new MenuOption[] {
            MenuOption.create("Crear Warriors y Wizards", (Runnable) () -> {
                //System.out.println("Selecciona el número de Warriors Y Wizards que desea:");
                int num = ReadFromKeyboard.readKeyboard();
                createWarriorsAndWizards(num);
            }),
            //MenuOption.create("Execute round", )
        }, "Main menu", "Choose an option:");

        var selected = mainMenu.display();

        selected.run();*/

        //Requirement 2
        System.out.println("numWarriorsAndWizards: " + randomNumWarriorsAndWizards);
        createWarriorsAndWizards(randomNumWarriorsAndWizards);
        System.out.println("warriorsParty.size(): " + warriorsParty.size());

        while(warriorsParty.size() > 0  ||  wizardsParty.size() > 0) {

            int randomPosOfWarriorsParty = new Random().nextInt(0, warriorsParty.size());
            int randomPosOfWizardsParty = new Random().nextInt(0, wizardsParty.size());

            Warrior warrior = warriorsParty.get(randomPosOfWarriorsParty);
            Wizard wizard = wizardsParty.get(randomPosOfWizardsParty);

            newRoundOfBattle(warrior, wizard);

            int currentNumWarriors = warriorsParty.size();
            int currentNumWizards = wizardsParty.size();

            System.out.println();
            System.out.println("currentNumWarriors: " + currentNumWarriors);
            System.out.println("currentNumWizards: " + currentNumWizards);
            System.out.println();

            if (warriorsParty.size() == 0  || wizardsParty.size() == 0) break;
        }

        System.out.println("\n\n" + "--------------------------------------------------------------");

        if (warriorsParty.size() == 0) System.out.println("WINNER TEAM: Wizards!!!" + "\n");
        else System.out.println("WINNER TEAM: Warriors!!!" + "\n");

        System.out.println("Initial number of Warriors: " + randomNumWarriorsAndWizards);
        System.out.println("Initial number of Wizards: " + randomNumWarriorsAndWizards);
        System.out.println("Total number of Warriors & Wizards: " + randomNumWarriorsAndWizards*2 + "\n");

        System.out.println(
                "Warriors: " + "\n" +
                        "Alive warriors: " + warriorsParty.size() + "\n" +
                        "Array of warriors: " + warriorsParty + "\n"
        );

        System.out.println(
                "Wizards: " + "\n" +
                        "Alive wizards: " + wizardsParty.size() + "\n" +
                        "Array of wizards: " + wizardsParty + "\n"
        );
    }

    public static void createWarriorsAndWizards(int numWarriorsAndWizards) {
        for(int i = 0; i < numWarriorsAndWizards; i++) {
            var warrior = new Warrior(
                    (i + 1),
                    "Warrior " + (i + 1),
                    true
            );
            var wizard = new Wizard(
                    (i + 1),
                    "Wizard " + (i + 1),
                    true
            );
            warriorsParty.add(warrior);
            wizardsParty.add(wizard);
        }
    }

    public static void newRoundOfBattle(Warrior warrior, Wizard wizard) {
        while(warrior.isAlive() && wizard.isAlive()) {
            wizard.attack(warrior);
            warrior.attack(wizard);
            System.out.println();
            System.out.println("counter: " + counter++);
            System.out.println("warrior 1: " + warrior);
            System.out.println("wizard  1: " + wizard);
            System.out.println();
            if (warrior.getHp() <= 0) {
                warrior.setAlive(false);
                warrior.setHp(0);
                warriorsParty.remove(warrior);
                break;
            }
            if (wizard.getHp() <= 0) {
                wizard.setAlive(false);
                wizard.setHp(0);
                wizardsParty.remove(wizard);
                break;
            }
        }
    }
}