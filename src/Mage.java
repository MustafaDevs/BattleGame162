import java.util.HashSet;
import java.util.Set;

/**
 * A class representing a Mage character in the game.
 * Mages differ from generic characters and other classes in that they
 * have the ability to learn and cast spells and have spell points instead of
 * stamina.
 */
public class Mage extends Character {
    /**
     * Class Invariants:
     * - A mage's maximum spell points must be equal to 25 + (5 * the mage's level).
     * - A mage's spell points cannot exceed their maximum spell points.
     * - A mage cannot cast a spell that they do not know.
     * - A mage cannot cast a spell if the spell costs more spell points than they
     * currently have.
     * - A mage's spell points must be in the range: 0 <= Spell Points <= Max Spell
     * Points
     */

    /** The character's spell points. */
    private int spellPoints;
    /** The set of spells the character knows/can use. */
    private Set<Spell> spells;

    /**
     * Creates a new mage with the given name and clan.
     * 
     * @param name The name of the mage.  Example: new Name("FirstName", "LastName")
     * @param clan The mage's clan.
     * @postcondition A new mage will be created with the given name and clan.  The mage will know no spells.
     */
    public Mage(Name name, Clan clan) {
        super(name, clan);
        this.spells = new HashSet<>();
    }

    public String getSpells() {
        return spells.toString();
    }

    /**
     * Adds a spell to the set of spells that the mage knows.
     * 
     * @postcondition The spell will be added to the set of useable spells for the
     *                mage, or nothing will happen if it was already present in the
     *                set.
     */
    public void addSpell(Spell spell) {
        spells.add(spell);
    }

    /**
     * Removes a spell from teh set of spells that the mage knows.
     * 
     * @param spell The spell to remove from the mage's set of known spells.
     * @postcondition If the mage knew the spell before, they no longer do, and thus cannot use it.
     */
    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    /**
     * Whether the mage knows a given spell.
     * 
     * @param spell The spell to check for.
     * @return Whether the mage knows the spell (true if the set of the mage's spell
     *         contains the given spell).
     */
    public boolean knowsSpell(Spell spell) {
        return spells.contains(spell);
    }

    /**
     * Checks if the mage can cast a given spell.
     * 
     * @param spell The spell to check.
     * @return Whether the mage can cast the spell (true if the mage knows the spell
     *         and has enough spell points).
     */
    public boolean canCastSpell(Spell spell) {
        return spells.contains(spell) && spellPoints >= spell.getPointsToCast();
    }

    /**
     * Gets the mage's spell points.
     * 
     * @return The mage's spell points.
     */
    public int getSpellPoints() {
        return spellPoints;
    }

    /**
     * Gets the maximum number of spell points that the mage can have at a given moment.
     * 
     * @return The mage's maximum spell points (equivalent to: 25 + (mageLevel * 5)).
     */
    public int getMaxSpellPoints() {
        return 25 + (getLevel() * 5); // Max spell points increase with level.
    }

    @Override
    public String getCharacterType() {
        return "Mage";
    }
}
