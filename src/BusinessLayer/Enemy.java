package BusinessLayer;

public abstract class Enemy extends Unit {
    protected Integer exprienceToGain;
    protected EnemyDeathCallBack enemyDeathCallBack;
    protected MessageCallBack messageCallBack;
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
        this.enemyDeathCallBack.call();
    }
    public void update(Unit u){

    }

    @Override
    public void accept(Unit unit) {

    }

    public void setEnemyDeathCallBack(EnemyDeathCallBack enemyDeathCallBack) {
        this.enemyDeathCallBack = enemyDeathCallBack;
    }

    public void setMessageCallBack(MessageCallBack messageCallBack) {
        this.messageCallBack = messageCallBack;
    }
}
