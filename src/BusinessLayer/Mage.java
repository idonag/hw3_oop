package BusinessLayer;

public class Mage extends Player{
    private Resource mana;
    private Integer manaCost;
    private Resource spellPower;
    private Integer hitsCount;
    private Integer abilityRange;
    public Mage(String name, Integer helthPool, Integer attackPoints, Integer defensePoints,Integer manaPool,Integer spellPower,Integer manaCost,Integer hitsCount,Integer abilityRange){
        super(name,  helthPool, attackPoints,  defensePoints);
        mana = new Resource(manaPool/4,manaPool);
        this.spellPower = new Resource(spellPower);
        this.manaCost = manaCost;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }
    public void levelUp() {
        super.levelUp();
        mana.increaseMaxCapacitiy(10 * playerLevel);
        mana.increaseAmount(mana.maxCapacity/4);
        spellPower.increaseAmount(10 * playerLevel);
    }
    public void abilityCast(){
        mana.reduceAmount(manaCost);
        Integer hits = 0;
        //complicated while that needs to be done...
        while (hits < hitsCount){
            //as long as there is an enemy withing ability range distance, select one randomly
            // and hit him with amount worth to spell power
        }
    }
    public void gameTick() {
        mana.increaseAmount(playerLevel);
    }

}
