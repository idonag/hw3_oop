package BusinessLayer.Tiles.Players;

import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Utils.Resource;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Warrior extends Player {
    private Resource coolDown;
    public Warrior(String name, Integer helthPool, Integer attackPoints, Integer defensePoints,Integer abilityCoolDown){
        super(name,helthPool,attackPoints,defensePoints);
        this.coolDown = new Resource(0,abilityCoolDown);

    }

    @Override
    public void levelUp() {
        super.levelUp();
        coolDown.reduceAmount(coolDown.getMaxCapacity());
        health.increaseMaxCapacitiy(5 * playerLevel);
        attackPoints.increaseAmount(2 * playerLevel);
    }
    public void abilityCast(LinkedList<Unit> enemies){
        super.abilityCast( enemies);
        if (coolDown.getResource() == 0){
            coolDown.increaseAmount(coolDown.getMaxCapacity());
            health.increaseAmount(10 * defensePoints.getResource());
            List<Unit> enemyList = enemies.stream().filter(x -> this.position.range(x.getPosition()) < 3).collect(Collectors.toList());
            Random r = new Random();

            if (!enemyList.isEmpty()) {
                Unit e = enemyList.get(r.nextInt(enemyList.size()));
                int damage = attack((int) health.getMaxCapacity()/10);
                e.getHealth().reduceAmount(damage);
                messageCallBack.send(String.format("%s lost %d health points",e.getName(),damage));
            }
        }
        else {
            messageCallBack.send("not enough resources for ability cast");
        }
    }
    public void gameTick(){
        coolDown.reduceAmount(1);
    }
}
