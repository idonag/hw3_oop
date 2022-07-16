package BusinessLayer.Tiles.Players;

import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Tiles.Enemies.Monster;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Wall;
import BusinessLayer.Utils.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player p1;
    @BeforeEach
    void setUp() {
        p1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        p1.setPosition(new Position(0,1));
        p1.setMessageCallBack(s -> {});
    }

    @Test
    void interactWithWallTest() {
        Tile wall = new Wall(new Position(1,1));
        p1.interact(wall);
        Assertions.assertEquals(new Position(0,1),p1.getPosition(),"player position shoudlnt chagne");
    }
    @Test
    void interactWithEmptyTest(){
        Tile empty = new Empty(new Position(1,1));
        p1.interact(empty);
        Assertions.assertEquals(new Position(1,1),p1.getPosition(),"player should replace position with tile");
    }

    @Test
    void interactWithEnemyTest() {
        Unit enemy = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        enemy.setPosition(new Position(1,1));
        enemy.setMessageCallBack(s->{});
        p1.interact(enemy);
        Assertions.assertTrue(enemy.getHealth().getResource() <= 80 & enemy.getHealth().getResource() >= 50,"range of enemy health after attack should be between 50-80");
    }

    @Test
    void levelUp() {
        p1.levelUp();
        Assertions.assertEquals(42,p1.getAttackPoints().getResource());
        Assertions.assertEquals(6,p1.getDefensePoints().getResource());
    }

    @Test
    void abilityCast() {
        Unit enemy = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        enemy.setPosition(new Position(1,1));
        LinkedList<Unit> list =  new LinkedList<>();
        list.add(enemy);
        p1.abilityCast(list);
        Assertions.assertEquals(80-30,enemy.getHealth().getResource());
    }
    @Test
    void onDeath() {
        p1.setDeathCallBack(()->{});
        p1.onDeath();
        Assertions.assertEquals('X',p1.getTile());
    }
    @Test
    void onKill() {
        Enemy enemy = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        p1.onKill(enemy);
        Assertions.assertEquals(25,p1.experience.getResource());
    }

}