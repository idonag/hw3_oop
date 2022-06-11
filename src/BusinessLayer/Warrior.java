package BusinessLayer;

public class Warrior extends Player{
    private Integer abilityCoolDown;
    private Integer remainingCoolDown;
    public Warrior(Character c, String name, Integer helthPool, Integer attackPoints, Integer defensePoints,Integer abilityCoolDown){
        super(c,name,helthPool,attackPoints,defensePoints);
        this.abilityCoolDown = abilityCoolDown;
        remainingCoolDown = 0;

    }

    @Override
    public void levelUp() {
        super.levelUp();
        remainingCoolDown = 0;
        helthPool = helthPool + 5 * playerLevel;
        attackPoints = attackPoints + 2 * playerLevel;
    }
    public void abilityCast(){
        if (remainingCoolDown == 0){
            remainingCoolDown = abilityCoolDown;
            helthAmount = Math.min(helthAmount + 10 * defensePoints,helthPool);
            //randomly hits an enemy within less then 3 distance units
            //for an amount equals to 10% of the warrior’s health pool.
        }
    }
    public void gameTick(){
        if (remainingCoolDown > 0) {
            remainingCoolDown--;
        }
    }
}
