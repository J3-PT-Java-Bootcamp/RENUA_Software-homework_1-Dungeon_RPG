package battleSystem;

import entities.Character;

/*
* This class manages the battle. It contains both parties and makes them fight.
* */
public class Battle {

    Party blueTeam, redTeam;

    public Battle(int partySize) {

        // Generate parties
        blueTeam = new Party(partySize);
        redTeam = new Party(partySize);
    }

    public void begin() {
        Party winner;
        do {


            winner = getWinner();
        } while(winner == null);
    }

    private void fight(Character character1, Character character2) {

    }

    private Party getWinner() {
        if(blueTeam.getAliveCharacters().length <= 0) return redTeam;
        if(redTeam.getAliveCharacters().length <= 0) return blueTeam;
        return null;
    }
}