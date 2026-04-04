/**
 * A class representing a Character in the game.  Has a name, clan, and health.
 */
public abstract class Character {
	/** The name of the character. */
	private Name name;
	/** The clan of the character. */
	private Clan clan;
	/** The health (hit points) of the character. */
	private int health;
	/** The character's XP. */
	private int xp;
	/** The character's level. */
	private int level;

	/**
	 * Creates a new character with the specified name and clan.
	 * 
	 * @param name The name of the character.
	 * @param clan The clan of the character.
	 * @postcondition A new character will be created with the specified name and clan.  The character's health will be initialized to 100 by default.
	 */
	public Character(Name name, Clan clan) {
		this.name = name;
		this.clan = clan;
		this.health = 100;
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
	 * @return
	 */
	public Clan getClan() {
		return clan;
	}

	/** Gets the level of the player.
	 * 
	 * @return The player's level.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the health of the character.
	 * 
	 * @return The health of the character.
	 */
	public int getHealth() {
		return health;
	}

	@Override
	public String toString() {
		return name.getFullName() + ", belonging to the " + clan.getName() + " clan";
	}
}
