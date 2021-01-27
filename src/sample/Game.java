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
        if (getGamers()[0].getScore() == MAX_SHIPS)
        {
            return 0;
        }else if (getGamers()[1].getScore() == MAX_SHIPS)
        {
            return 1;
        }else
        {
            return -7;
        }
    }

    //Generiert ein random Board, wo die Schiffe zufällig platziert werden.
    static Board randomBoard()
    {
        Board reBoard = new Board();
        int shipSize;
        int x,y;
        byte randomHV;
        char hv;

        while(reBoard.getPlaceableShips() >= 1)
        {
            shipSize = (int)((Math.random()*5)+1);
            x = (int)((Math.random()*FIRELDSIZE));
            y = (int)((Math.random()*FIRELDSIZE));
            randomHV = (byte)((Math.random()*2)+1);
            if (randomHV == 1)
            {
                hv = 'H';
            }else{
                hv = 'V';
            }
            reBoard.placeShip(x, y, shipSize, hv);
        }
        return reBoard;
    }
}
