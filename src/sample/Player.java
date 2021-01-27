package sample;

public class Player
{
    /**♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦
     * @Authors:    Arbi Saidov        (Backend.)
     *              Patrick Watzinger   (Frontend.)
     *              Marko Jezidzic      (Project Manager.)
     * @param primaryStage
     * @throws Exception
    ♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦♦*/

    private String name;
    private int score;
    private Board board;
    public static String[] randomName = {
            "Altinger","Bandalo","Blazevic",
            "Gegenleitner","Gmeiner","Gojer",
            "Helperstorfer","Imre","Imre",
            "Jedzizic","Jukic","Kovacic",
            "Luttenberger","Müllecker","Neubauer",
            "Prast","Puntigam","Saidov",
            "Tekel","Tokic","Viechtbauer",
            "Watzinger"
    };

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }

    Player(String name)
    {
        setName(name);
        setBoard(new Board());
        setScore(0);
    }


    //Generiert einen random Spieler mit random Board.
    static Player botPlayer()
    {
        Player rePlayer = new Player(randomName[(int)(Math.random()*22)]);
        rePlayer.setBoard(Game.randomBoard());
        return rePlayer;
    }
}
