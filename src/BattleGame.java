public class BattleGame {
    public static void main(String[] args) throws Exception {
        Spell fireball = new Spell("Fireball", 25, 100, 0.5, 0.2);
        //System.out.println(fireball);
        Spell snowball = new Spell("Snowball", 5, 15, 0.25, 0.8);
        Clan mageClan = new Clan("Mage Clan", 15, 5, 10, 0);

        Mage elementMage = new Mage(new Name("Gandalf", "The Grey"), mageClan);
        elementMage.addSpell(fireball);
        elementMage.addSpell(snowball);
        System.out.println(elementMage.getSpells());
    
    }
}
