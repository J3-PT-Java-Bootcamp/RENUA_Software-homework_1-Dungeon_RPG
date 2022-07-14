package battleSystem;
import java.util.ArrayList;
import java.util.Random;

import com.github.javafaker.Faker;
import entities.Character;
import entities.Warrior;
import entities.Wizard;

public class Party {
    private final ArrayList<Character> characters;

    public Party(int partySize) {

        // TODO: replace ids with UUIDs

        this.characters = new ArrayList<Character>();

        for(int i = 0; i < partySize; i++) {
            if(new Random().nextInt(0,1) == 0) {
                // Generate Wizard
                characters.add(new Wizard(0, generateName()));
            } else {
                // Generate Warrior
                characters.add(new Warrior(0, generateName()));
            }
        }
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

    public Character[] getAliveCharacters() {
        return (Character[]) characters.stream().filter(Character::isAlive).toArray();
    }

    public Character[] getDeadCharacters() {
        return (Character[]) characters.stream().filter((Character c) -> !c.isAlive()).toArray();
    }
}