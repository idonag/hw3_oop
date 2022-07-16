package BusinessLayer.Tiles.Enemies;

import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Players.Player;
import BusinessLayer.Tiles.Unit;

import java.util.LinkedList;
import java.util.Random;

public class Monster extends Enemy {
    private Integer visionRange;
    public Monster(Character c, String name, Integer helthPool,Integer attackPoints, Integer defensePoints, Integer exprienceToGain, Integer visionRange){
        super(c,name,helthPool,attackPoints,defensePoints,exprienceToGain);
        this.visionRange = visionRange;
    }
    public void processStep(Board b, LinkedList<Unit> unitList){
        Unit player = unitList.get(0);
        if(visionRange > this.position.range(player.getPosition())){
            if(Math.abs(position.getX() - player.getPosition().getX())>Math.abs(position.getY() - player.getPosition().getY())){
                if (position.getX() - player.getPosition().getX()> 0)
                    this.interact(b.get(position.move(-1,0)));
                else
                    this.interact(b.get(position.move(1,0)));
            }
            else {
                if( position.getY() - player.getPosition().getY()>0){
                    this.interact(b.get(position.move(0,-1)));
                }
                else
                    this.interact(b.get(position.move(0,1)));
            }
        }
        else{
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




    public void processStep() {

    }

}
