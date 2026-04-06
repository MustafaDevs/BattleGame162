public class Spell {
    private String name;
    private int pointsToCast;
    private int baseDamage;
    private double lifeStealPercentage;

    public Spell(String name, int pointsToCast, int baseDamage, double lifeStealPercentage) {
        this.name = name;
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

    @Override
    public String toString() {
        String result = name + " (Cost: " + pointsToCast + " SP)" + " | Base Damage: " + baseDamage + " | Life Steal (%): " + lifeStealPercentage;
        return result;
    }
}
