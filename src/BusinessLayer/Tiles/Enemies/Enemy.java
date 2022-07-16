package BusinessLayer.Tiles.Enemies;

import BusinessLayer.Game.Board;
import BusinessLayer.CallBacks.EnemyDeathCallBack;
import BusinessLayer.Tiles.Players.Player;
import BusinessLayer.Tiles.Unit;

import java.util.LinkedList;

public abstract class Enemy extends Unit {
    protected Integer exprienceToGain;
    protected EnemyDeathCallBack enemyDeathCallBack;

    public Enemy(Character c, String name, Integer helthPool,Integer attackPoints, Integer defensePoints, Integer exprienceToGain){
        super(c,name,helthPool,attackPoints,defensePoints);
        this.exprienceToGain = exprienceToGain;
    }
    @Override
    public void visit(Enemy e) {

    }

    @Override
    public void visit(Player p) {
        battle(p);
        if (p.getHealth().getResource() <= 0)
            p.onDeath();
    }
    public void onDeath() {
        messageCallBack.send(String.format("%s died ",getName()));
        this.enemyDeathCallBack.call();
    }
    public void update(Unit u){

    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void setEnemyDeathCallBack(EnemyDeathCallBack enemyDeathCallBack) {
        this.enemyDeathCallBack = enemyDeathCallBack;
    }

    public Integer getExprienceToGain() {
        return exprienceToGain;
    }

    @Override
    public void gameTick() {

    }
}
