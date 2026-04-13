import java.util.Random;

public class Spell implements Attack {
    /** The name of the spell. */
    private final String name;
    /** The number of spell points required to cast the spell. */
    private final int pointsToCast;
    /** The amount of damage the spell deals before any multipliers. */
    private final int baseDamage;
    /** The percentage of damage that is converted to HP for the caster. */
    private final double lifeStealPercentage;

    public Spell(String name, int pointsToCast, int baseDamage, double lifeStealPercentage) {
        this.name = name;
        this.pointsToCast = pointsToCast;
        this.baseDamage = baseDamage;
        this.lifeStealPercentage = lifeStealPercentage;
    }

    public String getName() {
        return name;
    }

    public int getPointsToCast() {
        return pointsToCast;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public double getLifeStealPercentage() {
        return lifeStealPercentage;
    }

    public String toDetailedString() {
        String result = name + " (Cost: " + pointsToCast + " SP)" + " | Base Damage: " + baseDamage
                + " | Life Steal (%): " + lifeStealPercentage;
        return result;
    }

    @Override
    public String toString() {
        return name + " (" + pointsToCast + " SP, " + baseDamage + " DMG, " + lifeStealPercentage + "% LS" + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Spell otherSpell = (Spell) obj;

        // Field Comparison
        if (getPointsToCast() != otherSpell.getPointsToCast())
            return false;
        if (getBaseDamage() != otherSpell.getBaseDamage())
            return false;
        if (getLifeStealPercentage() != otherSpell.getLifeStealPercentage())
            return false;

        return this.name.equals(otherSpell.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + pointsToCast;
        result = 31 * result + baseDamage;
        result = 31 * result + (int)lifeStealPercentage;

        return result;
    }

    @Override
    public int execute(Character self, Character target, int damage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'"
    }

    @Override
    public int calculateDamage(Character self, double variance) {
        // Variance cannot be negative
        if (variance < 0.0) {
            throw new IllegalArgumentException("Damage variance cannot be negative (less than 0.0).");
        }

        // Need a new Random object for calculating pseudorandom damage.
        Random rand = new Random();

        // Calculate the total buff percentage from the clan's base damage buff and spell damage buff.
        Clan clan = self.getClan();
        double baseDamageBuffPercentage = clan.getDamageBuffPercentage();
        double spellDamageBuffPercentage = clan.getSpellDamageBuffPercentage();

        // The number to multiply the randomly calculated damage by (example: if damage was 1 and buffs were 30%, 1 * 1.3).
        double totalDamageMultiplier = 1.0 + (baseDamageBuffPercentage + spellDamageBuffPercentage);

        // Minimum & maximum range for the base damage (lower bound, upper bound)
        int minBaseDamage = (int) Math.round(baseDamage * (1 - variance));
        int maxBaseDamage = (int) Math.round(baseDamage * (1 + variance));

        // Get a pseudorandom damage in the range [minBaseDamage, maxBaseDamage].
        int randomDamage = rand.nextInt(maxBaseDamage - minBaseDamage + 1) + minBaseDamage;

        // The randomly generated damage multiplied by the total damage multiplier, rounded and then converted to an integer.
        int damageWithBuffs = (int)Math.round(randomDamage * totalDamageMultiplier);

        return damageWithBuffs;
    }
}
