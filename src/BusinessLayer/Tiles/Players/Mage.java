package BusinessLayer.Tiles.Players;

import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Utils.Resource;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Mage extends Player {
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
        mana.increaseAmount(mana.getMaxCapacity()/4);
        spellPower.increaseAmount(10 * playerLevel);
    }
    public void abilityCast(LinkedList<Unit> enemies){
        super.abilityCast( enemies);
        mana.reduceAmount(manaCost);
        Integer hits = 0;

        List<Unit> enemyList = enemies.stream().filter(x -> this.position.range(x.getPosition()) < abilityRange).collect(Collectors.toList());

        while (hits < hitsCount && !enemyList.isEmpty()){
            Random r = new Random();
            Unit e = enemyList.get(r.nextInt(enemyList.size()));
            if (e != null) {
                int damage = attack(attackPoints.getResource()) - e.defend();
                if ( damage > 0) {
                    e.getHealth().reduceAmount(damage);
                    messageCallBack.send(String.format("%s lost %d health points",e.getName(),damage));
                }
            }
            hits++;
            if (e.getHealth().getResource() <= 0)
                enemyList.remove(e);
        }
    }
    public void gameTick() {
        mana.increaseAmount(playerLevel);
    }

}
