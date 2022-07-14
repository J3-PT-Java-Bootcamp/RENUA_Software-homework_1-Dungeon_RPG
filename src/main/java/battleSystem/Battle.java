package battleSystem;

import com.github.javafaker.Faker;
import entities.Character;

/*
* This class manages the battle. It contains both parties and makes them fight.
* */
public class Battle {

    // colors are for identification
    Party blueTeam, redTeam;

    public Battle(int partySize) {

        // Generate parties
        blueTeam = new Party(partySize, new Faker().lordOfTheRings().location());
        redTeam = new Party(partySize, new Faker().harryPotter().location());
    }

    public void begin() {
        Party winner;
        do {
            // New round

            fight(blueTeam.getFighter(), redTeam.getFighter());

            // Ask if stats are wanted

            winner = getWinner();
        } while(winner == null);

        System.out.println("The winner is " + winner.getName());
    }

    private void fight(Character character1, Character character2) {
        character1.attack(character2);
        character2.attack(character1);
    }

    private Party getWinner() {
        if(blueTeam.getAliveCharacters().size() <= 0) return redTeam;
        if(redTeam.getAliveCharacters().size() <= 0) return blueTeam;
        return null;
    }
}