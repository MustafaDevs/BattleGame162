public class BattleSim {
    // Class Invariants:
    // - characterOne cannot be null
    // - characterTwo cannot be null
    // - characterOne != characterTwo

    private final Character characterOne;
    private final Character characterTwo;

    /**
     * Creates a new battle.
     * 
     * @param characterOne A character participating in the battle. Cannot be null.
     * @param characterTwo A character participating in the battle. Cannot be null.
     * @postcondition A new battle will be created between the two characters.
     */
    public BattleSim(Character characterOne, Character characterTwo) {
        if (characterOne == null || characterTwo == null) {
            throw new IllegalArgumentException("The characters in a battle cannot be null.");
        }
        if (characterOne == characterTwo) {
            throw new IllegalArgumentException("A character cannot battle themselves.");
        }

        this.characterOne = characterOne;
        this.characterTwo = characterTwo;
    }

    /**
     * Gets the first character, based on order provided in the constructor.
     * 
     * @return The first character (character one).
     */
    public Character getCharacterOne() {
        return characterOne;
    }

    /**
     * Gets the second character, based on order provided in the constructor.
     * 
     * @return The second character (character two).
     */
    public Character getCharacterTwo() {
        return characterTwo;
    }

    /**
     * Performs a turn in the battle (one character attacks the other).
     * 
     * @param attacker The character who should be using the attack.
     * @param defender The character who should be receiving the attack.
     * @param attack The attack that should be performed by the attacking character. 
     * @return An AttackResult object containing information on the attack.
     * @throws IllegalArgumentException if the attacker or defender are not one of the two characters
     *                  participating in the battle, or if the attacker and the defender are the same character.
     * @throws IllegalStateException if the method is called when the battle has already ended (meaning, one 
     *                  character has lost).  A character has lost if they have zero health.
     */
    public AttackResult performTurn(Character attacker, Character defender, Attack attack) {
        if (isBattleOver()) {
            throw new IllegalStateException("Cannot perform a turn because the battle is already over.");
        }
        if (attacker != characterOne && attacker != characterTwo) {
            throw new IllegalArgumentException("The provided attacker is not part of this battle (neither character one nor character two).");
        }
        if (defender != characterOne && defender != characterTwo) {
            throw new IllegalArgumentException("The provided defender is not part of the battle (neither character one nor character two).");
        }
        if (attacker == defender) {
            throw new IllegalArgumentException("The attacker and the defender cannot be the same character.");
        }

        int damage = attacker.performAttack(defender, attack);

        return new AttackResult(attacker, defender, attack, damage, attacker.getHealth(), defender.getHealth());
    }

    /**
     * Gets whether the battle is over.  True if either character has been defeated (meaning, if either has 0 health).
     * 
     * @return Whether the battle is over (if either character has 0 health).
     */
    public boolean isBattleOver() {
        return characterOne.getHealth() == 0 || characterTwo.getHealth() == 0;
    }

    /**
     * Gets the winner of the battle, or null if there is no winner yet.
     * 
     * @return The Character who won the battle, or null if neither character has lost yet (neither at 0 health).
     */
    public Character getWinner() {
        if (!isBattleOver()) {
            return null;
        }

        return characterOne.getHealth() > 0 ? characterOne : characterTwo;
    }
}
