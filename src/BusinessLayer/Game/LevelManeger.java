package BusinessLayer.Game;

import BusinessLayer.CallBacks.EndGameCallBack;
import BusinessLayer.CallBacks.NextLevelCallBack;
import BusinessLayer.Tiles.Enemies.Enemy;
import BusinessLayer.Tiles.Players.Player;
import BusinessLayer.Tiles.Unit;

import java.util.LinkedList;

public class LevelManeger {
    private Unit player;
    private LinkedList<Unit> enemies;
    private Board board;
    protected boolean gameIsActive;
    protected EndGameCallBack endGameCallBack;
    protected NextLevelCallBack nextLevelCallBack;
    public LevelManeger(Board board, Unit player, LinkedList<Unit> enemies){
        this.enemies =enemies;
        gameIsActive = true;
        this.board = board;
        this.player = player;
    }

    public void removeEnemy(Enemy e){
        board.remove(e);
        enemies.remove(e);
    }
    public void addEnemy(Enemy e){
        enemies.add(e);
    }
    public void play(){
        LinkedList<Unit> playerList = new LinkedList<>();
        playerList.add(player);
        while (gameIsActive){
            System.out.println(board.toString());
            player.processStep(board,enemies);
            player.gameTick();
            for (Unit e:enemies
                 ) {
                e.processStep(board,playerList);
            }
            if (enemies.isEmpty())
                nextLevel();
        }
    }
    public void endGame(){
        gameIsActive= !gameIsActive;
        board.toString();
        endGameCallBack.call();
    }
    public void nextLevel(){
        gameIsActive= !gameIsActive;
        System.out.println("congrats!");
        nextLevelCallBack.call();

    }

    public void setEndGameCallBack(EndGameCallBack endGameCallBack) {
        this.endGameCallBack = endGameCallBack;
    }

    public void setNextLevelCallBack(NextLevelCallBack nextLevelCallBack) {
        this.nextLevelCallBack = nextLevelCallBack;
    }

    public Board getBoard() {
        return board;
    }
    public void onEnemyDeath(Enemy e,Player p){
        board.remove(e);
        enemies.remove(e);
        p.onKill(e);
    }
}
