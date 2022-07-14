package battleSystem;

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

    }
}