package BusinessLayer.Tiles.Players;

import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Utils.Resource;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Hunter extends Player{
    private Resource arrows;
    private Integer shootingRange;
    private Integer ticksCount;
    public Hunter(String name, Integer helthPool, Integer attackPoints, Integer defensePoints,Integer shootingRange) {
        super(name, helthPool, attackPoints, defensePoints);
        arrows = new Resource(10);
        this.shootingRange = shootingRange;
        ticksCount = 0;
    }

    @Override
    public void gameTick() {
        if (ticksCount == 10 ) {
            arrows.increaseAmount(playerLevel);
            ticksCount = 0;
        }
        else
            ticksCount ++;
    }

    @Override
    public void abilityCast(LinkedList<Unit> enemies) {
        super.abilityCast(enemies);
        List<Unit> enemyList = enemies.stream().filter(x -> this.position.range(x.getPosition()) < shootingRange).collect(Collectors.toList());
        if (!enemyList.isEmpty() && arrows.getResource() > 0) {
            Comparator<Unit> rangeComparator = Comparator.comparing(e -> e.getPosition().range(position));
            arrows.reduceAmount(1);
            enemyList = enemyList.stream().sorted(rangeComparator).collect(Collectors.toList());
            Unit enemyToAttack = enemyList.get(0);

            int damage = attack(attackPoints.getResource()) - enemyToAttack.defend();
            if ( damage > 0) {
                enemyToAttack.getHealth().reduceAmount(damage);
                messageCallBack.send(String.format("%s lost %d health points",enemyToAttack.getName(),damage));
                if (enemyToAttack.getHealth().getResource() <= 0) {
                    enemyToAttack.onDeath();
                }
            }
        }
    }

    @Override
    public void levelUp() {
        super.levelUp();
        arrows.increaseAmount(10 * playerLevel);
        attackPoints.increaseAmount(2 * playerLevel);
        defensePoints.increaseAmount(playerLevel);
    }
}
