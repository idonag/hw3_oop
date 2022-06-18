package BusinessLayer;

import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
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
        this.attackPoints = new Resource(attackPoints,attackPoints);
        this.defensePoints = new Resource(defensePoints,defensePoints);


    }

    protected void initialize(Position p)//MessageCallback messageCallback
    {
        position = p;
    }
    protected int attack(){
        Random random = new Random();
        return random.nextInt(attackPoints.resource);
    }
    protected int defend(){
        Random random = new Random();
        return random.nextInt(defensePoints.resource);
    }
    public abstract void onDeath();
    public abstract void processStep();
    public void interact(Tile t){
        t.accept(this);
    }
    public void visit(Empty e){
        position = e.position;
    }
    public void visit(Wall w){
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    // Combat against another unit.
    protected void battle(Unit unit){
        messageCallBack.send(String.format("%s engaged combat with %s \n %s \n %s",getName(),unit.getName(),describe(),unit.describe()));
        int damage = this.attack() - unit.defend();
        if (damage > 0)
            unit.health.reduceAmount(damage);


    }
    public String getName(){
        return name;
    }
    public String describe(){
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), health.resource, attackPoints.resource, defensePoints.resource);
    }

}
