# RPG Battle Game

The following is a mini RPG-style battle game with characters that can either be mages or warriors.  All characters have the ability to partake in battle against each other, but a character's dynamic type must be of one of its subclasses (i.e., must be a mage or warrior).  Mages can own and cast spells, whereas warriors use weapons.  All characters have the typical properties expected of RPG characters (xp, level, health, name).

## Generic Character Information
All characters have health, XP, a level, a clan, and a name.  A character's clan provides certain buffs, and the remaining properties are self explanatory.  A Character's XP is (intentionally) left for the person writing their own battle game to manage.  This allows for increased customizability and flexibility so that there is no strictly imposed change in XP based on any action.

The below formulas are used to calculate level and maximum health, with health being a volatile property (subject to change at any given point):

- Level: `experiencePoints / 100`
- Maximum Health: `100 + (getLevel() * 10)`

## Mage Class
Mages differ from warriors in that they possess a set of spells that can be modified at any point in time.  They can only cast spells that are in their set of known spells, and every spell costs some amount of spell points to cast.

A mage's maximum spell points are calculated by the following formula:
- `25 + (getLevel() * 5)`, where getLevel() is the mage's level.

When a mage casts a spell, they lose spell points in accordance to the cost of the spell that they are casting.  If they do not have enough spell points to cast that spell, they will be unable to do so, and their spell points cannot dip below zero or go above the maximum spell points.


## Warrior Class
Warriors, unlike mages, cannot possess multiple weapons, but must only carry one weapon at a time.  This is done for the sake of simplicity and also because it is more logical for a mage to be able to cast multiple spells than for a warrior to be able to use multiple weapons at the same time.

Warriors do not possess spell points, but instead, they possess stamina.  Similarly to mages, swinging/attacking with their weapon results in a loss of stamina in accordance to the weapon's cost per attack.  Warriors, too, cannot swing their weapon if it costs more stamina than they possess, and their stamina will never be less than zero or greater than their maximum stamina.

The formula for a warrior's maximum stamina is:
- `25 + (getLevel() * 5)`

## Weapons & Spells
Weapons and spells are extremely similar in function.  Their most important properties are their base damage, energy cost (SP or stamina), damage variance, and life steal percentage.

Base damage and damage variance are used to calculate the amount of damage that an attack deals, with the base being the starting point and the variance (between 0 and 100% or [0.0, 1.0]) being used for randomness.

The life steal percentage of an attack is converted into health for the attacker, so if the life steal percentage was 20% with an attack that deals 10 damage, the attacker would gain 2 health points.

> Additionally, no weapon can be used by a non-warrior, and no spell can be cast by a non-mage.

## Battles
Battles must be between two different characters, and after each turn in a battle (with there being precisely one attacker and precisely one defender), an AttackResult is returned, containing information regarding the attack.

## Overview with Usage Examples

> ### Clan Overview
- `Clan`: An attribute of every character. Contains varying buffs that can affect battle performance.

Example of creating clans:
```java
    Clan pendragon = new Clan("Pendragon", 0.25, 0.15, 0, 0.8);
    Clan headmaster = new Clan("Headmaster", 0.3, 0.2, 0.75, 0);
```

> ### Character Overview

- `Name`: Stores a character's first and last name.
- `Character`: A base class extended by all specific character types.
- `Mage`: A subclass of Character that battles using spells.
- `Warrior`: A subclass of Character that battles using a weapon.

Example of creating characters:
```java
    // Creates a Warrior named King Arthur with the spear weapon.
    Warrior kingArthur = new Warrior(new Name("King Arthur", "Pendragon"), pendragon, spear);

    // Creates a Mage named Albus Dumbledore.
    Mage albusDumbledore = new Mage(new Name("Albus", "Dumbledore"), headmaster);

    // Experience points determine level.
    kingArthur.addExperiencePoints(10000);
    albusDumbledore.addExperiencePoints(8000);

    // Mages can learn spells.
    albusDumbledore.addSpell(fireball);
    albusDumbledore.addSpell(transfiguration);

    // Warriors can swap their equipped weapon.
    kingArthur.updateWeapon(excalibur);
```

> ### Weapons & Spells Overview
- `Attack`: An interface implemented by both Weapon and Spell.
- `Weapon`: Used by warriors to perform attacks.
- `Spell`: Used by mages to perform attacks.

Example of creating spells & weapons:
```java
    // Creates a spell named "Fireball" that costs 25 spell points to cast, has a base damage of 100, a life steal percentage of 50%, and damage variance of 20%
    Spell fireball = new Spell("Fireball", 25, 100, 0.5, 0.2);
    
    // Creates a weapon named "Axe" that costs 25 stamina to swing, has a base damage of 75, a life steal percentage of 20%, and damage variance of 15%
    Weapon axe = new Weapon("Axe", 25, 75, 0.2, 0.15);
```

Example of checking whether a character can use an attack:
```java
    if (kingArthur.canUse(kingArthur.getWeapon())) {
        System.out.println("Arthur can attack with " + kingArthur.getWeapon().getName());
    }

    if (albusDumbledore.canUse(fireball)) {
        System.out.println("Dumbledore can cast Fireball.");
    }
```

> ### Battle Overview
- `BattleSim`: A class used to create battles between two unique characters. Every attack turn returns an `AttackResult`.
- `AttackResult`: A data structure containing information on the outcome of an attack amidst a battle.

Example of starting and progressing a battle:
```java
    BattleSim battle = new BattleSim(kingArthur, albusDumbledore);

    AttackResult firstTurn = battle.performTurn(kingArthur, albusDumbledore, kingArthur.getWeapon());
    System.out.println("Arthur dealt " + firstTurn.getDamageDealt() + " damage.");

    if (!battle.isBattleOver() && albusDumbledore.canUse(fireball)) {
        AttackResult secondTurn = battle.performTurn(albusDumbledore, kingArthur, fireball);
        System.out.println("Dumbledore dealt " + secondTurn.getDamageDealt() + " damage.");
    }

    if (battle.isBattleOver()) {
        System.out.println("Winner: " + battle.getWinner().getName().getFullName());
    }
```

`AttackResult` gives access to:
- The attacker and target
- The attack that was used
- The damage dealt
- The attacker and target health after the turn
- Whether the target was defeated by that attack

## Main Demo

The playable example for this project is in `src/BattleGame.java`. It creates:

- Two clans: `Pendragon` and `Headmaster`
- Two characters: `King Arthur Pendragon` and `Albus Dumbledore`
- Two spells: `Fireball` and `Transfiguration`
- Two weapons: `Spear` and `Excalibur`

The demo then lets the player:

- Optionally equip Excalibur
- Battle Dumbledore turn by turn
- Rest to recover stamina when needed
- See the winner once one character reaches 0 health
