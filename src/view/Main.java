package view;

import BusinessLayer.Game.LevelManeger;
import BusinessLayer.Tiles.Players.Player;
import BusinessLayer.Utils.InputProvider;
import controll.GameManeger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.exit;
import static java.nio.file.Files.readAllLines;

public class Main {

    public static void main(String[] args) throws IOException {
        GameManeger gm = new GameManeger(args[0]);
        gm.setMessageCallBack(s-> System.out.println(s));
        gm.play();
    }


}

