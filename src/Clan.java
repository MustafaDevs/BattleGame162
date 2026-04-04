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
    /** The additional spell damage for clan members. */
    private int spellDamageBuff;
    /** The additional weapon damage for clan members. */
    private int weaponDamageBuff;

    /**
     * Creates a new clan with certain buffs.
     * 
     * @param name The name of the clan.
     * @param damageBuffPercentage The damage buff (percentage)
     * @param healthBuffPercentage The health buff (percentage)
     * @param spellDamageBuff The spell damage buff 
     * @param weaponDamageBuff The weapon damage buff
     * @postcondition A new clan will be created with the specified buffs. Negative buff values will automatically be set to 0.
     * 
     * @throws IllegalStateException if any of the buff values are negative.
     */
    public Clan(String name, double damageBuffPercentage, double healthBuffPercentage, int spellDamageBuff, int weaponDamageBuff) {
        this.name = name;
        this.damageBuffPercentage = damageBuffPercentage;
        this.healthBuffPercentage = healthBuffPercentage;
        this.spellDamageBuff = spellDamageBuff;
        this.weaponDamageBuff = weaponDamageBuff;
        checkAndRepairInvariants();
    }

    /**
     * Checks that no class invariants of the Clan class are violated.
     * Buff values must be greater than or equal to 0 and are set to 0 if this is violated.
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

        if (spellDamageBuff < 0) {
            spellDamageBuff = 0;
        }

        if (weaponDamageBuff < 0) {
            weaponDamageBuff = 0;
        }
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
     * Gets the spell damage buff (flat amount) of the clan.
     * 
     * @return The spell damage buff of the clan.
     */
    public int getSpellDamageBuff() {
        return spellDamageBuff;
    }

    /**
     * Gets the weapon damage buff (flat amount) of the clan.
     * 
     * @return The weapon damage buff of the clan.
     */
    public int getWeaponDamageBuff() {
        return weaponDamageBuff;
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
     * Gets the damage buff percentage of the clan.
     * 
     * @return The damage buff percentage of the clan.
     */
    public double getDamageBuffPercentage() {
        return damageBuffPercentage;
    }

    @Override
    public String toString() {
        return name + " Clan: {\n" +
                "\tDamage Buff (%) = " + damageBuffPercentage + "%\n" +
                "\tHealth Buff (%) = " + healthBuffPercentage + "%\n" +
                "\tSpell Damage Buff = " + spellDamageBuff + "\n" +
                "\tWeapon Damage Buff = " + weaponDamageBuff + "\n" +
                "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Clan clan = (Clan) obj;

        if (damageBuffPercentage != clan.damageBuffPercentage) return false;
        if (healthBuffPercentage != clan.healthBuffPercentage) return false;
        if (spellDamageBuff != clan.spellDamageBuff) return false;
        if (weaponDamageBuff != clan.weaponDamageBuff) return false;


        return name.equals(clan.name);
    }

    @Override
    public int hashCode() {
        // Apparently, 31 is the ideal prime number for hashCode() methods.  Why?  I don't know.
        int result = name.hashCode();
        result = 31 * result + Double.hashCode(damageBuffPercentage);
        result = 31 * result + Double.hashCode(healthBuffPercentage);
        result = 31 * result + spellDamageBuff;
        result = 31 * result + weaponDamageBuff;

        return result;
    }


}
