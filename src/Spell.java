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
    public void execute(Character self, Character target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public int calculateDamage(Character self, double variance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateDamage'");
    }
}
