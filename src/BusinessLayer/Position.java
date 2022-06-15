package BusinessLayer;

import java.awt.*;

public class Position {
    private int x;
    private int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int compareTo(Position position) {
        if (y > position.y)
            return 1;
        if(y < position.y)
            return -1;
        if (x > position.x)
            return 1;
        if (x < position.x)
            return -1;
        return 0;
    }
    public static Position positionAt (int x, int y){
        return new Position(x,y);
    }
    public double range(Position p){
        return Math.sqrt(Math.pow(this.x-p.x,2)+Math.pow(this.y-p.y,2));
    }
    public Position move(int x,int y){
        return new Position(this.x += x,this.y += y);
    }

}
