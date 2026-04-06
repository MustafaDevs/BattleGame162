import java.util.HashSet;
import java.util.Set;

public class Mage extends Character {
    /** The character's spell points. */
    private int spellPoints;
    /** The maximum spell points for the character. */
    private int maxSpellPoints;
    /** The set of spells the character knows/can use. */
    private Set<Spell> spells;

    public Mage(Name name, Clan clan) {
        super(name, clan);
        this.spellPoints = maxSpellPoints; // Start with full spell points
        this.spells = new HashSet<>();
    }

    public Set<Spell> getSpells() {
        return spells;
    }

    public int getSpellPoints() {
        return spellPoints;
    }

    public String getSpellsAsString() {
        return spells.toString();
    }

    @Override
    public String getCharacterType() {
        return "Mage";
    }
}
