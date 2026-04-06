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
     * - A mage cannot cast a spell if the spell costs more spell points than they currently have.
     * - A mage's spell points must be in the range: 0 <= Spell Points <= Max Spell Points
     */

    /** The character's spell points. */
    private int spellPoints;
    /** The set of spells the character knows/can use. */
    private Set<Spell> spells;

    public Mage(Name name, Clan clan) {
        super(name, clan);
        this.spells = new HashSet<>();
    }

    public String getSpells() {
        return spells.toString();
    }

    public void addSpell(Spell spell) {
        spells.add(spell);
    }

    public void removeSpell(Spell spell) {
        spells.remove(spell);
    }

    /** Whether the mage knows a given spell.
     * 
     * @param spell The spell to check for.
     * @return Whether the mage knows the spell (true if the set of the mage's spell contains the given spell).
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

    public int getSpellPoints() {
        return spellPoints;
    }

    public int getMaxSpellPoints() {
        return 25 + (getLevel() * 5); // Max spell points increase with level.
    }

    @Override
    public String getCharacterType() {
        return "Mage";
    }
}
