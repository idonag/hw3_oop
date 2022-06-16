package BusinessLayer;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;

public class GameManeger {
    private Player player;
    private LinkedList<Enemy> enemies;
    private Board board;
    protected boolean gameIsActive;

    public GameManeger(){
        enemies = new LinkedList<Enemy>();
        gameIsActive = true;
    }

    public void removeEnemy(Enemy e){
        board.remove(e);
        enemies.remove(e);
    }
    public void addEnemy(Enemy e){
        enemies.add(e);
    }
    public void play(){
        while (gameIsActive){
            player.gameTick();
            enemies.forEach(Enemy::turn);
        }
    }
}
