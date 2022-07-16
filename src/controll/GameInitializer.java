package controll;

import BusinessLayer.Game.Board;
import BusinessLayer.Game.LevelManeger;
import BusinessLayer.Tiles.*;
import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Tiles.Players.Player;
import BusinessLayer.Utils.Position;

import java.util.LinkedList;

public class GameInitializer {
    private LevelManeger levelManeger;
    private Board board;
    private char[][] arr;
    private TileFactory tileFactory;
    private LinkedList<Unit> enemyList;
    private Player player;
    public GameInitializer(char[][] arr, Player player){
        this.arr = arr;
        tileFactory = new TileFactory();
        this.player = player;
        enemyList = new LinkedList<Unit>();

    }
    public void initialize(){
        LinkedList<Tile> tileList = new LinkedList<>();

        for (int i = 0 ; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                Position p = new Position(j,i);
                if (arr[i][j] ==  '.')
                    tileList.add(new Empty(p));
                else if(arr[i][j] == '#')
                    tileList.add(new Wall(p));
                else if(arr[i][j] == '@') {

                    player.setPosition(p);

                    player.setMessageCallBack(s -> System.out.println(s));
                    player.setDeathCallBack(()-> levelManeger.endGame());
                    tileList.add(player);
                }
                else{
                    Enemy e = tileFactory.produceEnemy(arr[i][j],p);
                    e.setEnemyDeathCallBack(()-> levelManeger.onEnemyDeath(e,player));
                    e.setMessageCallBack((s -> System.out.println(s)));
                    tileList.add(e);
                    enemyList.add(e);
                }
            }

        }
        board = new Board(tileList);
    }

    public Board getBoard() {
        return board;
    }

    public LinkedList<Unit> getEnemyList() {
        return enemyList;
    }

    public void setLevelManeger(LevelManeger levelManeger) {
        this.levelManeger = levelManeger;
    }
}
