/**
 * Represents a warrior character in the game.
 * 
 * @author Mustafa Faqiryar
 */
public class Warrior extends Character {
    private Weapon weapon;

    public Warrior(Name name, Clan clan, Weapon weapon) {
        super(name, clan);
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void updateWeapon(Weapon newWeapon) {
        this.weapon = newWeapon;
    }
}
