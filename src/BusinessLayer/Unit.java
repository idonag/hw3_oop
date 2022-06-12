package BusinessLayer;

import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
import java.util.Random;

public abstract class Unit extends Tile {
    protected String name;
    protected Integer helthPool;
    protected Integer helthAmount;
    protected Integer attackPoints;
    protected Integer defensePoints;

    public Unit(Character c, String name, Integer helthPool, Integer attackPoints, Integer defensePoints) {
        super(c);
        this.name = name;
        this.helthPool = helthPool;
        helthAmount = helthPool;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;

    }

    protected void initialize(Position p)//MessageCallback messageCallback
    {
        position = p;
    }
    protected int attack(){
        Random random = new Random();
        return random.nextInt(attackPoints);
    }
    protected int defend(){
        Random random = new Random();
        return random.nextInt(defensePoints);
    }
    public abstract void onDeath();
    public abstract void processStep();
    public void interact(Tile t){

    }
    public void visit(Unit u){

    }
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    // Combat against another unit.
    protected void battle(Unit attacker){
        int damage = attacker.attack() - this.defend();
        if (damage > 0)
            this.helthPool -= damage;
        if (this.helthPool <= 0)
            onDeath();

    }
    public String getName(){
        return name;
    }
    public Integer getHelthPool(){
        return helthPool;
    }
    public Integer gethelthAnount(){
        return helthAmount;
    }
    public Integer getattackPoints(){
        return attackPoints;
    }
    public Integer getdefensePoints(){
        return defensePoints;
    }


}
