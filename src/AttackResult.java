/**
 * Stores information on the result of an attack (during a battle).
 * 
 * @author Mustafa Faqiryar
 */
public class AttackResult {
    // Class Invariants:
    // - attacker cannot be null
    // - target cannot be null
    // - attackUsed cannot be null
    // - attacker != target
    // - damageDealt > 0
    // - attackerHealthAfter >= 0
    // - targetHealthAfter >= 0
    // - attackerHealthAfter <= attacker.getMaxHealth() (even if the attacker gains health, it cannot exceed their max health)
    // - targetHealthAfter < target.getMaxHealth() (meaning, they must have lost at least some health)
    // - targetDefeated is true if targetHealthAfter == 0.

    /** The character performing the attack. */
    private final Character attacker;
    /** The character receiving the attack. */
    private final Character target;
    /** The attack being dealt by the attacker. */
    private final Attack attackUsed;
    /** The amount of damage dealt to the target from the attack. */
    private final int damageDealt;
    /** The amount of health the attacker has after performing the attack. */
    private final int attackerHealthAfter;
    /** The amount of health the target has after receiving the attack. */
    private final int targetHealthAfter;

    /**
     * Creates a new AttackResult storing the outcome of an attack.
     * 
     * @param attacker The character performing the attack.
     * @param target The character receiving the attack.
     * @param attackUsed The attack that was used.
     * @param damageDealt The amount of damage dealt by the attack.
     * @param attackerHealthAfter The attacker's health after the attack was performed.
     * @param targetHealthAfter The target's health after the attack was performed.
     */
    public AttackResult(Character attacker, Character target, Attack attackUsed, int damageDealt,
            int attackerHealthAfter, int targetHealthAfter) {
        if (attacker == null) {
            throw new IllegalArgumentException("The attacker cannot be null.");
        }
        if (target == null) {
            throw new IllegalArgumentException("The target cannot be null.");
        }
        if (attackUsed == null) {
            throw new IllegalArgumentException("The attack used cannot be null.");
        }
        if (attacker == target) {
            throw new IllegalArgumentException("The attacker and target cannot be the same character.");
        }
        if (damageDealt <= 0) {
            throw new IllegalArgumentException("Damage dealt must be a positive integer (>= 1).");
        }
        if (attackerHealthAfter < 0) {
            throw new IllegalArgumentException("The attacker's health after the attack cannot be negative.");
        }
        if (targetHealthAfter < 0) {
            throw new IllegalArgumentException("The target's health after the attack cannot be negative.");
        }
        if (attackerHealthAfter > attacker.getMaxHealth()) {
            throw new IllegalArgumentException("The attacker's health after the attack cannot exceed their maximum health.");
        }
        if (targetHealthAfter >= target.getMaxHealth()) {
            throw new IllegalArgumentException("The target's health after the attack cannot exceed their maximum health.");
        }

        this.attacker = attacker;
        this.target = target;
        this.attackUsed = attackUsed;
        this.damageDealt = damageDealt;
        this.attackerHealthAfter = attackerHealthAfter;
        this.targetHealthAfter = targetHealthAfter;
    }

    /**
     * Gets the attacking character (attacker).
     * 
     * @return The character who performed the attack.
     */
    public Character getAttacker() {
        return attacker;
    }

    /**
     * Gets the character who received the attack (target).
     * 
     * @return The character who received the attack.
     */
    public Character getTarget() {
        return target;
    }

    /**
     * Gets the attack that was dealt by the attacker.
     * 
     * @return The attack used by the attacker on the target.
     */
    public Attack getAttack() {
        return attackUsed;
    }

    /**
     * Gets the amount of damage inflicted on the target by the attacker.
     * 
     * @return The amount of damage the target took from the attack.
     */
    public int getDamageDealt() {
        return damageDealt;
    }

    /**
     * Gets the health of the attacker after performing the attack.
     * The attacker's health may increase as a result of the attack if their weapon has life steal.
     * 
     * @return The attacker's health after the attack.
     */
    public int getAttackerHealthAfter() {
        return attackerHealthAfter;
    }

    /**
     * Gets the health of the target after receiving the attack.
     * 
     * @return The target's health after the attack.
     */
    public int getTargetHealthAfter() {
        return targetHealthAfter;
    }

    /**
     * Gets whether the target was defeated following this attack (new health == 0).
     * 
     * @return Whether the target was defeated, equivalent to whether they have zero health.
     */
    public boolean isTargetDefeated() {
        return targetHealthAfter == 0;
    }
}
