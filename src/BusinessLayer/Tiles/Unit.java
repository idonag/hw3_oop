package BusinessLayer.Tiles;

import BusinessLayer.CallBacks.MessageCallBack;
import BusinessLayer.Game.Board;
import BusinessLayer.Utils.Position;
import BusinessLayer.Utils.Resource;
import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Tiles.Players.Player;

import java.util.LinkedList;
import java.util.Random;

public abstract class Unit extends Tile {
    protected String name;
    protected Resource health;
    protected Resource attackPoints;
    protected Resource defensePoints;
    protected MessageCallBack messageCallBack;

    public Unit(Character c, String name, Integer helthPool, Integer attackPoints, Integer defensePoints) {
        super(c);
        this.name = name;
        this.health = new Resource(helthPool,helthPool);
        this.attackPoints = new Resource(attackPoints);
        this.defensePoints = new Resource(defensePoints);


    }

    protected void initialize(Position p)//MessageCallback messageCallback
    {
        position = p;
    }
    public int attack(){
        Random random = new Random();
        int attack = random.nextInt(attackPoints.getResource());
        messageCallBack.send(String.format("%s attacked with %d attack points",getName(),attack));
        return attack;

    }
    public int attack(int amount){
        messageCallBack.send(String.format("%s attacked with %d attack points",getName(),amount));
        return amount;
    }
    public int defend(){
        Random random = new Random();
        int defend = random.nextInt(defensePoints.getResource());
        messageCallBack.send(String.format("%s defend with %d defence points",getName(),defend));
        return defend;
    }
    public abstract void onDeath();

    public void interact(Tile t){
        t.accept(this);
    }
    public void visit(Empty e){
        swapPosition(e);
    }
    public void visit(Wall w){
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    // Combat against another unit.
    protected void battle(Unit unit){
        messageCallBack.send(String.format("%s engaged combat with %s \n%s\n%s",getName(),unit.getName(),describe(),unit.describe()));
        int damage = this.attack() - unit.defend();
        if (damage > 0) {
            unit.health.reduceAmount(damage);
            messageCallBack.send(String.format("%s lost %d health points ",unit.getName(),damage));
        }


    }
    public String getName(){
        return name;
    }
    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), health.getResource(), attackPoints.getResource(), defensePoints.getResource());
    }
    public void swapPosition(Tile tile){
        Position p =this.position;
        this.setPosition(tile.position);
        tile.setPosition(p);

    }

    public void setMessageCallBack(MessageCallBack messageCallBack) {
        this.messageCallBack = messageCallBack;
    }

    public Resource getAttackPoints() {
        return attackPoints;
    }

    public Resource getDefensePoints() {
        return defensePoints;
    }

    public Resource getHealth() {
        return health;
    }
    public abstract void processStep(Board b, LinkedList<Unit> unitList);
    public abstract void gameTick();
}
