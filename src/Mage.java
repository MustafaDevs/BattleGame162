
public class Mage extends Character {
    private int spellPoints;
    private int maxSpellPoints;
    private Set<Spell> spells;

    public Mage(Name name, Clan clan) {
        super(name, clan);
        this.spellPoints = maxSpellPoints; // Start with full spell points
        this.spells = new HashSet<>();
    }
}
