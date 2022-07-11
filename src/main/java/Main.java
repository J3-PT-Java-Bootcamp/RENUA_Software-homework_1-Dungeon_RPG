import utilities.Keyboard.ReadFromKeyboard;
import utilities.menu.Menu;
import utilities.menu.MenuOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

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
       */ List<Warrior> warriorsParty = new ArrayList<>();
        List<Wizard> wizardsParty = new ArrayList<>();
        List<Character> graveyard = new ArrayList<>();

        var warrior1 = new Warrior(
                1,
                "Conan",
                true
        );



        var wizard1 = new Wizard(
                1,
                "Gandalf",
                true
        );

        System.out.println("warrior 1: " + warrior1);
        System.out.println("wizard  1: " + wizard1);
        System.out.println();
        battler(warrior1, wizard1);

       /* int counter = 0;
        while(warrior1.getStamina() >= 1) {
            warrior1.attack(wizard1);
            counter++;
            System.out.println(counter);
            if (wizard1.isAlive() == false){
                System.out.println(wizard1.getName() + " has died in a ferocious battle");
                break;
            }
            System.out.println("warrior 1: " + warrior1);
            System.out.println("wizard  1: " + wizard1);
            System.out.println();
            if (wizard1.getHp()<= 0) wizard1.setAlive(false);
        }
        int warriorDamage = initialHpWizard - wizard1.getHp();
        System.out.println("STATS");
        System.out.println(warrior1.getName());
        System.out.println("Damage infringed: " + warriorDamage);

*/
    }

    public static void createWarriorsAndWizards(int num) {

    }

    public static void battler (Warrior warrior, Wizard wizard){
        System.out.println("3.............2...........1........FIGHT!!!");
        int counter = 0;
        while((warrior.getStamina() >= 1 || wizard.getMana() >= 1) )  {
            if (wizard.getHp()<= 0) wizard.setAlive(false);
            if (warrior.getHp()<= 0) warrior.setAlive(false);
            if (!wizard.isAlive() || !warrior.isAlive())break;
            warrior.attack(wizard);
            wizard.attack(warrior);
            counter++;


            /*System.out.println("warrior 1: " + warrior);
            System.out.println("wizard  1: " + wizard);
            System.out.println();
            */

        }
        System.out.println("Number of rounds fought: "+ counter);
        statsCreator(warrior, wizard);
    }

    public static void statsCreator (Warrior warrior, Wizard wizard){
        int damageWarrior = warrior.getInitialHp() - warrior.getHp();
        int damageWizard = wizard.getInitialHp() - wizard.getHp();
        System.out.println("------------------------------");
        System.out.println("STATS OF THE BATTLE");
        System.out.println(warrior.getName() + "(warrior):");
        System.out.println("Stamina left: " + warrior.getStamina());
        System.out.println("HP left: " + warrior.getHp() + " (Damage taken: " + damageWarrior + ")");
        System.out.println("Damage inflicted : " + damageWizard );
        System.out.println();
        System.out.println(wizard.getName() + "(wizard):");
        System.out.println("Mana left: " + wizard.getMana());
        System.out.println("HP left: " + wizard.getHp() + " (Damage taken: " + damageWizard + ")");
        System.out.println("Damage inflicted : " + damageWarrior );
        System.out.println("------------------------------");
        if (!warrior.isAlive()) System.out.println(wizard.getName()+ " has killed " + warrior.getName());
        else if (!wizard.isAlive()) System.out.println(warrior.getName()+ " has killed " + wizard.getName());
        else System.out.println("It has been a ferocious battle");


    }
}