package sample;

public class Player
{
    private String name;
    private int score;
    private Board board;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }

    //Generiert einen random Spieler.
    Player botPlayer()
    {
        return null;
    }
}
