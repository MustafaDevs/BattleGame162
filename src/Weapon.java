import java.util.Random;

/**
 * A class representing a Weapon in the game, which has the properties of base
 * damage and life steal percentage.
 * 
 * @author Mustafa Faqiryar
 */
public class Weapon implements Attack {
    /** The name of the weapon. */
    private String name;
    /** The base damage of the weapon. */
    private int baseDamage;
    /** The percentage of damage dealt by the weapon that is converted to health. */
    private int lifeStealPercentage;
    /** The percentage of variance that an attack can have. */
    private final double damageVariance;

    public Weapon(String name, int baseDamage, int lifeStealPercentage, double damageVariance) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.lifeStealPercentage = lifeStealPercentage;
        this.damageVariance = damageVariance;
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

    @Override
    public void execute(Character self, Character target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public int calculateDamage(Character self, double variance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateDamage'");
    }

    @Override
    public boolean canAttack(Character self) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canAttack'");
    }
}
