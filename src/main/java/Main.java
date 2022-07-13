import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    static int numWarriorsAndWizards = new Random().nextInt(1,5);
    static int counter = 1;
    static List<Warrior> warriorsParty = new ArrayList<>();
    static List<Wizard> wizardsParty = new ArrayList<>();


    public static void main(String[] args) {

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

        //System.out.println("numWarriorsAndWizards: " + numWarriorsAndWizards);
        //createWarriorsAndWizards();

       var warrior1 = new Warrior(
                1,
                "Warrior1",
                true
        );

        var wizard1 = new Wizard(
                1,
                "Wizard1",
                true
        );

        System.out.println("                   warrior : " + warrior1);
        System.out.println("                   wizard  : " + wizard1);
        System.out.println();

        while(warrior1.isAlive() && wizard1.isAlive()) {
            wizard1.attack(warrior1);
            warrior1.attack(wizard1);
            System.out.println();
            System.out.println("counter: " + counter++);
            System.out.println("warrior 1: " + warrior1);
            System.out.println("wizard  1: " + wizard1);
            System.out.println();
            if (warrior1.getHp() <= 0) {
                warrior1.setAlive(false);
                warrior1.setHp(0);
                warriorsParty.remove(warrior1);
                break;
            }
            if (wizard1.getHp() <= 0) {
                wizard1.setAlive(false);
                wizard1.setHp(0);
                warriorsParty.remove(wizard1);
                break;
            }
        }

        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("warrior 1: " + warrior1);
        System.out.println("wizard  1: " + wizard1);
    }

    public static void createWarriorsAndWizards() {
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
            /*System.out.println(warrior);
            System.out.println(wizard);
            System.out.println();*/
        }
    }

    public static void newRoundOfBattle(Warrior warrior, Wizard wizard) {

    }
}