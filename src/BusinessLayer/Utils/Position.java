package BusinessLayer.Utils;

import java.util.Objects;

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
    public Double range(Position p){
        return Math.sqrt(Math.pow(this.x-p.x,2)+Math.pow(this.y-p.y,2));
    }
    public Position move(int x,int y){
        int newX = this.x;
        int newY = this.y;
        return new Position(newX + x,newY + y);
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    @Override
    public boolean equals(Object p2){
        if (p2 instanceof Position)
            return this.compareTo((Position) p2) == 0;
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
