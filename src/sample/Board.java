package sample;

public class Board
{
    private int[][] fireld;

    public int[][] getFireld() { return fireld; }
    public void setFireld(int[][] fireld) { this.fireld = fireld; }

    //Kontrolliert, ob an dieser Stelle 0 steht. Wenn nicht, dann gibt es false zurück.
    boolean checkForSpace(int x,int y)
    {
        return true;
    }
}
