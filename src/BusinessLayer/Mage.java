package BusinessLayer;

public class Mage extends Player{
    private Integer manaPool;
    private Integer currentMana;
    private Integer manaCost;
    private Integer spellPower;
    private Integer hitsCount;
    private Integer abilityRange;
    public Mage(Character c, String name, Integer helthPool, Integer attackPoints, Integer defensePoints,Integer manaPool,Integer spellPower,Integer manaCost,Integer hitsCount,Integer abilityRange){
        super(c, name,  helthPool, attackPoints,  defensePoints);
        this.manaPool = manaPool;
        currentMana = manaPool/4;
        this.spellPower = spellPower;
        this.manaCost = manaCost;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
    }
    public void levelUp() {
        super.levelUp();
        manaPool = manaPool + 10 * playerLevel;
        currentMana = Math.min(currentMana + manaPool/4,manaPool);
        spellPower = spellPower + 10 * playerLevel;
    }
    public void abilityCast(){
        currentMana = currentMana - manaCost;
        Integer hits = 0;
        //complicated while that needs to be done...
        while (hits < hitsCount){
            //as long as there is an enemy withing ability range distance, select one randomly
            // and hit him with amount worth to spell power
        }
    }
    public void gameTick() {
        currentMana = Math.min(manaPool,currentMana + playerLevel);
    }
}
