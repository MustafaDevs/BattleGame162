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
    private double lifeStealPercentage;
    /** The percentage of variance that an attack can have. */
    private final double damageVariance;

    public Weapon(String name, int baseDamage, double  lifeStealPercentage, double damageVariance) {
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

    public double getLifeStealPercentage() {
        return lifeStealPercentage;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + baseDamage;
        result = 31 * result + (int)lifeStealPercentage;
        result = 31 * result + (int)damageVariance; 

        return result;
    }

    @Override
    public void execute(Character self, Character target) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'execute'");
        if (self == target) {
            throw new IllegalArgumentException("You cannot execute an attack on yourself.");
        }


    }

    @Override
    public int calculateDamage(Character self, double variance) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'calculateDamage'");
        if (variance < 0.0) {
            throw new IllegalArgumentException("Damage variance cannot be less than 0.0");
        }

        double min = baseDamage - baseDamage * variance;
        double max = baseDamage + baseDamage * variance;
        
    }

    @Override
    public boolean canAttack(Character self) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canAttack'");
    }
}
