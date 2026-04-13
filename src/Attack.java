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
     * @return The amount of damage dealt to the target.
     */
    int execute(Character attacker, Character target);
}
