package BusinessLayer;

import java.util.Scanner;

public abstract class Player extends Unit{
    protected Resource experience;
    protected Integer playerLevel;
    protected MessageCallBack messageCallBack;

    public Player(String name, Integer helthPool, Integer attackPoints, Integer defensePoints){
        super('@',name,helthPool,attackPoints,defensePoints);
        experience = new Resource(0);
        playerLevel = 1;
    }
    public void levelUp(){
        experience.reduceAmount(50*playerLevel);
        playerLevel++;
        health.increaseMaxCapacitiy(10 * playerLevel);
        health.increaseAmount(health.maxCapacity);
        attackPoints.increaseAmount(4 * playerLevel);
        defensePoints.increaseAmount(playerLevel);
    }
    public abstract void abilityCast();
    public void gameTick(){
        Scanner sc = new Scanner(System.in);
        String action = sc.nextLine();
        ///
        ///
        ///
        /*this.interact();*/
    }

    @Override
    public void onDeath() {
        //tile = 'X';
        //endGame
    }

    @Override
    public void processStep() {

    }
    @Override
    public void visit(Enemy e) {
        battle(e);
        if (e.health.resource <= 0)
            onKeal(e);
    }

    @Override
    public void visit(Player p) {
    }
    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
    public void onKeal(Enemy e){
        this.experience.increaseAmount(e.exprienceToGain);
        this.position = e.position;
        e.onDeath();
    }

    public void setMessageCallBack(MessageCallBack messageCallBack) {
        this.messageCallBack = messageCallBack;
    }
}
