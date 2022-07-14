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
            if(new Random().nextInt(0,1) == 0) {
                // Generate Wizard
                characters.add(new Wizard(generateName()));
            } else {
                // Generate Warrior
                characters.add(new Warrior(generateName()));
            }
        }
    }

    public Party(String name, Character[] characters) {
        this.name = name;
        this.characters = new ArrayList<>(List.of(characters));
    }

    public String getName() {
        return this.name;
    }

    private String generateName() {
        String name = new Faker().name().name();
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
        strb.append("id;name;hp\n");
        for(var c: characters) {
            strb.append("%s;%s;%s\n".formatted(c.getId(), c.getName(), c.getHp()));
        }
        return strb.toString();
    }
}