import com.github.javafaker.Faker;
import utilities.Keyboard.ReadFromKeyboard;
import utilities.menu.Menu;
import utilities.menu.MenuOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static List<Warrior> warriorsParty = new ArrayList<>();
    static List<Wizard> wizardsParty = new ArrayList<>();
    static List<Character> graveyard = new ArrayList<>();

    public static void main(String[] args) {

        int randomNumWarriorsAndWizards = new Random().nextInt(4,8);
        createWarriorsAndWizards(randomNumWarriorsAndWizards);



        System.out.println("Initial number of Warriors: " + randomNumWarriorsAndWizards);
        System.out.println("Initial number of Wizards: " + randomNumWarriorsAndWizards);
        System.out.println("Total number of Warriors & Wizards: " + randomNumWarriorsAndWizards*2 + "\n");

        //Requirement 1
       /* var mainMenu = new Menu<Runnable>(new MenuOption[] {
            MenuOption.create("Crear Warriors y Wizards", (Runnable) () -> {
                System.out.println("Selecciona el número de Warriors Y Wizards que desea:");
                int num = ReadFromKeyboard.readKeyboard();
                createWarriorsAndWizards(num);
            }),
            //MenuOption.create("Execute round", )
        }, "Main menu", "Choose an option:");

        var selected = mainMenu.display();

        selected.run();

        //Requirement 2
       */
        int counter = 0;
        while(warriorsParty.size() > 0  &&  wizardsParty.size() > 0) {
            counter ++;
            int randomPosOfWarriorsParty = new Random().nextInt(0, warriorsParty.size());
            int randomPosOfWizardsParty = new Random().nextInt(0, wizardsParty.size());

            Warrior warrior = warriorsParty.get(randomPosOfWarriorsParty);
            Wizard wizard = wizardsParty.get(randomPosOfWizardsParty);

            System.out.println("------------------------------");
            System.out.println("BATTLE " + counter);
            System.out.println();
            System.out.println("In this battle the fighters are:");
            System.out.println(warrior);
            System.out.println(wizard);
            System.out.println();

            newRoundOfBattle(warrior, wizard);



            System.out.println("------------------------------");

            System.out.println();
            System.out.println("Warriors alive: " + warriorsParty.size());
            System.out.println("Wizards alive: " + wizardsParty.size());
            System.out.println();
        }
        System.out.println("************************************");
        System.out.println();

        if (warriorsParty.size() == 0) System.out.println("WINNER TEAM: Wizards!!!" + "\n");
        else System.out.println("WINNER TEAM: Warriors!!!" + "\n");
        System.out.println();
        System.out.println("SURVIVORS");
        System.out.println();
        int survivorNumber = 0;
        while (!warriorsParty.isEmpty() && warriorsParty.size() > survivorNumber ) {
            System.out.println(warriorsParty.get(survivorNumber));
            survivorNumber++;
        }
        // I use the same variable as an index because only one while loop will initialize (survivorNumber)
        while (!wizardsParty.isEmpty() && wizardsParty.size() > survivorNumber) {
            System.out.println(wizardsParty.get(survivorNumber));
            survivorNumber++;
        }
    }
    public static void createWarriorsAndWizards(int numWarriorsAndWizards) {

        Faker faker = new Faker();

        for(int i = 0; i < numWarriorsAndWizards; i++) {
            var warrior = new Warrior(
                    (i + 1),
                    faker.lordOfTheRings().character(),
                    true
            );
            var wizard = new Wizard(
                    (i + 1),
                    faker.harryPotter().character(),
                    true
            );
            warriorsParty.add(warrior);
            wizardsParty.add(wizard);
        }
    }


    public static void newRoundOfBattle(Warrior warrior, Wizard wizard) {
        warrior.restartCounters();
        wizard.restartCounters();
        System.out.println("3.............2...........1........FIGHT!!!");
        System.out.println();
        int counter = 0;
        while(warrior.isAlive() && wizard.isAlive()) {
            wizard.attack(warrior);
            warrior.attack(wizard);
            counter ++;
            if (warrior.getHp() == 0) {
                warrior.setAlive(false);
                warriorsParty.remove(warrior);
            }
            if (wizard.getHp() == 0) {
                wizard.setAlive(false);
                wizardsParty.remove(wizard);
            }
        }
        statsCreator(warrior, wizard, counter);
    }


    public static void statsCreator (Warrior warrior, Wizard wizard, int counter){
        int damageWarrior = warrior.getInitialHp() - warrior.getHp();
        int damageWizard = wizard.getInitialHp() - wizard.getHp();

        System.out.println("STATS OF THE BATTLE");
        System.out.println("Number of rounds fought: "+ counter);
        System.out.println();
        System.out.println(warrior.getName() + "(warrior):");
        System.out.println("Stamina left: " + warrior.getStamina());
        System.out.println("HP left: " + warrior.getHp() + " (Damage taken: " + damageWarrior + ")");
        System.out.println("Damage inflicted : " + damageWizard + " (Heavy Attacks performed: " + warrior.getHeavyAttackCounter() + "  Weak Attacks performed: " + warrior.getWeakAttackCounter() + ")");
        System.out.println();
        System.out.println(wizard.getName() + "(wizard):");
        System.out.println("Mana left: " + wizard.getMana());
        System.out.println("HP left: " + wizard.getHp() + " (Damage taken: " + damageWizard + ")");
        System.out.println("Damage inflicted : " + damageWarrior + " (Fireballs casted: " + wizard.getFireballCounter() + " Staff Hits performed: " + wizard.getStaffHitCounter()+ ")");
        System.out.println();
        if (!warrior.isAlive() && !wizard.isAlive()) System.out.println("Both of them are dead! What a ferocious battle!!");
        else if (!warrior.isAlive()) System.out.println(wizard.getName()+ " has killed " + warrior.getName());
        else if (!wizard.isAlive()) System.out.println(warrior.getName()+ " has killed " + wizard.getName());

    }
}