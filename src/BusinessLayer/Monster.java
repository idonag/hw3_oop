package BusinessLayer;

import java.awt.*;

public class Monster extends Enemy{
    private Integer visionRange;
    public Monster(Character c, String name, Integer helthPool,Integer attackPoints, Integer defensePoints, Integer exprienceToGain, Integer visionRange){
        super(c,name,helthPool,attackPoints,defensePoints,exprienceToGain);
        this.visionRange = visionRange;
    }
    public void turn(){
     /*   if(visionRange > ditance from player){
            if(Math.abs(position.x - player.position.x)>Math.abs(position.y - player.position.y)){
                if (Math.abs(position.x - player.position.x)> 0
                    MoveLeft;
                else
                    MoveRight;
            }
            else {
                if( Math.abs(position.y - player.position.y)>0){
                    MoveUp;
                }
                else
                    MoveDown;
            }
        }
        else
        Perform a random movement action: left, right, up, down or stay at the same place.*/
    }



    @Override
    public void processStep() {

    }

}
