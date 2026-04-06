/**
 * An interface representing an attack.  Implemented differently by weapons/spells rather than by characters themselves.
 */
public interface Attack {
    /**
     * Executes an attack on another character.
     * 
     * @param self The character that is performing the attack.
     * @param target The character that is receiving the attack.
     * @throws IllegalArgumentException if self == target (meaning, they are the exact same character in memory)
     */
    void execute(Character self, Character target);

    /**
     * Calculates the damage that a hypothetical attack may perform.  Has damage variance.
     * 
     * @param self The character performing the attack.
     * @param variance The percentage of variance in the min/max potential damage (0.5 = 50%, 0.02 = 2%)
     * @return A rounded down integer value representing the amount of damage performed by this attack.
     */
    int calculateDamage(Character self, double variance);

    /**
     * Checks if a given character is able to perform an attack.
     * 
     * @param self The character that is verifying its ability to attack.
     * @return true if the attack can be performed by the character.
     */
    boolean canAttack(Character self);
}
