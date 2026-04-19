import java.util.Random;

/**
 * A class representing a Weapon in the game, which can be
 * equipped by a warrior.  Used to perform attacks.
 * 
 * @author Mustafa Faqiryar
 */
public class Weapon implements Attack {
    // Class Invariants:
    // - Name must never be null
    // - 0 <= staminaToAttack
    // - 0 < baseDamage
    // - 0 <= lifeStealPercentage
    // - 0.0 <= damageVariance <= 1.0

    /** The name of the weapon. */
    private final String name;
    /** The base damage of the weapon. */
    private final int baseDamage;
    /** The amount of stamina a character needs and loses when they use an attack. */
    private final int staminaToAttack;
    /** The percentage of damage dealt by the weapon that is converted to health. */
    private final double lifeStealPercentage;
    /** The percentage of variance that an attack can have. */
    private final double damageVariance;

    /**
     * 
     * @param name The name of the weapon.  Cannot be null.
     * @param staminaToAttack The amount of stamina required to perform an attack.  Must be a non-negative integer.
     * @param baseDamage The amount of damage the weapon deals.  Must be a positive integer.
     * @param lifeStealPercentage The percentage of damage dealt by the weapon that should be converted into health for the character casting it.  Must be a non-negative integer.
     * @param damageVariance The percentage of variance in the weapon's maximum/minimum damage calculations (used for randomization). Must be a non-negative integer.
     * @postcondition A new Weapon will be created with the given properties.
     */
    public Weapon(String name, int baseDamage, double lifeStealPercentage, double damageVariance, int staminaToAttack) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.lifeStealPercentage = lifeStealPercentage;
        this.damageVariance = damageVariance;
        this.staminaToAttack = staminaToAttack;

