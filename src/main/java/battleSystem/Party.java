package battleSystem;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;
import entities.Character;
import entities.Warrior;
import entities.Wizard;

public class Party {
    private final ArrayList<Character> characters;
    private final String name;

    public Party(int partySize, String name) {

        this.name = name;

        System.out.println("Created the " + this.name + " team!");

        // TODO: replace ids with UUIDs

        this.characters = new ArrayList<Character>();

        for(int i = 0; i < partySize; i++) {
            if(new Random().nextInt(0,2) == 1) {
                // Generate Wizard
                characters.add(new Wizard(generateNameForWizards()));
            } else {
                // Generate Warrior
                characters.add(new Warrior(generateNameForWarriors()));
            }
        }
    }

    public Party(String name, ArrayList<Character> characters) {
        this.name = name;
        this.characters = characters;
    }

    public String getName() {
        return this.name;
    }

    private String generateNameForWarriors() {
        Faker nameGenerator = new Faker();
        String name = nameGenerator.lordOfTheRings().character();
        while(!isNameAvailable(name)) name += " Jr";
        return name;
    }

    private String generateNameForWizards() {
        Faker nameGenerator = new Faker();
        String name = nameGenerator.harryPotter().character();
        while(!isNameAvailable(name)) name += " Jr";
        return name;
    }

    private boolean isNameAvailable(String name) {
        for(var c: characters) if(c.getName().equals(name)) return false;
        return true;
    }

    // Virtual data

    public LinkedList<Character> getAliveCharacters() {
        var list = new LinkedList<Character>();
        for(var c: characters) if(c.isAlive()) list.add(c);
        return list;
    }

    public LinkedList<Character> getDeadCharacters() {
        var list = new LinkedList<Character>();
        for(var c: characters) if(!c.isAlive()) list.add(c);
        return list;
    }

    // Get fighter

    public Character getFighter() {
        var alive = getAliveCharacters();
        // BONUS DONE
        if(alive.size() <= 0) return null;
        if(alive.size() == 1) return alive.get(0);
        return alive.get(new Random().nextInt(0, alive.size() - 1));
    }

    // CSV
    public String toCSV() {
        var strb = new StringBuilder();
        strb.append("type;id;name;hp;resource;strong\n");
        for(var c: characters) {

            if (c instanceof Wizard) {
                var wizard = (Wizard) c;
                strb.append("%s;%s;%s;%s;%s;%s\n".formatted("Wizard", c.getId(), c.getName(), c.getHp(), wizard.getMana(), wizard.getIntelligence()));
            } else {
                var warrior = (Warrior) c;
                strb.append("%s;%s;%s;%s;%s;%S\n".formatted("Warrior", c.getId(), c.getName(), c.getHp(), warrior.getStamina(), warrior.getStrength()));
            }
        }
        return strb.toString();
    }
}