/**
 * An interface representing an attack.
 */
public interface Attack {
    /**
     * Executes an attack on another character.
     * 
     * @param attacker The character that is performing the attack.
     * @param target The character that is receiving the attack.
     * @throws IllegalArgumentException if attacker == target (meaning, they are the exact same character in memory)
     */
    void execute(Character attacker, Character target);

    /**
     * Calculates the damage that a hypothetical attack may perform.  Has damage variance.
     * 
     * @param attacker The character performing the attack.
     * @param variance The percentage of variance in the min/max potential damage (0.5 = 50%, 0.02 = 2%)
     * @return A rounded down integer value representing the amount of damage performed by this attack.
     * @throws IllegalArgumentException if variance is less than 0.0
     */
    int calculateDamage(Character attacker, double variance);
}
