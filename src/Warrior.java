/**
 * Represents a warrior character in the game.
 * 
 * @author Mustafa Faqiryar
 */
public class Warrior extends Character {
    /**
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
}
