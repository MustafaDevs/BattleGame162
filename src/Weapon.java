/**
 * A class representing a Weapon in the game, which has the properties of base
 * damage and life steal percentage.
 * 
 * @author Mustafa Faqiryar
 */
public class Weapon {
    /** The name of the weapon. */
    private String name;
    /** The base damage of the weapon. */
    private int baseDamage;
    /** The percentage of damage dealt by the weapon that is converted to health. */
    private int lifeStealPercentage;

    public Weapon(String name, int baseDamage, int lifeStealPercentage) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.lifeStealPercentage = lifeStealPercentage;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getLifeStealPercentage() {
        return lifeStealPercentage;
    }

    public int generateAttack() {
        // TODO
        return 0;
    }
}
