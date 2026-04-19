import java.util.Random;

/**
 * A class representing a Spell in the game, which can be
 * used by mages to attack other characters.
 * 
 * @author Mustafa Faqiryar
 */
public class Spell implements Attack {
    // Class Invariants:
    // - Name must never be null
    // - 0 <= pointsToCast
    // - 0 < baseDamage
    // - 0 <= lifeStealPercentage
    // - 0.0 <= damageVariance <= 1.0

    /** The name of the spell. */
    private final String name;
    /** The number of spell points required to cast the spell. */
    private final int pointsToCast;
    /** The amount of damage the spell deals before any multipliers. */
    private final int baseDamage;
    /** The percentage of damage that is converted to HP for the caster. */
    private final double lifeStealPercentage;
    /** The percentage of variance that an attack can have. */
    private final double damageVariance;

    /**
     * Creates a new Spell using the given values.
     * 
     * @param name The name of the spell.  Cannot be null.
     * @param pointsToCast The number of spell points required to cast the spell.  Must be a non-negative integer.
     * @param baseDamage The amount of damage the spell deals.  Must be a positive integer.
     * @param lifeStealPercentage The percentage of damage dealt by the spell that should be converted into health for the character casting it.  Must be a non-negative integer.
     * @param damageVariance The percentage of variance in the spell's maximum/minimum damage calculations (used for randomization). Must be a non-negative integer.
     * @postcondition A new Spell will be created with the given properties.
     */
    public Spell(String name, int pointsToCast, int baseDamage, double lifeStealPercentage, double damageVariance) {
        this.name = name;
        this.pointsToCast = pointsToCast;
        this.baseDamage = baseDamage;
        this.lifeStealPercentage = lifeStealPercentage;
        this.damageVariance = damageVariance;

        checkInvariants();
    }

    /**
     * Throws an exception if any Class Invariants are violated.
     * 
     * @throws IllegalArgumentException if any invariants are violated.  Since all fields are final, these invariants
     * can only be violated in the constructor, so there is no need to throw an IllegalStateException at any point.
     */
    private void checkInvariants() {
        if (name == null) {
            throw new IllegalStateException("The spell's name cannot be null.");
        }
        else if (pointsToCast < 0) {
            throw new IllegalStateException("The required number points to cast the spell cannot be a negative number.");
        }
        else if (baseDamage <= 0) {
            throw new IllegalStateException("The spell's base damage cannot be less than or equal to zero (i.e., the spell must deal at least some damage).");
        }
        else if (lifeStealPercentage < 0) {
            throw new IllegalArgumentException("The spell's life steal percentage cannot be negative");
        }
    }

    // ======== Accessors ========

    /**
     * Gets the name of the spell.
     * 
     * @return The name of the spell.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of points required to cast the spell.
     * 
     * @return The amount of points a mage must have in order to cast the spell.
     */
    public int getPointsToCast() {
        return pointsToCast;
    }

    /**
     * Gets the base damage dealt by the spell before any modifiers/multipliers are applied.
     * 
     * @return The base damage of the spell.
     */
    public int getBaseDamage() {
        return baseDamage;
    }

    /**
     * Gets the life steal percentage of the spell (percentage of the damage that is added to the health of the one casting it).
     * 
     * @return The spell's life steal percentage.
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
     * Gets the percentage of damage variance (example: 0.2 = 20%) which is used to randomize the damage dealt when casting the spell.
     * If, for example, the damage variance was 20% and the base damage was 10 (ignoring all other modifiers), the attack
     * would deal between 8-12 damage due to the possibility of dealing 20% less than and 20% more than the base damage.
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
     * Calculates the damage that an attack by a character may perform by applying modifiers in addition to damage variance.
     * 
     * @param attacker The character performing the attack.
     * @param variance The percentage of variance in the min/max potential damage (0.5 = 50%, 0.02 = 2%)
     * @return A rounded down integer value representing the amount of damage performed by this attack.
     * @throws IllegalArgumentException if variance is less than 0.0
     */
    private int calculateDamage(Character self, double variance) {
        // Variance cannot be negative
        if (variance < 0.0) {
            throw new IllegalArgumentException("Damage variance cannot be negative (less than 0.0).");
        }
        else if (variance > 1.0) {
            throw new IllegalArgumentException("Damage variance cannot be greater than 1.0 (100%).");
        }

        // Need a new Random object for calculating pseudorandom damage.
        Random rand = new Random();

        // Calculate the total buff percentage from the clan's base damage buff and spell damage buff.
        Clan clan = self.getClan();
        double baseDamageBuffPercentage = clan.getDamageBuffPercentage();
        double spellDamageBuffPercentage = clan.getSpellDamageBuffPercentage();

        // The number to multiply the randomly calculated damage by (example: if damage was 1 and buffs were 30%, 1 * 1.3).
        double totalDamageMultiplier = 1.0 + (baseDamageBuffPercentage + spellDamageBuffPercentage);

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

    /**
     * Gets a String containing a detailed description of the spell.
     *  
     * @return The String containing a detailed description of the spell.
     */
    public String toDetailedString() {
        String result = name + " (Cost: " + pointsToCast + " SP)" + " | Base Damage: " + baseDamage
                + " | Life Steal (%): " + lifeStealPercentage;
        return result;
    }

    // ======== Default Object Method Overrides ========

    @Override
    public String toString() {
        return name + " (" + pointsToCast + " SP, " + baseDamage + " DMG, " + lifeStealPercentage + "% LS" + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Spell otherSpell = (Spell) obj;

        // Field Comparison
        if (getPointsToCast() != otherSpell.getPointsToCast())
            return false;
        if (getBaseDamage() != otherSpell.getBaseDamage())
            return false;
        if (getLifeStealPercentage() != otherSpell.getLifeStealPercentage())
            return false;

        return this.name.equals(otherSpell.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + pointsToCast;
        result = 31 * result + baseDamage;
        result = 31 * result + (int)lifeStealPercentage;

        return result;
    }
}
