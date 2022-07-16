package BusinessLayer.Tiles;

import BusinessLayer.Utils.Position;

public class Wall extends Tile {
    public Wall(Position p){
        super('#');
        setPosition(p);
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
