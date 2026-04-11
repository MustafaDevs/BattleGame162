/**
 * A class representing a clan in the game, which has certain buffs.
 * 
 * @author Mustafa Faqiryar
 */
public class Clan {
    /** The name of the clan. */
    private final String name;
    /** The percentage increase in damage for clan members. */
    private double damageBuffPercentage;
    /** The percentage increase in health for clan members. */
    private double healthBuffPercentage;
    /** The additional spell damage percentage for clan members. */
    private double spellDamageBuffPercentage;
    /** The additional weapon damage percentage for clan members. */
    private double weaponDamageBuffPercentage;

    /**
     * Creates a new clan with certain buffs.
     * 
     * @param name                 The name of the clan.
     * @param damageBuffPercentage The damage buff (percentage)
     * @param healthBuffPercentage The health buff (percentage)
    * @param spellDamageBuffPercentage      The spell damage buff (percentage)
    * @param weaponDamageBuffPercentage     The weapon damage buff (percentage)
     * @postcondition A new clan will be created with the specified buffs. Negative
     *                buff values will automatically be set to 0.
     * 
     * @throws IllegalStateException if any of the buff values are negative.
     */
    public Clan(String name, double damageBuffPercentage, double healthBuffPercentage, double spellDamageBuffPercentage,
            double weaponDamageBuffPercentage) {
        this.name = name;
        this.damageBuffPercentage = damageBuffPercentage;
        this.healthBuffPercentage = healthBuffPercentage;
        this.spellDamageBuffPercentage = spellDamageBuffPercentage;
        this.weaponDamageBuffPercentage = weaponDamageBuffPercentage;
        checkAndRepairInvariants();
    }

    /**
     * Gets the name of the clan.
     * 
     * @return The name of the clan.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks that no class invariants of the Clan class are violated.
     * Buff values must be greater than or equal to 0 and are set to 0 if this is
     * violated.
     * 
     * The clan name must not be null, and an exception is thrown if it is.
     * 
     * @throws IllegalStateException if the clan name is null.
     */
    private void checkAndRepairInvariants() {
        if (name == null) {
            throw new IllegalStateException("Clan name cannot be null.");
        }

        if (damageBuffPercentage < 0) {
            damageBuffPercentage = 0;
        }

        if (healthBuffPercentage < 0) {
            healthBuffPercentage = 0;
        }

        if (spellDamageBuffPercentage < 0) {
            spellDamageBuffPercentage = 0;
        }

        if (weaponDamageBuffPercentage < 0) {
            weaponDamageBuffPercentage = 0;
        }
    }

    /**
     * Gets the damage buff percentage of the clan.
     * 
     * @return The damage buff percentage of the clan.
     */
    public double getDamageBuffPercentage() {
        return damageBuffPercentage;
    }

    /**
     * Gets the health buff percentage of the clan.
     * 
     * @return The health buff percentage of the clan.
     */
    public double getHealthBuffPercentage() {
        return healthBuffPercentage;
    }

    /**
     * Gets the spell damage buff percentage of the clan.
     * 
     * @return The spell damage buff percentage of the clan.
     */
    public double getSpellDamageBuffPercentage() {
        return spellDamageBuffPercentage;
    }

    /**
     * Gets the weapon damage buff percentage of the clan.
     * 
     * @return The weapon damage buff percentage of the clan.
     */
    public double getWeaponDamageBuffPercentage() {
        return weaponDamageBuffPercentage;
    }

    @Override
    public String toString() {
        return name + " Clan: {\n" +
                "\tDamage Buff (%) = " + damageBuffPercentage + "%\n" +
                "\tHealth Buff (%) = " + healthBuffPercentage + "%\n" +
                "\tSpell Damage Buff = " + spellDamageBuffPercentage + "\n" +
                "\tWeapon Damage Buff = " + weaponDamageBuffPercentage + "\n" +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true; // Meaning, they are literally the same object in memory.
        if (obj == null || getClass() != obj.getClass())
            return false;

        Clan otherClan = (Clan) obj;

        // Pretty sure this goes against our 162 coding style (no brackets for the
        // conditionals), but it looks a lot cleaner to me here.
        if (this.damageBuffPercentage != otherClan.damageBuffPercentage)
            return false;
        if (this.healthBuffPercentage != otherClan.healthBuffPercentage)
            return false;
        if (this.spellDamageBuffPercentage != otherClan.spellDamageBuffPercentage)
            return false;
        if (this.weaponDamageBuffPercentage != otherClan.weaponDamageBuffPercentage)
            return false;

        return name.equals(otherClan.name);
    }

    @Override
    public int hashCode() {
        // Apparently, 31 is the ideal prime number for hashCode() methods. Why? I don't
        // know.
        int result = name.hashCode();
        result = 31 * result + Double.hashCode(damageBuffPercentage);
        result = 31 * result + Double.hashCode(healthBuffPercentage);
        result = 31 * result + Double.hashCode(spellDamageBuffPercentage);
        result = 31 * result + Double.hashCode(weaponDamageBuffPercentage);

        return result;
    }

}
