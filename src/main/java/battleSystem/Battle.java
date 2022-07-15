package battleSystem;

import com.github.javafaker.Faker;
import entities.Character;
import utilities.Keyboard.ReadFromKeyboard;
import utilities.menu.Menu;
import utilities.menu.MenuOption;

/*
* This class manages the battle. It contains both parties and makes them fight.
* */
public class Battle {

    // colors are for identification
    Party blueTeam, redTeam;

    public Party getBlueTeam() {
        return blueTeam;
    }

    public Party getRedTeam() {
        return redTeam;
    }

    public Battle(int partySize) {

        // Generate parties
        blueTeam = new Party(partySize, new Faker().lordOfTheRings().location());
        redTeam = new Party(partySize, new Faker().harryPotter().location());
    }


    public void BeginSimulation(){

            do {
                // New round
                Character fighterOfBlueTeam = blueTeam.getFighter();
                Character fighterOfRedTeam = redTeam.getFighter();
                runRound(fighterOfBlueTeam, fighterOfRedTeam);
                // Ask if stats are wanted


            } while (getWinner() == null);
        System.out.println();
        System.out.println(getWinner());
    }
    public void BeginChoice(){

        do {
            System.out.println();
            Character fighterOfBlueTeam = blueTeam.getFighter(BlueFighterChoice());
            Character fighterOfRedTeam = redTeam.getFighter(RedFighterChoice());
            runRound(fighterOfBlueTeam, fighterOfRedTeam);

        } while (getWinner() == null);
        System.out.println();
        System.out.println(getWinner());
    }

    private  int  BlueFighterChoice(){
        MenuOption<Integer>[] blueFightersAvailable = new MenuOption[blueTeam.getAliveCharacters().size()];

        for (Integer i = 0; i < blueFightersAvailable.length; i++){

            var menuOption = MenuOption.create(blueTeam.getAliveCharacters(i).toString(), i);
            blueFightersAvailable[i] = menuOption;
        }
        var fightersMenuB = new Menu<>(blueFightersAvailable, "Team " + blueTeam.getName(), "Choose your next champion" );
        return fightersMenuB.display();
    }

    private int RedFighterChoice(){
        MenuOption<Integer>[] redFightersAvailable = new MenuOption[redTeam.getAliveCharacters().size()];


        for (Integer i = 0; i < redFightersAvailable.length; i++)
        {
            var menuOption = MenuOption.create(redTeam.getAliveCharacters(i).toString(), i);
            redFightersAvailable[i] = menuOption;
        }
        var fightersMenu = new Menu<>(redFightersAvailable, "Team " + redTeam.getName(), "Choose your next champion" );
        return fightersMenu.display();
    }

    private void runRound (Character char1, Character char2){
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("In this match the contestants are: ");
        System.out.println("From Team " + blueTeam.getName() + ": " + char1);
        System.out.println("From Team " + redTeam.getName() + ": " + char2);
        System.out.println();
        System.out.println("3.................2..............1.........FIGHT!");
        System.out.println();
        fight(char1, char2);
        System.out.println("--------------------------------------------");
        System.out.println("Remaining members of Team " + blueTeam.getName() + " :" + blueTeam.getAliveCharacters().size());
        System.out.println("Remaining members of Team " + redTeam.getName() + " :" + redTeam.getAliveCharacters().size());
    }
    private void fight(Character character1, Character character2) {
        character1.restartCounters();
        character2.restartCounters();
        int counter = 0;

        while (character1.isAlive() && character2.isAlive()) {
            character1.attack(character2);
            character2.attack(character1);
            counter++;
        }

        statsCreator(character1, character2, counter);
    }

    private String getWinner() {
        if(blueTeam.getAliveCharacters().size() <=0 && redTeam.getAliveCharacters().size() <= 0) return "Both teams are dead, its a draw!!";
        if (blueTeam.getAliveCharacters().size() <= 0) return "The winner is " + redTeam.getName() + "!!";
        if (redTeam.getAliveCharacters().size() <= 0) return "The winner is " + blueTeam.getName() + "!!";
        return null;
    }

    public static void statsCreator (Character character1, Character character2, int counter){
        int damageCharacter1 = character1.getInitialHp() - character1.getHp();
        int damageCharacter2 = character2.getInitialHp() - character2.getHp();

        System.out.println("STATS OF THE BATTLE");
        System.out.println("Number of rounds fought: "+ counter);
        System.out.println();
        System.out.println(character1.getName() + ":");
        System.out.println("HP left: " + character1.getHp() + " (Damage taken: " + damageCharacter1 + ")");
        System.out.println("Damage inflicted : " + damageCharacter2);
        character1.showStats();
        System.out.println();
        System.out.println(character2.getName() + ":");
        System.out.println("HP left: " + character2.getHp() + " (Damage taken: " + damageCharacter2 + ")");
        System.out.println("Damage inflicted : " + damageCharacter1);
        character2.showStats();
        System.out.println();

        if (!character1.isAlive() && !character2.isAlive()) System.out.println("Both of them are dead! What a ferocious battle!!");
        else if (!character1.isAlive()) System.out.println(character2.getName()+ " has killed " + character1.getName());
        else if (!character2.isAlive()) System.out.println(character1.getName()+ " has killed " + character2.getName());
    }
}