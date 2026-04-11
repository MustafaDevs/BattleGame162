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
    private final int baseDamage;
    /** The amount of stamina a character needs and loses when they use an attack. */
    private final int staminaToAttack;
    /** The percentage of damage dealt by the weapon that is converted to health. */
    private final double lifeStealPercentage;
    /** The percentage of variance that an attack can have. */
    private final double damageVariance;

    public Weapon(String name, int baseDamage, double  lifeStealPercentage, double damageVariance, int  staminaToAttack) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.lifeStealPercentage = lifeStealPercentage;
        this.damageVariance = damageVariance;
        this.staminaToAttack = staminaToAttack;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getStaminaToAttack() {
        return staminaToAttack;
    } 

    public double getLifeStealPercentage() {
        return lifeStealPercentage;
    }
    
    public double getDamageVariance() {
        return damageVariance;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + baseDamage;
        result = 31 * result + Double.hashCode(lifeStealPercentage);
        result = 31 * result + Double.hashCode(damageVariance);
        result = 31 * result + staminaToAttack;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
			return true;
        }
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
        }

        Weapon otherWeapon = (Weapon)obj;

        if (!this.name.equals(otherWeapon.name)) {
            return false;
        }
        if (this.baseDamage != otherWeapon.baseDamage) {
            return false;
        }
        if (this.lifeStealPercentage != otherWeapon.lifeStealPercentage) {
            return false;
        }
        if (this.damageVariance != otherWeapon.damageVariance) {
            return false;
        }

        return (this.staminaToAttack == otherWeapon.staminaToAttack);
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
        // Variance cannot be negative
        if (variance < 0.0) {
            throw new IllegalArgumentException("Damage variance cannot be negative (less than 0.0).");
        }

        // Need a new Random object for calculating pseudorandom damage.
        Random rand = new Random();

        // Calculate the total buff percentage from the clan's base damage buff and weapon damage buff.
        Clan clan = self.getClan();
        double baseDamageBuffPercentage = clan.getDamageBuffPercentage();
        double weaponDamageBuffPercentage = clan.getWeaponDamageBuffPercentage();

        // The number to multiply the randomly calculated damage by (example: if damage was 1 and buffs were 30%, 1 * 1.3).
        double totalDamageMultiplier = 1.0 + (baseDamageBuffPercentage + weaponDamageBuffPercentage);

        // Minimum & maximum range for the base damage (lower bound, upper bound)
        int minBaseDamage = (int) Math.round(baseDamage * (1 - variance));
        int maxBaseDamage = (int) Math.round(baseDamage * (1 + variance));

        // Get a pseudorandom damage in the range [minBaseDamage, maxBaseDamage].
        int randomDamage = rand.nextInt(maxBaseDamage - minBaseDamage + 1) + minBaseDamage;

        // The randomly generated damage multiplied by the total damage multiplier, rounded and then converted to an integer.
        int damageWithBuffs = (int)Math.round(randomDamage * totalDamageMultiplier);

        return damageWithBuffs;
    }
}
