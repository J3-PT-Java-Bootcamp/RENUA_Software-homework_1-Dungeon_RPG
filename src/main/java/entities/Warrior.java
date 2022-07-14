package entities;

import java.util.Random;

public class Warrior extends Character {

    final static int MIN_HP_WARRIOR = 100;
    final static int MAX_HP_WARRIOR = 200;
    final static int MIN_STAMINA = 10;
    final static int MAX_STAMINA = 50;
    final static int MIN_STRENGTH = 1;
    final static int MAX_STRENGTH = 10;
    int stamina;
    int strength;

    public Warrior(int id, String name) {
        super(id, name, new Random().nextInt(MIN_HP_WARRIOR, MAX_HP_WARRIOR));
        setStamina(new Random().nextInt(MIN_STAMINA, MAX_STAMINA));
        setStrength(new Random().nextInt(MIN_STRENGTH, MAX_STRENGTH));
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
   
    public String toString() {
        return "Characters.entities.Warrior{" +
                "stamina=" + stamina +
                ", strength=" + strength +
                ", hp=" + hp +
                '}';
    }

    @Override
    public void attack(Character character) {
        if (stamina >= 5) {
            System.out.print("   Metodo a ejecutar: heavyAttack()");
            strongAttack(character);
        } else {
            System.out.print("   Metodo a ejecutar: weakAttack()");
            weakAttack(character);
        }
    }

    private void strongAttack(Character character) {
        character.setHp(character.getHp() - this.strength);
        setStamina(getStamina() - 5);
    }

    private void weakAttack(Character character) {
        character.setHp(character.getHp() - (int)Math.floor(this.strength / 2.0));
        setStamina(getStamina() + 1);
    }
}
