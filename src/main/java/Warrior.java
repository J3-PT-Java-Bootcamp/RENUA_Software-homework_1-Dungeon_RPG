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



    int heavyAttackCounter;
    int weakAttackCounter;

    public Warrior(int id, String name, boolean isAlive) {
        super(id, name, new Random().nextInt(MIN_HP_WARRIOR, MAX_HP_WARRIOR), isAlive);
        setInitialHp(getHp());
        setStamina(new Random().nextInt(MIN_STAMINA, MAX_STAMINA));
        setStrength(new Random().nextInt(MIN_STRENGTH, MAX_STRENGTH));
        setHeavyAttackCounter(0);
        setWeakAttackCounter(0);

    }

    public Warrior(int id, String name, int HP, boolean isAlive) {
        super(id, name, HP, isAlive);
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

    public int getHeavyAttackCounter() {
        return heavyAttackCounter;
    }

    public void setHeavyAttackCounter(int heavyAttackCounter) {
        this.heavyAttackCounter = heavyAttackCounter;
    }

    public int getWeakAttackCounter() {
        return weakAttackCounter;
    }

    public void setWeakAttackCounter(int weakAttackCounter) {
        this.weakAttackCounter = weakAttackCounter;
    }
    public String toString() {
        return  getName()+ " (warrior " + getId() + ")"
                + "{" +
                "stamina=" + stamina +
                ", strength=" + strength +
                ", hp=" + hp +
                '}';
    }

    @Override
    public void attack(Character character) {
        if (stamina >= 5) {
            strongAttack(character);
            setHeavyAttackCounter(getHeavyAttackCounter()+1);
        } else  {
            weakAttack(character);
            setWeakAttackCounter(getWeakAttackCounter()+1);
        }
    }

    private void strongAttack(Character target) {

        target.doDamage(this.getStrength());
        setStamina(getStamina() - 5);
    }

    private void weakAttack(Character taget) {
        taget.doDamage (this.getStrength() / 2);
        setStamina(getStamina() + 1);
    }
    public void restartCounters (){
        setInitialHp(getHp());
        setHeavyAttackCounter(0);
        setWeakAttackCounter(0);
    }
}
