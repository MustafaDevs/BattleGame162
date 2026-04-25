/**
 * Represents a warrior character in the game.
 * 
 * @author Mustafa Faqiryar
 */
public class Warrior extends Character {
    /**
     * Class Invariants:
     * - A warrior's weapon cannot be null.
     * - A warrior's stamina must be in the range 0 <= stamina <= getMaxStamina()
     */

    /** The warrior's weapon. */
    private Weapon weapon;
    /** The warrior's stamina (used to perform tasks such as attacks) */
    private int stamina;

    /** Creates a new Warrior character.
     * 
     * @param name The name of the warrior.
     * @param clan The clan of the warrior.
     * @param weapon The warrior's weapon.
     * @postcondition A new warrior character will be created with the specified name, clan, and weapon.
     */
    public Warrior(Name name, Clan clan, Weapon weapon) {
        super(name, clan);
        if (weapon == null) {
            throw new IllegalArgumentException("A warrior's weapon cannot be null.");
        }
        this.weapon = weapon;
        stamina = getMaxStamina();
    }

    /**
     * Gets the warrior's weapon.
     * 
     * @return The warrior's weapon.
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Adds stamina to the warrior.
     * 
     * @param amount The amount of stamina to add
     * @postcondition The warrior's stamina will be equal to whichever is less between
     *                the warrior's maximum stamina and their current stamina
     *                plus the amount to add, or: Min(getMaxStamina(),
     *                getStamina() + SP)
     */
    public void addStamina(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("The amount of stamina to add must be a positive integer.");
        }
        stamina = Math.min(getMaxStamina(), stamina + amount);
    }

    /**
     * Removes stamina from the warrior.
     * 
     * @param amount The amount of stamina to remove.
     * @postcondition The warrior's stamina will be equal to whichever is greater between
     *                zero and their current stamina minus the amount to
     *                remove, or: Max(0, getStamina() - SP)
     */
    public void removeStamina(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("The amount of stamina to add must be a positive integer.");
        }
        stamina = Math.max(0, stamina - amount);
    }
    
    /**
     * Gets the warrior's stamina.  Warriors require certain amounts of stamina to perform certain attacks.
     * 
     * @return The warrior's stamina.
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * Returns the maximum stamina that a warrior can have.
     * 
     * @return
     */
    public int getMaxStamina() {
        return 25 + (getLevel() * 5); // Max stamina increases per level.
    }

    /**
     * Updates the warrior's weapon.
     * 
     * @param newWeapon The new weapon for the warrior. Cannot be null.
     * @postcondition The warrior's weapon will be updated to the new weapon.
     * @throws IllegalArgumentException if the new weapon is null.
     */
    public void updateWeapon(Weapon newWeapon) {
        if (newWeapon == null) {
            throw new IllegalArgumentException("The warrior's new weapon cannot be null.");
        }

        this.weapon = newWeapon;
    }

    @Override
    protected void handleLevelUp() {
        stamina = getMaxStamina();
    }

    @Override
    public String getCharacterType() {
        return "Warrior";
    }
    
    @Override
    public boolean canUse(Attack attack) {
        if (!(attack instanceof Weapon)) {
            return false;
        }

        Weapon attackWeapon = (Weapon) attack;

        if (!attackWeapon.equals(this.weapon)) {
            return false;
        }

        return getStamina() >= attackWeapon.getStaminaToAttack();
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        else {
            Warrior otherWarrior = (Warrior) obj;

            if (!otherWarrior.weapon.equals(this.weapon)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "  Wielding: " + weapon.getName();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + weapon.hashCode();
        
        return result;
    }
}
