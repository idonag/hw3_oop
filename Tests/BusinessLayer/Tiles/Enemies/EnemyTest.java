package BusinessLayer.Tiles.Enemies;

import BusinessLayer.Game.Board;
import BusinessLayer.Tiles.Empty;
import BusinessLayer.Tiles.Players.Player;
import BusinessLayer.Tiles.Players.Warrior;
import BusinessLayer.Tiles.Tile;
import BusinessLayer.Tiles.Unit;
import BusinessLayer.Tiles.Wall;
import BusinessLayer.Utils.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    Enemy enemy;
    @BeforeEach
    void setUp() {
        enemy = new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3);
        enemy.setPosition(new Position(1, 1));
        enemy.setMessageCallBack(s -> {
        });
        enemy.setEnemyDeathCallBack(() -> {
        });
    }

    @Test
    void interactWithWallTest() {
        Tile wall = new Wall(new Position(2, 1));
        enemy.interact(wall);
        Assertions.assertEquals(new Position(1, 1), enemy.getPosition(), "enemy position shoudlnt chagne");
    }

    @Test
    void interactWithEmptyTest() {
        Tile empty = new Empty(new Position(2, 1));
        enemy.interact(empty);
        Assertions.assertEquals(new Position(2, 1), enemy.getPosition(), "enemy should replace position with tile");
    }

    @Test
    void interactWithEnemyTest() {
        Unit e2 = new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3);
        e2.setPosition(new Position(2, 1));
        e2.setMessageCallBack(s -> {
        });
        enemy.interact(e2);
        Assertions.assertEquals(new Position(1, 1), enemy.getPosition(), "enemy position shoudlnt chagne");
        Assertions.assertEquals(new Position(2, 1), e2.getPosition(), "enemy position shoudlnt chagne");
    }
    @Test
    void processStepTest(){
        LinkedList<Tile> tiles = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5 ; j++)
            {
                if (j == 0 || i == 0 || j == 4 || i == 4)
                    tiles.add(new Wall(new Position(j,i)));
                else tiles.add(new Empty(new Position(j,i)));
            }
        }
        Board b = new Board(tiles);
        Player p1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        p1.setPosition(new Position(3,3));
        LinkedList<Unit> players = new LinkedList<>();
        players.add(p1);
        enemy.processStep(b,players);

        Assertions.assertTrue(enemy.getPosition().equals(new Position(1,1)) || enemy.getPosition().equals(new Position(1,2))|| enemy.getPosition().equals(new Position(2,1)));
    }
}