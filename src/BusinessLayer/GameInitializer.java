package BusinessLayer;

public class GameInitializer {
    private GameManeger gameManeger;
    private Board board;
    private char[][] arr;
    public GameInitializer(char[][] arr){
        this.arr = arr;
    }
    public void initialize(){
        for (int i = 0 ; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                Position p = new Position(i,j);
                if (arr[i][j] ==  '.')
                    board.add(new Empty(p));
                else if(arr[i][j] == '#')
                    board.add(new Wall(p));
                else if(arr[i][j] == '@') {
                   /* Player player = new Player();
                    *//*board.add(board.add(player);*//*
                    player.setMessageCallBack((s -> System.out.println(s)));*/
                }
                else{
                    /*Enemy e = new Enemy();
                    e.setEnemyDeathCallBack(()->gameManeger.removeEnemy(e));
                    e.setMessageCallBack((s -> System.out.println(s)));
                    board.add(e);*/
                }
            }
        }
    }
}
