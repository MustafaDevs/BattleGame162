/**
 * A class representing a Character in the game. Has a name, clan, and health.
 */
public abstract class Character implements Comparable<Character> {
	/** The name of the character. */
	private Name name;
	/** The clan of the character. */
	private Clan clan;
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
	 * @return The player's level.  Equivalent to the player's experience points divided by 100 (rounded down).
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
	 * Gets the experience points of the character.
	 * 
	 * @return The character's experience points.
	 */
	public int getExperiencePoints() {
		return experiencePoints;
	}

	public String getCharacterType() {
		return "Generic Character";
	}

	@Override
	public String toString() {
		return String.format("%s (%s, Level: %d, XP: %d, Health: %d) from the %s clan.)", name.getFullName(), getCharacterType(), getLevel(), experiencePoints, health, clan.getName());
	}

	@Override
	public int compareTo(Character other) {
		// Character comparison order of precedence:
		// Level (1) -> XP (2) -> Name (3) -> Clan (4) -> Equal (CONCLUSION).
		// We will not take health into account for the sake of this comparison since it
		// is a volatile property.

		if (this.getLevel() != other.getLevel()) {
			return other.getLevel() - this.getLevel(); //
		} else if (this.experiencePoints != other.experiencePoints) {
			return other.experiencePoints - this.experiencePoints;
		} else if (!this.name.equals(other.name)) {
			return this.name.getFullName().compareTo(other.name.getFullName());
		} else {
			// After exhausting all other comparisons, the characters are equal if their clans are equal.
			return this.clan.getName().compareTo(other.clan.getName());
		}
	}
}
