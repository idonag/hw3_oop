package BusinessLayer.Tiles.Enemies;

import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Utils.HeroicUnit;

import java.util.LinkedList;
import java.util.Random;

public class Boss extends Enemy implements HeroicUnit {
    private Integer visionRange;
    private Integer abilityfrequency;
    private Integer combatTicks;
    public Boss(Character c, String name, Integer helthPool, Integer attackPoints, Integer defensePoints, Integer exprienceToGain,Integer visionRange,Integer abilityfrequency ) {
        super(c, name, helthPool, attackPoints, defensePoints, exprienceToGain);
        this.visionRange = visionRange;
        this.abilityfrequency = abilityfrequency;
        combatTicks = 0;
    }

    @Override
    public void abilityCast(LinkedList<Unit> units) {
        Unit player = units.get(0);
        int damage = this.attack(attackPoints.getResource())-player.defend();
        player.getHealth().reduceAmount(damage);
        messageCallBack.send(String.format("%s lost %d health points",player.getName(),damage));
    }

    @Override
    public void processStep(Board b, LinkedList<Unit> unitList) {
        Unit player = unitList.get(0);
        if(visionRange > this.position.range(player.getPosition())){
            if (combatTicks == abilityfrequency) {
                combatTicks = 0;
                abilityCast(unitList);
            }
            else {
                combatTicks++;
                if (Math.abs(position.getX() - player.getPosition().getX()) > Math.abs(position.getY() - player.getPosition().getY())) {
                    if (position.getX() - player.getPosition().getX() > 0)
                        this.interact(b.get(position.move(-1, 0)));
                    else
                        this.interact(b.get(position.move(1, 0)));
                } else {
                    if (position.getY() - player.getPosition().getY() > 0) {
                        this.interact(b.get(position.move(0, -1)));
                    } else
                        this.interact(b.get(position.move(0, 1)));
                }
            }
        }
        else{
            combatTicks = 0;
            Random r = new Random();
            int action = r.nextInt(0,5);
            if (action == 0)
                this.interact(b.get(position.move(-1,0)));
            else if (action == 1)
                this.interact(b.get(position.move(1,0)));
            else if (action == 2)
                this.interact(b.get(position.move(0,-1)));
            else if (action == 3)
                this.interact(b.get(position.move(0,1)));
            else{

            }

        }
    }
}
