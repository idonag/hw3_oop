package BusinessLayer.Tiles.Players;

import BusinessLayer.CallBacks.PlayerDeathCallBack;
import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Utils.HeroicUnit;
import BusinessLayer.Utils.Resource;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Utils.Action;
import BusinessLayer.Utils.InputProvider;

import java.util.LinkedList;

public abstract class Player extends Unit implements HeroicUnit {
    protected Resource experience;
    protected Integer playerLevel;
    protected PlayerDeathCallBack playerDeathCallBack;
    protected InputProvider inputProvider;

    public Player(String name, Integer helthPool, Integer attackPoints, Integer defensePoints){
        super('@',name,helthPool,attackPoints,defensePoints);
        experience = new Resource(0);
        playerLevel = 1;
        inputProvider = new InputProvider();
    }
    public void levelUp(){
        experience.reduceAmount(50*playerLevel);
        playerLevel++;
        health.increaseMaxCapacitiy(10 * playerLevel);
        health.increaseAmount(health.getMaxCapacity());
        attackPoints.increaseAmount(4 * playerLevel);
        defensePoints.increaseAmount(playerLevel);
        messageCallBack.send(String.format("%s leveled up to level %d! ",getName(),playerLevel));
    }
    public void abilityCast(LinkedList<Unit> enemies){
        messageCallBack.send(String.format("%s trying to cast his ability ",getName()));
    }
    public abstract void gameTick();


    public void setDeathCallBack(PlayerDeathCallBack playerDeathCallBack) {
        this.playerDeathCallBack = playerDeathCallBack;
    }

    @Override
    public void onDeath() {
        messageCallBack.send(String.format("%s died",getName()));
        tile = 'X';
        playerDeathCallBack.call();

    }


    public void processStep(Board b, LinkedList<Unit> enemies) {
        Action action = translate(inputProvider.getInput());
        switch (action){
            case UP -> interact(b.get(position.move(0,-1)));
            case DOWN -> interact(b.get(position.move(0,1)));
            case LEFT -> interact(b.get(position.move(-1,0)));
            case RIGHT -> interact(b.get(position.move(1,0)));
            case ABILITYCAST -> abilityCast(enemies);
        }
        messageCallBack.send(this.describe());
        while (this.experience.getResource() >= 50 * playerLevel){
            levelUp();
        }
    }
    @Override
    public void visit(Enemy e) {
        battle(e);
        if (e.getHealth().getResource() <= 0) {
            swapPosition(e);
            e.onDeath();
        }
    }

    @Override
    public void visit(Player p) {
    }
    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
    public void onKill(Enemy e){
        this.experience.increaseAmount(e.getExprienceToGain());
    }
    private Action translate(String input){
        if (input.equals( "w"))
            return Action.UP;
        if (input.equals("a"))
            return Action.LEFT;
        if (input.equals("s"))
            return Action.DOWN;
        if (input.equals("d"))
            return Action.RIGHT;
        if (input.equals("e"))
            return Action.ABILITYCAST;
        else
            return Action.NOTHING;
    }
    public String describe(){
        String tempDescription = super.describe();
        return tempDescription + String.format("\t\texperience: %d/%d\t\tlevel: %d",experience.getResource(),50 * playerLevel.intValue(),playerLevel);
    }

    public Resource getExperience() {
        return experience;
    }
}
