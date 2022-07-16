package controll;

import BusinessLayer.CallBacks.MessageCallBack;
import BusinessLayer.Game.Board;
import BusinessLayer.Game.LevelManeger;
import BusinessLayer.Tiles.Players.Player;
import BusinessLayer.Utils.InputProvider;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.exit;
import static java.nio.file.Files.readAllLines;

public class GameManeger  {
    private Player player;
    private InputProvider inputProvider;
    private int levelidx = 1;
    private TileFactory tf;
    private String path;
    private MessageCallBack messageCallBack;
    public GameManeger(String path) {
        player = null;
        inputProvider = new InputProvider();
        tf= new TileFactory();
        tf.setMessageCallBack(s->messageCallBack.send(s));
        this.path = path;
    }
    public void play()  throws IOException {
        for (Path p : Files.list(Paths.get(path)).sorted().collect(Collectors.toList())) {
            List<String> list = readAllLines(p);
            char[][] c = convertBoard(list);
            if (player == null) {
                tf.presentPlayer();
                String answer = inputProvider.getInput();
                int indx = Integer.parseInt(answer);
                player = tf.producePlayer(indx);
            }
            messageCallBack.send("level " + levelidx);
            GameInitializer g = new GameInitializer(c, player);
            g.initialize();
            LevelManeger levelManeger = new LevelManeger(g.getBoard(), player, g.getEnemyList());
            levelManeger.setEndGameCallBack(()->endGame(levelManeger.getBoard()));
            levelManeger.setNextLevelCallBack(() -> messageCallBack.send("finish level"));

            g.setLevelManeger(levelManeger);
            levelManeger.play();
            levelidx++;
        }
        messageCallBack.send("congratulations! you are the winner!");
    }

    public char[][] convertBoard(List<String> list) {
        char[][] c = new char[list.size()][list.get(0).length()];
        int i = 0;
        int j = 0;
        for (String s : list
        ) {
            j = 0;
            for (char tile : s.toCharArray()
            ) {
                c[i][j] = tile;
                j++;
            }
            i++;
        }
        return c;
    }
    public void endGame(Board board){
        messageCallBack.send(board.toString());
        messageCallBack.send("game over");
        exit(0);

    }

    public void setMessageCallBack(MessageCallBack messageCallBack) {
        this.messageCallBack = messageCallBack;
    }
}
