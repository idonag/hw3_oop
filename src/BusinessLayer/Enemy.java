package BusinessLayer;

public abstract class Enemy extends Unit implements Observer {
    protected Integer exprienceToGain;
    public Enemy(Character c, String name, Integer helthPool,Integer attackPoints, Integer defensePoints, Integer exprienceToGain){
        super(c,name,helthPool,attackPoints,defensePoints);
        this.exprienceToGain = exprienceToGain;
    }
    public abstract void turn();

    @Override
    public void visit(Enemy e) {

    }

    @Override
    public void visit(Player p) {

    }
    public void onDeath() {
        //update player's expirience and so on...
    }
    public void update(Unit u){

    }


}
