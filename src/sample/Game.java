package sample;

public class Game
{
    final static public int FIRELDSIZE = 10;
    static public final int MAX_SHIPS = (int) ( FIRELDSIZE * 1.7);
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


    //Generiert ein random Board, wo die Schiffe zufällig platziert werden.
    Board randomBoard()
    {
        Board reBoard = new Board();
        int placeableShips = Game.MAX_SHIPS;
        int shipSize;
        int x,y;
        byte randomHV;
        char hv;

        shipSize = (int) ((Math.random()*6)+3);
        placeableShips -= shipSize;
        x = (int) (Math.random()*Game.FIRELDSIZE);
        y = (int) (Math.random()*Game.FIRELDSIZE);
        randomHV = (byte) (Math.random()*2);
        if (randomHV == 0){ hv = 'H'; } else { hv = 'V'; }

        reBoard.placeShip(x,y,shipSize,hv);
        return reBoard;
    }
}
