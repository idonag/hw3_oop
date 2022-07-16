package BusinessLayer.Utils;

import javax.swing.*;

public class Resource {
    protected int resource;
    protected int maxCapacity;
    public Resource(int resource,int maxCapacity){
        this.resource = resource;
        this.maxCapacity = maxCapacity;
    }
    public Resource(int resource){
        this(resource,Integer.MAX_VALUE);
    }
    public void reduceAmount(int amount){
        resource = Math.max(0,resource-amount);
    }
    public void increaseAmount(int amount){
        resource = Math.min(maxCapacity,resource + amount);
    }

    public void increaseMaxCapacitiy(int amount) {
        this.maxCapacity = maxCapacity + amount;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getResource() {
        return resource;
    }



}
