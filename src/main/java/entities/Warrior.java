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


    public Warrior(String name) {
        super(name, new Random().nextInt(MIN_HP_WARRIOR, MAX_HP_WARRIOR));
        setStamina(new Random().nextInt(MIN_STAMINA, MAX_STAMINA));
        setStrength(new Random().nextInt(MIN_STRENGTH, MAX_STRENGTH));
        setInitialHp(this.getHp());
        setStrongAttackCounter(0);
        setWeakAttackCounter(0);
    }

    public Warrior(String id, String name, int hp, int stamina, int strength) {
        super(name, hp, id);
        setStamina(stamina);
        setStrength(strength);
        setInitialHp(this.getHp());
        setStrongAttackCounter(0);
        setWeakAttackCounter(0);
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
        return getName() + " (warrior)" + " {" +
                "stamina=" + stamina +
                ", strength=" + strength +
                ", hp=" + hp +
                '}';
    }


    @Override
    public void attack(Character character) {
        if (stamina >= 5) {
            //System.out.print("   Metodo a ejecutar: heavyAttack()");
            strongAttack(character);
            setStrongAttackCounter(getStrongAttackCounter() + 1);
        } else {
            //System.out.print("   Metodo a ejecutar: weakAttack()");
            weakAttack(character);
            setWeakAttackCounter(getWeakAttackCounter() + 1);
        }
    }

    private void strongAttack(Character character) {
        character.doDamage(this.strength / 2);
        setStamina(getStamina() - 5);
    }

    private void weakAttack(Character character) {
        character.doDamage(this.strength / 2);
        setStamina(getStamina() + 1);
    }

    @Override
    public void showStats() {
        System.out.println("Stamina left: " + this.getStamina());
        System.out.println("Heavy Attacks performed: " + this.getStrongAttackCounter());
        System.out.println("Weak Attacks performed: " + this.getWeakAttackCounter());
    }
}
