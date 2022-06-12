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
        throw new IllegalArgumentException("not implemented yet");
    }
}
