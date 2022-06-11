package BusinessLayer;

import java.awt.*;

public class Tile {
    protected Character tile;
    protected Point position;
    public Tile(Character c){
        tile = c;
    }
    public Point getPosition(){
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }
//    public abstract void accept(Unit unit);

//    @Override
//    public int compareTo(Tile tile) {
//        return getPosition().compareTo(tile.getPosition());
//    }
//
//    @Override
//    public String toString() {
//        return String.valueOf(tile);
//    }
}
