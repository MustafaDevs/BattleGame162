public class BattleSim {
    private final Character characterOne;
    private final Character characterTwo;

    /**
     * Creates a new battle.
     * 
     * @param characterOne A character participating in the battle. Cannot be null.
     * @param characterTwo A character participating in the battle. Cannot be null/
     * @postcondition A new battle will be created between the two characters.
     */
    public BattleSim(Character characterOne, Character characterTwo) {
        if (characterOne == null || characterTwo == null) {
            throw new IllegalArgumentException("BattleSim characters cannot be null.");
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
     * @return A TurnResult object containing information on 
     */
    public AttackResult performTurn(Character attacker, Character defender, Attack attack) {
        if (attacker != characterOne && attacker != characterTwo) {
            throw new IllegalArgumentException("The provided attacker is not part of this battle (neither character one nor character two).");
        }
        if (defender != characterOne && defender != characterTwo) {
            throw new IllegalArgumentException("The provided defender is not part of the battle (neither character one nor character two).");
        }
        if (attacker == defender) {
            throw new IllegalArgumentException("The attacker and the defender cannot be the same character.");
        }

        return attacker.performAttack(defender, attack);
    }

    public boolean isBattleOver() {
        return characterOne.getHealth() == 0 || characterTwo.getHealth() == 0;
    }

    public Character getWinner() {
        if (!isBattleOver()) {
            return null;
        }
        return characterOne.getHealth() > 0 ? characterOne : characterTwo;
    }
}
