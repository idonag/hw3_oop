package BusinessLayer;

public class Rogue extends Player{
    private Integer specialAbilityCost;
    private Integer currentEnergy;
    public Rogue (Character c, String name, Integer helthPool, Integer attackPoints, Integer defensePoints,Integer specialAbilityCost){
        super(c, name,  helthPool, attackPoints,  defensePoints);
        this.specialAbilityCost = specialAbilityCost;
        currentEnergy = 100;
    }
    public void levelUp() {
        super.levelUp();
        currentEnergy = 100;
        attackPoints = attackPoints + 3 * playerLevel;
    }
    public void abilityCast(){
        if (currentEnergy >= specialAbilityCost){
            currentEnergy = currentEnergy - specialAbilityCost;
            //- For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
            //attack points (each enemy will try to defend itself).
        }
    }
    public void gameTick() {

        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

}
