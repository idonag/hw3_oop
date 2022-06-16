package BusinessLayer;

public class Warrior extends Player{
    private Resource coolDown;
    public Warrior(String name, Integer helthPool, Integer attackPoints, Integer defensePoints,Integer abilityCoolDown){
        super(name,helthPool,attackPoints,defensePoints);
        this.coolDown = new Resource(0,abilityCoolDown);

    }

    @Override
    public void levelUp() {
        super.levelUp();
        coolDown.reduceAmount(coolDown.maxCapacity);
        health.increaseMaxCapacitiy(5 * playerLevel);
        attackPoints.increaseAmount(2 * playerLevel);
    }
    public void abilityCast(){
        if (coolDown.resource == 0){
            coolDown.increaseAmount(coolDown.maxCapacity);
            health.increaseAmount(10 * defensePoints.resource);
            //randomly hits an enemy within less then 3 distance units
            //for an amount equals to 10% of the warrior’s health pool.
        }
    }
    public void gameTick(){
        coolDown.reduceAmount(1);
    }
}
