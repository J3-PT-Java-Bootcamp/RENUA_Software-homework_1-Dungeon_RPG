import utilities.menu.Menu;
import utilities.menu.MenuOption;

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
    public Wizard(int id, String name, boolean isAlive) {
        super(id, name, new Random().nextInt(MIN_HP, MAX_HP), isAlive);
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
        // TODO: give the user the ability to choose
        // When we wan to, we can remove comments and comment the code below
        /*var options = new MenuOption[] {
            MenuOption.create("Fireball", Attacks.Fireball, this.getMana() >= 5),
            MenuOption.create("Staff Hit", Attacks.StaffHit)
        };
        var chosenAttack = new Menu<Attacks>(options, "Attack", "Choose an attack").display();

        if(chosenAttack == Attacks.StaffHit) staffHit(target);
        else fireball(target);*/

        if(this.getMana() >= 5) {
            System.out.print("Metodo a ejecutar: fireball()");
            fireball(target);
        }
        else {
            System.out.print("Metodo a ejecutar: staffHit()");
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
        return "Wizard{" +
                "mana=" + mana +
                ", intelligence=" + intelligence +
                ", hp=" + hp +
                '}';
    }
}
