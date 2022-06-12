package BusinessLayer;

public abstract class Player extends Unit implements EnemyDeathCallBack{
    protected Integer experience;
    protected Integer playerLevel;
    public Player(String name, Integer helthPool, Integer attackPoints, Integer defensePoints){
        super('@',name,helthPool,attackPoints,defensePoints);
        experience = 0;
        playerLevel = 1;
    }
    public void levelUp(){
        experience = experience + 50 * playerLevel;
        playerLevel++;
        helthPool = helthPool + 10 * playerLevel;
        helthAmount = helthPool;
        attackPoints = attackPoints + 4 * playerLevel;
        defensePoints = defensePoints + playerLevel;
    }
    public abstract void abilityCast();
    public abstract void gameTick();

    @Override
    public void onDeath() {
        tile = 'X';
        //endGame
    }

    @Override
    public void processStep() {

    }
    @Override
    public void visit(Enemy e) {

    }

    @Override
    public void visit(Player p) {

    }
    public void EnemyDies(Enemy e){
        experience += e.exprienceToGain;
        this.setPosition(e.position);
    }
}