        checkInvariants();
    }

    // ======== Private Helper Methods ========
    
    /**
     * Throws an exception if any Class Invariants are violated.
     * 
     * @throws IllegalArgumentException if any invariants are violated.  Since all fields are final, these invariants
     * can only be violated in the constructor, so there is no need to throw an IllegalStateException at any point.
     */
    private void checkInvariants() {
        if (name == null) {
            throw new IllegalArgumentException("The weapon's name cannot be null.");
        }
        else if (staminaToAttack < 0) {
            throw new IllegalArgumentException("The required number points to cast the weapon cannot be a negative number.");
        }
        else if (baseDamage <= 0) {
            throw new IllegalArgumentException("The weapon's base damage cannot be less than or equal to zero (i.e., the weapon must deal at least some damage).");
        }
        else if (lifeStealPercentage < 0) {
            throw new IllegalArgumentException("The weapon's life steal percentage cannot be negative");
        }
        else if (damageVariance < 0) {
            throw new IllegalArgumentException("The damage variance percentage cannot be negative.");
        }
        else if (damageVariance > 1.0) {
            throw new IllegalArgumentException("The damage variance percentage cannot be greater than 1.0 (100%).");
        }
    }

    // ======== Accessors ========

    /**
     * Gets the name of the weapon.
     * 
     * @return The name of the weapon.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the base damage dealt by the weapon before any modifiers/multipliers are applied.
     * 
     * @return The base damage of the weapon.
     */
    public int getBaseDamage() {
        return baseDamage;
    }

    /**
     * Gets the amount of stamina required to attack.
     * 
     * @return The amount of stamina required (and drained) from a character each time they perform an attack.
     */
    public int getStaminaToAttack() {
        return staminaToAttack;
    } 

    /**
     * Gets the life steal percentage of the weapon (percentage of the damage that is added to the health of the one attacking with it).
     * 
     * @return The weapon's life steal ercentage.
     */
    public double getLifeStealPercentage() {
        return lifeStealPercentage;
    }

    /**
     * Gets the amount of health (using the life steal percentage) based on a certain amount of damage dealt.
     * 
     * @param damage The amount of damage to which the life steal percentage should be applied to.  Must be a non-negative number.
     * @return The amount of health a character should receive when they perform an attack that deals a given amount of damage.
     * @throws IllegalArgumentException if the damage is less than zero.
     */
    public int getLifeStealFromDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("The amount of damage cannot be less than zero.");
        }
        return (int)(Math.round(damage * lifeStealPercentage));
    }
    
    /**
     * Gets the percentage of damage variance (example: 0.2 = 20%) which is used to randomize the damage dealt when casting the weapon.
     * If, for example, the damage variance was 20% and the base damage was 10 (ignoring all other modifiers), the attack
     * damage would be in the range [8, 12] due to the possibility of dealing 20% less than or 20% more than the base damage.
     * 
     * @return The percentage of damage variance.
     */
    public double getDamageVariance() {
        return damageVariance;
    }

    // ======== Attack Methods & Helpers ========
    
    @Override
    public int execute(Character attacker, Character target) {
        if (attacker == target) {
            throw new IllegalArgumentException("You cannot execute an attack on yourself.");
        }

        int damage = calculateDamage(attacker, damageVariance);
        int healthFromLifeSteal  = getLifeStealFromDamage(damage);

        target.subtractHealth(damage);
        attacker.addHealth(healthFromLifeSteal);

        return damage;
    }

    /**
     * Calculates the damage that a hypothetical attack may perform.  Has damage variance.
     * 
     * @param attacker The character performing the attack.
     * @param variance The percentage of variance in the min/max potential damage (0.5 = 50%, 0.02 = 2%).  Must be in range [0.0, 1.0].
     * @return A rounded down integer value representing the amount of damage performed by this attack.
     * @throws IllegalArgumentException if variance is less than 0.0 or greater than 1.0
     */
    private int calculateDamage(Character attacker, double variance) {
        // Variance cannot be negative
        if (variance < 0.0 || variance > 1.0) {
            throw new IllegalArgumentException("The variance in damage must be in the range [0.0, 1.0] (cannot be negative or exceed 100%).");
        }

        // Need a new Random object for calculating pseudorandom damage.
        Random rand = new Random();

        // Calculate the total buff percentage from the clan's base damage buff and weapon damage buff.
        Clan clan = attacker.getClan();
        double baseDamageBuffPercentage = clan.getDamageBuffPercentage();
        double weaponDamageBuffPercentage = clan.getWeaponDamageBuffPercentage();

        // The number to multiply the randomly calculated damage by (example: if damage was 1 and buffs were 30%, 1 * 1.3).
        double totalDamageMultiplier = 1.0 + (baseDamageBuffPercentage + weaponDamageBuffPercentage);

        // Minimum & maximum range for the base damage (lower bound, upper bound).
        // Math.max() is used to ensure that damage is always greater than or equal to 1.
        int minBaseDamage = Math.max(1, (int) Math.round(baseDamage * (1 - variance)));
        int maxBaseDamage = Math.max(1, (int) Math.round(baseDamage * (1 + variance)));

        // Get a pseudorandom damage in the range [minBaseDamage, maxBaseDamage].
        int randomDamage = rand.nextInt(maxBaseDamage - minBaseDamage + 1) + minBaseDamage;

        // The randomly generated damage multiplied by the total damage multiplier, rounded and then converted to an integer.
        int damageWithBuffs = (int)Math.round(randomDamage * totalDamageMultiplier);

        return damageWithBuffs;
    }

    // ======== Utility Methods ========

    public String toDetailedString() {
        String result = name + " (Cost: " + staminaToAttack + " Stamina)" + " | Base Damage: " + baseDamage
                + " | Life Steal (%): " + lifeStealPercentage;
        return result;
    }

    // ======== Default Object Method Overrides ========

    @Override
    public String toString() {
        return name + " (" + staminaToAttack + " SPA, " + baseDamage + " DMG, " + lifeStealPercentage + "% LS" + ")";
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
}
