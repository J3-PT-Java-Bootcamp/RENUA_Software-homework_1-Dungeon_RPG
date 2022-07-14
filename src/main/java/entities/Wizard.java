package entities;

import java.util.Random;

public class Wizard extends Character{

    final static int MIN_HP = 50;
    final static int MAX_HP = 100;
    final static int MIN_MANA = 10;
    final static int MAX_MANA = 50;
    final static int MIN_INTELLIGENCE = 1;
    final static int MAX_INTELLIGENCE = 50;

    private int mana;
    private int intelligence;
    public Wizard(String name) {
        super(name, new Random().nextInt(MIN_HP, MAX_HP));
        setMana(new Random().nextInt(MIN_MANA, MAX_MANA));
        setIntelligence(new Random().nextInt(MIN_INTELLIGENCE, MAX_INTELLIGENCE));
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    public void increaseMana(int mana) {
        this.setMana(this.getMana() + mana);
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public void attack(Character target) {
        if(this.getMana() >= 5) {
            fireball(target);
        }
        else {
            staffHit(target);
        }
    }

    public void fireball(Character target) {
        if(this.getMana() >= 5) {
            this.increaseMana(-5);
            target.doDamage(this.getIntelligence());
        } else staffHit(target); // <-- Just in case
    }

    public void staffHit(Character target) {
        this.increaseMana(1);
        target.doDamage(2);
    }

    public String toString() {
        return "Characters.entities.Wizard{" +
                "mana=" + mana +
                ", intelligence=" + intelligence +
                ", hp=" + hp +
                '}';
    }
}
