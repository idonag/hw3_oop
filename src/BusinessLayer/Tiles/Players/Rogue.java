package BusinessLayer.Tiles.Players;

import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Utils.Resource;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Rogue extends Player {
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
    public void abilityCast(LinkedList<Unit> enemies){
        super.abilityCast( enemies);
        if (currentEnergy.getResource() >= specialAbilityCost) {
            currentEnergy.reduceAmount(specialAbilityCost);
            //- For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
            //attack points (each enemy will try to defend itself).
            List<Unit> enemyList = enemies.stream().filter(x -> this.position.range(x.getPosition()) < 2).collect(Collectors.toList());
            if (!enemyList.isEmpty()) {
                Random r = new Random();
                Unit e = enemyList.get(r.nextInt(enemyList.size()));
                int damage = this.attack(this.attackPoints.getResource())-e.defend();
                e.getHealth().reduceAmount(damage);
                messageCallBack.send(String.format("%s lost %d health points",e.getName(),damage));
            }
        }
        else
            messageCallBack.send("not enough resources for ability cast");
    }
    public void gameTick() {

        currentEnergy.increaseAmount(10);
    }
}
