package sample;

public class Player
{
    private String name;
    private int score;
    private Board board;
    public String[] randomName = {
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
        setScore(0);
    }

    //Generiert einen random Spieler.
    Player botPlayer()
    {
        Player rePlayer = new Player(randomName[(int)(Math.random()*22)]);

        return rePlayer;
    }
}
