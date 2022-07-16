package BusinessLayer.Tiles.Enemies;

import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Players.Player;
import BusinessLayer.Tiles.Unit;

import java.util.LinkedList;

public class Trap extends Enemy {
    private Integer visibilityTime;
    private Integer invisibilityTime;
    private Integer ticksCount;
    private Boolean visible;
    public Trap(Character c, String name, Integer helthPool,Integer attackPoints, Integer defensePoints, Integer exprienceToGain,Integer visibilityTime,Integer invisibilityTime){
        super(c,name,helthPool,attackPoints,defensePoints,exprienceToGain);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        ticksCount = 0;
        visible = true;
    }


    public void turn(Player p, Board board) {

    }


    public void processStep() {

    }

    @Override
    public String toString() {
        if (visible)
            return super.toString();
        else
            return ".";
    }

    @Override
    public void processStep(Board b, LinkedList<Unit> unitList) {
        Unit player = unitList.get(0);
        visible = ticksCount < visibilityTime;
        if (ticksCount == (visibilityTime + invisibilityTime))
            ticksCount = 0;
        else
            ticksCount++;
        if (this.position.range(player.getPosition()) < 2)
            battle(player);
    }
}
