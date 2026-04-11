
/**
 * A class representing a Character in the game. Has a name, clan, and health.
 */
public abstract class Character implements Comparable<Character> {
	/**
	 * Class Invariants:
	 * - 0 <= Health <= 100 + (getLevel() * 10)
	 * - 0 <= experiencePoints
	 */

	/** The name of the character. */
	private final Name name;
	/** The clan of the character. */
	private final Clan clan;
	/** The health (hit points) of the character. */
	private int health;
	/** The character's experience points. */
	private int experiencePoints;

	/**
	 * Creates a new character with the specified name and clan.
	 * 
	 * @param name The name of the character.
	 * @param clan The clan of the character.
	 * @postcondition A new character will be created with the specified name and
	 *                clan. The character's health will be initialized to 100 by
	 *                default.
	 */
	public Character(Name name, Clan clan) {
		this.name = name;
		this.clan = clan;
		this.health = 100;
		this.experiencePoints = 0;
	}

	/**
	 * Gets the name of the character.
	 * 
	 * @return The name of the character.
	 */
	public Name getName() {
		return name;
	}

	/**
	 * Gets the clan of the character.
	 * 
	 * @return The character's clan.
	 */
	public Clan getClan() {
		return clan;
	}

	/**
	 * Gets the level of the player.
	 * 
	 * @return The player's level. Equivalent to the player's experience points
	 *         divided by 100 (rounded down).
	 */
	public int getLevel() {
		return experiencePoints / 100; // Level is determined by experience points, with 100 XP per level.
	}

	/**
	 * Gets the health of the character.
	 * 
	 * @return The health of the character.
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Gets the maximum health of the character.
	 * 
	 * @return The character's maximum health (100 + 10 * level)
	 */
	public int getMaxHealth() {
		return 100 + (getLevel() * 10); // Max health increases by 10 per level.
	}

	/**
	 * Gets the experience points of the character.
	 * 
	 * @return The character's experience points.
	 */
	public int getExperiencePoints() {
		return experiencePoints;
	}

	/**
	 * Sets the health of the player to a hard set number.
	 * 
	 * @param newHp The new number that the character's health will be set to.
	 * @postcondition The character's health will be equal to newHp, unless one of
	 *                newHp falls into one of these two cases:
	 *                (1). If newHp < 0, the character's health will be set to 0.
	 *                (2). If newHp > getMaxHealth(), health will be set to
	 *                getMaxHealth().
	 */
	private void setHealth(int newHp) {
		if (newHp < 0) {
			health = 0;
		} else if (newHp > getMaxHealth()) {
			health = getMaxHealth();
		} else {
			health = newHp;
		}
	}

	/**
	 * Increases the character's current experiencePoints by some given amount.
	 * 
	 * @param xpToAdd The amount of experience points that should be added onto the character's current experience points.  Must be a positive integer.
	 * @throws IllegalArgumentException if xpToAdd is less than or equal to 0 (i.e., xpToAdd is not a positive integer)
	 */
	private void addExperiencePoints(int xpToAdd) {
		if (xpToAdd <= 0) {
			throw new IllegalArgumentException("The number of experience points to add must be a positive integer.");
		}
		experiencePoints += xpToAdd;
	}

	/**
	 * Subtracts health from the character.
	 * 
	 * @param amount The amount of health to subtract. Must be a positive integer.
	 * @postcondition The character's health will be equal to their current health
	 *                minus
	 *                the amount to subtract. If the amount to subtract by
	 *                is greater than or equal to the character's current health,
	 *                their health will be set to 0.
	 * @throws IllegalArgumentException if hpToRemove is not a positive integer.
	 */
	public void subtractHealth(int hpToRemove) {
		if (hpToRemove < 1) {
			throw new IllegalArgumentException("The amount of health to be removed must be a positive integer.");
		}
		setHealth(getHealth() - hpToRemove);
	}

	/**
	 * Adds health to the character.
	 * 
	 * @param hpToAdd The amount of health points to add to the character. Must be a
	 *                positive integer.
	 * @postcondition The character's health will be equal to their current health
	 *                plus
	 *                the amount to add. If the amount to add by
	 *                is greater than or equal to the character's current health,
	 *                their health will be set to 0.
	 * @throws IllegalArgumentException if hpToAdd is not a positive integer.
	 */
	public void addHealth(int hpToAdd) {
		if (hpToAdd < 1) {
			throw new IllegalArgumentException("The amount of health to be added must be a positive integer.");
		}
		setHealth(getHealth() + hpToAdd);
	}

	/**
	 * Gets the character's specific type.
	 * 
	 * @return The character's class/type (mage, warrior, etc.)
	 */
	public abstract String getCharacterType();

	/**
     * Checks if a given character is able to perform an attack.
     * 
     * @param attack The attack to check if the character can use.
     * @return true if the character attacker can perform the attack.
     */
    public abstract boolean canUse(Attack attack);

	@Override
	public String toString() {
		return String.format("%s (%s, Level: %d, XP: %d, HP: %d) from the %s clan.)", name.getFullName(),
				getCharacterType(), getLevel(), experiencePoints, health, clan.getName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		Character otherCharacter = (Character) obj;

		if (this.getLevel() != otherCharacter.getLevel()) {
			return false;
		}
		if (this.experiencePoints != otherCharacter.experiencePoints) {
			return false;
		}
		if (!this.name.equals(otherCharacter.name)) {
			return false;
		}

		return this.clan.equals(otherCharacter.clan);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + clan.hashCode();
		result = 31 * result + health;
		result = 31 * result + experiencePoints;

		return result;
	}

	@Override
	public int compareTo(Character other) {
		// Character comparison order of precedence:
		// Level (1) -> XP (2) -> Name (3) -> Clan (4) -> Equal (CONCLUSION).
		// We will not take health into account for the sake of this comparison
		// since it is a volatile property.

		if (this.getLevel() != other.getLevel()) {
			return other.getLevel() - this.getLevel(); //
		} else if (this.experiencePoints != other.experiencePoints) {
			return other.experiencePoints - this.experiencePoints;
		} else if (!this.name.equals(other.name)) {
			// Using getFullName() allows us to compare using the String.compareTo() method
			// rather than having our own implementation, which I find unnecessary (though that is
			// definitely a valid design choice).
			return this.name.getFullName().compareTo(other.name.getFullName());
		} else {
			// After exhausting all other comparisons, the characters' equality
			// comes down to their clan names (alphabetically).

			// Though we could compare clan buffs, determining which buffs are more
			// important than others
			// is a bit tricky and, in my opinion, not necessary.
			return this.clan.getName().compareTo(other.clan.getName());
		}
	}
}
