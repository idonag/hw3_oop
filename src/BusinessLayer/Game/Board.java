package BusinessLayer.Game;

import BusinessLayer.Utils.Position;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Tiles.Tile;

import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<Tile> tiles;

    public Board(List<Tile> tiles){
        this.tiles = tiles;
    }

    public Tile get(Position p) {
        int x = p.getX();
        int y = p.getY();
        for(Tile t : tiles){
            if (t.getPosition().compareTo(Position.positionAt(x,y)) == 0){
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

    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        String s = "";
        int y = 0;
        for (Tile t:tiles
             ) {
            if (t.getPosition().getY() > y) {
                s += "\n";
                y++;
            }
            s+=t.toString();
        }
        return s;
    }
}
