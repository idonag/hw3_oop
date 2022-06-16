package BusinessLayer;

public class Rogue extends Player{
    private Integer specialAbilityCost;
    private Resource currentEnergy;
    public Rogue (String name, Integer helthPool, Integer attackPoints, Integer defensePoints,Integer specialAbilityCost){
        super(name,  helthPool, attackPoints,  defensePoints);
        this.specialAbilityCost = specialAbilityCost;
        currentEnergy = new Resource(100,100);
    }
    public void levelUp() {
        super.levelUp();
        currentEnergy.increaseAmount(100);
        attackPoints.increaseAmount(3*playerLevel);
    }
    public void abilityCast(){
        if (currentEnergy.resource >= specialAbilityCost){
            currentEnergy.reduceAmount(specialAbilityCost);
            //- For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
            //attack points (each enemy will try to defend itself).
        }
    }
    public void gameTick() {

        currentEnergy.increaseAmount(10);
    }
}
