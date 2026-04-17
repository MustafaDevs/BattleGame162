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
    // - 0 <= damageVariance

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
     */
    public Spell(String name, int pointsToCast, int baseDamage, double lifeStealPercentage, double damageVariance) {
        this.name = name;
        this.pointsToCast = pointsToCast;
        this.baseDamage = baseDamage;
        this.lifeStealPercentage = lifeStealPercentage;
        this.damageVariance = damageVariance;
    }

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

    public String getName() {
        return name;
    }

    public int getPointsToCast() {
        return pointsToCast;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public double getLifeStealPercentage() {
        return lifeStealPercentage;
    }

    public int getLifeStealFromDamage(int damage) {
        return (int)(Math.round(damage * lifeStealPercentage));
    }
    
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
     * @param variance The percentage of variance in the min/max potential damage (0.5 = 50%, 0.02 = 2%)
     * @return A rounded down integer value representing the amount of damage performed by this attack.
     * @throws IllegalArgumentException if variance is less than 0.0
     */
    private int calculateDamage(Character self, double variance) {
        // Variance cannot be negative
        if (variance < 0.0) {
            throw new IllegalArgumentException("Damage variance cannot be negative (less than 0.0).");
        }

        // Need a new Random object for calculating pseudorandom damage.
        Random rand = new Random();

        // Calculate the total buff percentage from the clan's base damage buff and spell damage buff.
        Clan clan = self.getClan();
        double baseDamageBuffPercentage = clan.getDamageBuffPercentage();
        double spellDamageBuffPercentage = clan.getSpellDamageBuffPercentage();

        // The number to multiply the randomly calculated damage by (example: if damage was 1 and buffs were 30%, 1 * 1.3).
        double totalDamageMultiplier = 1.0 + (baseDamageBuffPercentage + spellDamageBuffPercentage);

        // Minimum & maximum range for the base damage (lower bound, upper bound)
        int minBaseDamage = (int) Math.round(baseDamage * (1 - variance));
        int maxBaseDamage = (int) Math.round(baseDamage * (1 + variance));

        // Get a pseudorandom damage in the range [minBaseDamage, maxBaseDamage].
        int randomDamage = rand.nextInt(maxBaseDamage - minBaseDamage + 1) + minBaseDamage;

        // The randomly generated damage multiplied by the total damage multiplier, rounded and then converted to an integer.
        int damageWithBuffs = (int)Math.round(randomDamage * totalDamageMultiplier);

        return damageWithBuffs;
    }

    // ======== Utility Methods ========

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
