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

    public int getBaseDamage() {
        return baseDamage;
    }

    public double getLifeStealPercentage() {
        return lifeStealPercentage;
    }
}
