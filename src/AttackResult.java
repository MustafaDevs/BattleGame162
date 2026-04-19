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
    // - damageDealt >= 0
    // - attackerHealthA

    /** The character performing the attack. */
    private final Character attacker;
    private final Character target;
    private final Attack attackUsed;
    private final int damageDealt;
    private final int attackerHealthAfter;
    private final int targetHealthAfter;
    private final boolean targetDefeated;

    /**
     * 
     * @param attacker
     * @param target
     * @param attackUsed
     * @param damageDealt
     * @param attackerHealthAfter
     * @param targetHealthAfter
     * @param targetDefeated
     */
    public AttackResult(Character attacker, Character target, Attack attackUsed, int damageDealt,
            int attackerHealthAfter, int targetHealthAfter, boolean targetDefeated) {
        this.attacker = attacker;
        this.target = target;
        this.attackUsed = attackUsed;
        this.damageDealt = damageDealt;
        this.attackerHealthAfter = attackerHealthAfter;
        this.targetHealthAfter = targetHealthAfter;
        this.targetDefeated = targetDefeated;
    }

    
}
