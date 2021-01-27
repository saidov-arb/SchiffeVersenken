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
            "Altinger Julian","Bandalo Lukas","Blazevic Dominik",
            "Gegenleitner Alexander","Gmeiner Manuel","Gojer Sebastian",
            "Helperstorfer Julian","Imre Baran","Imre Bertal",
            "Jedzizic Marko","Jukic Leon","Kovacic David",
            "Luttenberger Fabian","Müllecker Georg","Neubauer Stefan",
            "Prast Eric","Puntigam Thomas","Saidov Arbi",
            "Tekel Marian","Tokic Jan","Viechtbauer Lukas",
            "Watzinger Patrick"
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
