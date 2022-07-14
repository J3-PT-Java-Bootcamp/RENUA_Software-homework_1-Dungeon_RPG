abstract class Character implements Attacker {
    private int id;
    private String name;
    protected int hp;
    private boolean isAlive;

    public Character(int id, String name, int HP, boolean isAlive) {
        this.id = id;
        this.name = name;
        this.hp = HP;
        this.isAlive = isAlive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        this.hp = hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void doDamage(int damage) {
        // Must check if it is dead

        this.setHp(this.getHp() - damage);
    }

    public String toString() {
        return super.toString();
    }

    public abstract void attack(Character character);
}

