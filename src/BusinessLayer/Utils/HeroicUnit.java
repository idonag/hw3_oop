package BusinessLayer.Utils;

import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Tiles.Unit;

import java.awt.*;
import java.util.LinkedList;

public interface HeroicUnit {
    public void abilityCast(LinkedList<Unit> enemies);
}
