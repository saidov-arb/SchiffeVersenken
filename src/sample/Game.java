package sample;

public class Game
{
    final static public int FIRELDSIZE = 7;
    static public final int MAX_SHIPS = 20;
    private Player[] gamers = new Player[2];

    public Player[] getGamers() { return gamers; }
    public void setGamers(Player[] gamers) { this.gamers = gamers; }

    //Ein Spieler muss "MAX_SHIPS" Score haben, damit er gewinnt.
    //Gibt den Index vom Array zurück, je nachdem welcher Spieler gewonnen hat.
    int checkForWinner()
    {
        return 0;
    }

    //Gibt true zurück, wenn ein Spieler aufgegeben hat.
    boolean checkForSurrender()
    {
        return false;
    }
}
