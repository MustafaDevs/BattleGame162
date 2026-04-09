/**
 * Represents a warrior character in the game.
 * 
 * @author Mustafa Faqiryar
 */
public class Warrior extends Character {
    /**
     * Class Invariants:
     * - A warrior's stamina must be a nonnegative number.
     * 
     */

    private Weapon weapon;
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
        this.weapon = weapon;
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
     * Gets the warrior's stamina.  Warriors require certain amounts of stamina to perform certain attacks.
     * 
     * @return The warrior's stamina.
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * Updates the warrior's weapon.
     * 
     * @param newWeapon The new weapon for the warrior.
     * @postcondition The warrior's weapon will be updated to the new weapon.
     */
    public void updateWeapon(Weapon newWeapon) {
        this.weapon = newWeapon;
    }

    @Override
    public String getCharacterType() {
        return "Warrior";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        else {
            Warrior otherWarrior = (Warrior)obj;
            if (!otherWarrior.weapon.equals(this.weapon)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        // TODO
        return "";
    }

    
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + weapon.hashCode();
        result = 31 * result + stamina;
        
        return result;
    }
}
