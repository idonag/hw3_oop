package BusinessLayer;

public class Trap extends Enemy{
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

    @Override
    public void turn() {
        visible = ticksCount < visibilityTime;
        if (ticksCount == (visibilityTime + invisibilityTime))
            ticksCount = 0;
        else
            ticksCount++;
//        if (range(trap,player) < 2)
//            attack;
    }

    @Override
    public void processStep() {

    }

    @Override
    public void accept(Unit unit) {

    }
}
