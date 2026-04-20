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
     * - The set of the mage's known spells cannot be null.
     * - The set of the mage's known spells cannot contain null.
     * - A mage cannot cast a spell that they do not know (i.e., is not in the set
     * of spells).
     * - A mage cannot cast a spell if the spell costs more spell points than they
     * currently have.
     * - A mage's spell points must be in the range: 0 <= Spell Points <= Max Spell
     * Points
     */

    /** The character's spell points. */
    private int spellPoints;
    /** The set of spells the character knows/can use. */
    private final Set<Spell> knownSpells;

    /**
     * Creates a new mage with the given name and clan.
     * 
     * @param name The name of the mage. Example: new Name("FirstName", "LastName")
     * @param clan The mage's clan.
     * @postcondition A new mage will be created with the given name and clan. The
     *                mage will know no spells.
     */
    public Mage(Name name, Clan clan) {
        super(name, clan);
        this.knownSpells = new HashSet<>();
        spellPoints = getMaxSpellPoints();
    }

    /**
     * Adds spell points to the mage.
     * 
     * @param SP The number of spell points to add.  Must be a positive integer.
     * @postcondition The mage's spell points will be equal to whichever is less between
     *                the mage's maximum spell points and their current spell points
     *                plus the amount to add, or: Min(getMaxSpellPoints(),
     *                getSpellPoints() + SP)
     */
    public void addSpellPoints(int SP) {
        if (SP < 1) {
            throw new IllegalArgumentException("The amount of spell points to add must be a positive integer.");
        }
        spellPoints = Math.min(getMaxSpellPoints(), spellPoints + SP);
    }

    /**
     * Removes spell points from the mage.
     * 
     * @param SP The number of spell points to remove.
     * @postcondition The mage's spell points will be equal to whichever is greater between
     *                zero and their current spell points minus the amount to
     *                remove, or: Max(0, getSpellPoints() - SP)
     */
    public void removeSpellPoints(int SP) {
        if (SP < 1) {
            throw new IllegalArgumentException("The amount of spell points to remove must be a positive integer.");
        }
        spellPoints = Math.max(0, spellPoints - SP);
    }

    /**
     * Gets the set of the mage's known spells as a string.
     * 
     * @return A string representation of the set of the mage's spells.
     */
    public String getSpells() {
        return knownSpells.toString();
    }

    /**
     * Adds a spell to the set of spells that the mage knows.
     * 
     * @postcondition The spell will be added to the set of useable spells for the
     *                mage, or nothing will happen if it was already present in the
     *                set.
     */
    public void addSpell(Spell spell) {
        if (spell == null) {
            throw new IllegalArgumentException("A mage cannot learn a null spell.");
        }
        knownSpells.add(spell);
    }

    /**
     * Removes a spell from teh set of spells that the mage knows.
     * 
     * @param spell The spell to remove from the mage's set of known spells.
     * @postcondition If the mage knew the spell before, they no longer do, and thus
     *                cannot use it.
     */
    public void removeSpell(Spell spell) {
        knownSpells.remove(spell);
    }

    /**
     * Whether the mage knows a given spell.
     * 
     * @param spell The spell to check for.
     * @return Whether the mage knows the spell (true if the set of the mage's spell
     *         contains the given spell).
     */
    public boolean knowsSpell(Spell spell) {
        return knownSpells.contains(spell);
    }

    /**
     * Checks if the mage can cast a given spell.
     * 
     * @param spell The spell to check.
     * @return Whether the mage can cast the spell (true if the mage knows the spell
     *         and has enough spell points).
     */
    public boolean canCastSpell(Spell spell) {
        return (knownSpells.contains(spell)) && (spellPoints >= spell.getPointsToCast());
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
     * Gets the maximum number of spell points that the mage can have at a given
     * moment.
     * 
     * @return The mage's maximum spell points (equivalent to: 25 + (mageLevel *
     *         5)).
     */
    public int getMaxSpellPoints() {
        return 25 + (getLevel() * 5); // Max spell points increase with level.
    }

    @Override
    public String getCharacterType() {
        return "Mage";
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj))
            return false;
        else {
            Mage otherMage = (Mage) obj;

            // spellPoints is a volatile property and will therefore not be used to check
            // equality.
            if (!this.knownSpells.equals(otherMage.knownSpells)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean canUse(Attack attack) {
        if (!(attack instanceof Spell)) {
            return false;
        }

        Spell attackSpell = (Spell) attack;

        // Since
        if (!knownSpells.contains(attackSpell)) {
            return false;
        }

        return getSpellPoints() >= attackSpell.getPointsToCast();
    }
}
