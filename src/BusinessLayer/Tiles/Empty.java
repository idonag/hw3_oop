package BusinessLayer.Tiles;

import BusinessLayer.Utils.Position;

public class Empty extends Tile {
    public Empty(Position p){
        super('.');
        setPosition(p);
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
}
