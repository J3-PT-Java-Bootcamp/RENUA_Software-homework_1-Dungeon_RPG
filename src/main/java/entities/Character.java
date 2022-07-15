package entities;
import java.util.UUID;

public abstract class Character implements Attacker {
    private final String id;
    private String name;
    protected int hp;
    protected int initialHp;
    private boolean isAlive = true;
    private int strongAttackCounter;
    private int weakAttackCounter;



    public Character(String name, int hp) {
        this.id = UUID.randomUUID().toString();
        setName(name);
        setHp(hp);
        setInitialHp(getHp());
        setStrongAttackCounter(0);
        setWeakAttackCounter(0);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if(hp <= 0) setAlive(false);
        this.hp = hp;
    }

    public int getInitialHp() {
        return initialHp;
    }

    public void setInitialHp(int initialHp) {
        this.initialHp = initialHp;
    }
    public int getStrongAttackCounter() {
        return strongAttackCounter;
    }

    public void setStrongAttackCounter(int heavyAttackCounter) {
        this.strongAttackCounter = heavyAttackCounter;
    }

    public int getWeakAttackCounter() {
        return weakAttackCounter;
    }

    public void setWeakAttackCounter(int weakAttackCounter) {
        this.weakAttackCounter = weakAttackCounter;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void doDamage(int damage) {
        // Must check if it is dead
        if(this.getHp() < damage)this.setHp(0);
        else this.setHp(this.getHp() - damage);

    }

    public String toString() {
        return super.toString();
    }

    public abstract void attack(Character character);

    public void restartCounters (){
        setInitialHp(getHp());
        setStrongAttackCounter(0);
        setWeakAttackCounter(0);

    }
    public void showStats(){}
}

