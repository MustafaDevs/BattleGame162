import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class BattleGame {
    public static void main(String[] args) throws Exception {
        // Creates a spell named "Fireball" that costs 25 spell points to cast, has a
        // base damage of 100, a life steal percentage of 50%, and damage variance of
        // 20%
        Spell fireball = new Spell("Fireball", 25, 100, 0.5, 0.2);
        // Creates a spell named "Transfiguration" that costs 50 spell points to cast,
        // has a base damage of 350, a life steal percentage of 5%, and damage variance
        // of 25%
        Spell transfiguration = new Spell("Transfiguration", 50, 250, 0.05, 0.25);

        // Creates a weapon named "Spear" that costs 25 stamina to swing, has a base
        // damage of 75, a life steal percentage of 20%, and damage variance of 15%
        Weapon spear = new Weapon("Spear", 25, 75, 0.2, 0.15);
        
        // Creates a new weapon named "Excalibur" that costs 25 stamina to swing, has a
        // base damage of 350, a life steal percentage of 10%, and damage variance of 20%.
        Weapon excalibur = new Weapon("Excalibur", 25, 350, 0.1, 0.2);

        // Creates a clan named "Pendragon" with a 25% damage buff, 15% health buff, 0%
        // spell damage buff, and 80% weapon damage buff.
        Clan pendragon = new Clan("Pendragon", 0.25, 0.15, 0, 0.8);
        
        // Creates a clan named Headmaster with a 30% damage buff, 20% health buff, 75%
        // spell damage buff, and 0% weapon damage buff.
        Clan headmaster = new Clan("Headmaster", 0.3, 0.2, 0.75, 0);

        // Creates a Warrior named King Arthur with the spear weapon.
        Warrior kingArthur = new Warrior(new Name("King Arthur", "Pendragon"), pendragon, spear);
        
        // Creates a Mage named Albus Dumbledore.
        Mage albusDumbledore = new Mage(new Name("Albus", "Dumbledore"), headmaster);

        kingArthur.addExperiencePoints(10000);
        albusDumbledore.addExperiencePoints(8000);

        // Gives Dumbledore the fireball and transfiguration spells, which allow him to use them during battle.
        albusDumbledore.addSpell(fireball);
        albusDumbledore.addSpell(transfiguration);

        // A sort of "inventory" of all available weapons. King Arthur does not automatically have access
        // to these weapons because warriors can only equip one weapon at a time and do not have an
        // inventory set the way that mages do.
        Set<Weapon> allWeapons = new HashSet<>();
        allWeapons.add(spear);
        allWeapons.add(excalibur);

        // A little simulation in which the user plays as King Arthur and gets to fight Dumbledore.
        Scanner input = new Scanner(System.in);

        // General information on the user's character as well as Dumbledore.
        System.out.println("You are King Arthur Pendragon.");
        System.out.println("Your opponent is Albus Dumbledore.\n");
        System.out.println("Arthur: " + kingArthur);
        System.out.println("Dumbledore: " + albusDumbledore);
        System.out.println("\nDumbledore knows the following spells: " + albusDumbledore.getSpells());

        System.out.print("\nWould you like to test your worthiness to equip Excalibur? (Y/N): ");
        String answer = input.next();

        // There is technically no real "worthiness" test.  The user will be able to equip excalibur
        // so long as it is in the set containing all available wepaons.
        if (answer.equalsIgnoreCase("Y")) {
            if (allWeapons.contains(excalibur)) {
                kingArthur.updateWeapon(excalibur);
                System.out.println("You are worthy! Successfully equipped Excalibur.");
            }
        } else {
            System.out.println("You kept your spear.");
        }

        System.out.print("\nWould you like to battle Dumbledore? (Y/N): ");
        answer = input.next();

        if (answer.equalsIgnoreCase("Y")) { // The user chooses to battle Dumbledore.
            BattleSim battle = new BattleSim(kingArthur, albusDumbledore);
            Random rand = new Random();

            while (!battle.isBattleOver()) {
                System.out.println("\nArthur HP: " + kingArthur.getHealth() + ", Stamina: " + kingArthur.getStamina());
                System.out.println("Dumbledore HP: " + albusDumbledore.getHealth() + ", Spell Points: "
                        + albusDumbledore.getSpellPoints());
                System.out.print("\nAttack with " + kingArthur.getWeapon().getName() + "? (Y/N): ");
                answer = input.next();
                
                if (answer.equalsIgnoreCase("Y")) { // The user wants to perform an attack
                    if (kingArthur.canUse(kingArthur.getWeapon())) {
                        AttackResult result = battle.performTurn(kingArthur, albusDumbledore, kingArthur.getWeapon());
                        System.out.println("- You dealt " + result.getDamageDealt() + " damage.");
                    } else {
                        System.out.println("You do not have enough stamina, so you rest and recover "
                                + kingArthur.getMaxStamina() / 10 + " stamina.");
                        kingArthur.addStamina(20);
                    }
                } else { // The user chose not to attack.
                    System.out.println("You rested and recovered " + kingArthur.getMaxStamina() / 10 + " stamina.");
                    kingArthur.addStamina(10);
                }

                if (!battle.isBattleOver()) {
                    // Dumbledore will make a pseudorandom pick between fireball & transfiguration,
                    // but if he cannot use both, then he will pick whichever one he can use.
                    boolean canUseFireball = albusDumbledore.canUse(fireball);
                    boolean canUseTransfiguration = albusDumbledore.canUse(transfiguration);

                    // The logic in this block is a little bit redundant given the current properties
                    // of the fireball & transfiguration spells, but writing it this way makes it
                    // easier to update the spells without breaking everything in here.
                    if (canUseFireball || canUseTransfiguration) {
                        Attack spellToUse;

                        if (canUseFireball && canUseTransfiguration) {
                            spellToUse = rand.nextBoolean() ? fireball : transfiguration;
                        } else {
                            spellToUse = canUseFireball ? fireball : transfiguration;
                        }

                        AttackResult result = battle.performTurn(albusDumbledore, kingArthur, spellToUse);
                        System.out.println("- Dumbledore cast " + ((Spell) spellToUse).getName()
                                + " and dealt " + result.getDamageDealt() + " damage.");
                    } else {
                        System.out.println(
                                "Dumbledore did not have enough spell points to attack, so he rested and recovered "
                                        + albusDumbledore.getMaxSpellPoints() / 10 + " spell points.");
                        albusDumbledore.addSpellPoints(albusDumbledore.getMaxSpellPoints() / 10);
                    }
                }
            }

            System.out.println("\nBattle over!");
            System.out.println("Winner: " + battle.getWinner().getName().getFullName() + ".  Thank you for playing!");
        } else {
            System.out.println("You decided not to battle Dumbledore.  Thank you for playing!");
        }

        // The scanner will be closed here because we know for certain that the program has finished running.
        input.close();
    }
}
