package battleSystem;

import com.github.javafaker.Faker;
import entities.Character;

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

    public Battle(Party bt, Party rt) {

        // Generate parties
        blueTeam = bt;
        redTeam = rt;
    }

    public void begin() {
        Party winner;

        do {
            // New round
            Character fighterOfBlueTeam = blueTeam.getFighter();
            Character fighterOfRedTeam = redTeam.getFighter();
            System.out.println("--------------------------------------------");
            System.out.println();
            System.out.println("In this match the contestants are: ");
            System.out.println("From Team " + blueTeam.getName() + ": " + fighterOfBlueTeam);
            System.out.println("From Team " + redTeam.getName() + ": " + fighterOfRedTeam);
            System.out.println();
            System.out.println("3.................2..............1.........FIGHT!");
            System.out.println();
            fight(fighterOfBlueTeam, fighterOfRedTeam);
            System.out.println("--------------------------------------------");
            System.out.println("Remaining members of Team " + blueTeam.getName() + " :" + blueTeam.getAliveCharacters().size());
            System.out.println("Remaining members of Team " + redTeam.getName() + " :" + redTeam.getAliveCharacters().size());

            // Ask if stats are wanted

            winner = getWinner();
        } while(winner == null);

        System.out.println("The winner is " + winner.getName());
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

    private Party getWinner() {
        if (blueTeam.getAliveCharacters().size() <= 0) return redTeam;
        if (redTeam.getAliveCharacters().size() <= 0) return blueTeam;
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