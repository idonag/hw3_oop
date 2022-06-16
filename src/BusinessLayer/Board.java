package BusinessLayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private List<Tile> tiles;

    public Board(Tile[][] board){
        tiles = new ArrayList<>();
        for(Tile[] line : board){
            tiles.addAll(Arrays.asList(line));
        }
    }

    public Tile get(int x, int y) {
        for(Tile t : tiles){
            if (t.getPosition().equals(Position.positionAt(x,y))){
                return t;
            }
        }
        throw new IllegalArgumentException("no such tile");
    }

    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
    }
    public void add(Tile t){

    }

//    public String toString() {
//        tiles = tiles.stream().sorted().collect(Collectors.toList());
//        // TODO: Implement me
//    }
}
