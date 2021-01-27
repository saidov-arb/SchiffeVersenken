package sample;

public class Board
{
    /********************************************
     * Codes:   0   -   Space
     *          1   -   Ship
     *          22  -   Needed Space
     *          404 -   Destroyed Ship
     *          501 -   Not a Ship
     */

    private int[][] fireld = new int[Game.FIRELDSIZE][Game.FIRELDSIZE];
    private int placeableShips = Game.MAX_SHIPS;

    public int[][] getFireld() { return fireld; }
    public void setFireld(int[][] fireld) { this.fireld = fireld; }
    public int getPlaceableShips() { return placeableShips; }
    public void setPlaceableShips(int placeableShips) { this.placeableShips = placeableShips; }

    //Schießt eine Bombe auf die gegebenen Koordinaten.
    //Aus Systemtechnischer Sicht - Man ändert einen 1er zu 404, wenn getroffen.
    void explode(int x, int y)
    {
        try
        {
            if (getFireld()[y][x] == 1)
            {
                getFireld()[y][x] = 404;
            }else{
                getFireld()[y][x] = 501;
            }
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println("Index gibt's ned.");
        }
    }

    //Schießt eine Bombe auf zufällige Koordinaten.
    //Es ist 1:1 dasselbe wie die andere explode Methode, nur ohne Parameter.
    void explode()
    {
        int x,y;
        boolean shot = false;
        while (!shot)
        {
            x = (int) (Math.random()*Game.FIRELDSIZE);
            y = (int) (Math.random()*Game.FIRELDSIZE);
            if (getFireld()[y][x] == 1)
            {
                shot = true;
                getFireld()[y][x] = 404;
            }else if (getFireld()[y][x] == 0 || getFireld()[y][x] == 22)
            {
                shot = true;
                getFireld()[y][x] = 501;
            }
        }
    }

    //Kontrolliert, ob an dieser Stelle 0 steht. Wenn nicht, dann gibt es false zurück.
    boolean checkForSpace(int x, int y, int shipSize, char hv)
    {
        try
        {
            if (getFireld()[y][x] != 0)
            {
                return false;
            }
            else
            {
                if (hv == 'H')
                {
                    for (int i = 1; i < shipSize; i++)
                    {
                        if (getFireld()[y][x+i] != 0)
                        {
                            return false;
                        }
                    }
                }
                else
                {
                    for (int i = 1; i < shipSize; i++)
                    {
                        if (getFireld()[y+i][x] != 0)
                        {
                            return false;
                        }
                    }
                }
            }
        }catch (IndexOutOfBoundsException e)
        {
            System.out.println("Index doesn't exist.");
            return false;
        }
        return true;
    }

    //Platziert ein Schiff mit 4 Parametern.
    //(Koordinaten aus x und y, Länge des Schiffes, Angabe von Horizontal oder Vertikal)
    void placeShip(int x, int y, int shipSize, char hv)
    {
        boolean worked = true;
        if (getPlaceableShips() - shipSize <= 0)
        {
            shipSize = getPlaceableShips();
        }

        if (checkForSpace(x,y,shipSize,hv))
        {
            if (shipSize != 0)
            {
                getFireld()[y][x] = 1;
            }
            if (hv == 'H')
            {
                for (int i = 1; i < shipSize; i++)
                {
                    getFireld()[y][x+i] = 1;
                }
            }
            else if (hv == 'V')
            {
                for (int i = 1; i < shipSize; i++)
                {
                    getFireld()[y+i][x] = 1;
                }
            }
            placeNeededSpace(x, y, shipSize, hv);
        }
        else
        {
            System.out.println("Not enough Place.");
            worked = false;
        }
        if (worked)
        {
            setPlaceableShips(getPlaceableShips()-shipSize);
        }
    }

    //Legt die Stellen fest, wo man kein Schiff platzieren darf/soll/kann.
    //(Damit sind diese Abstände gemeint, die man zwischen den Schiffen halten muss.)
    void placeNeededSpace(int x, int y, int shipSize, char hv)
    {
        if (shipSize != 0)
        {
            if (hv == 'H')
            {
                for (int i = -1; i < 2; i++)
                {
                    for (int j = -1; j <= shipSize; j++)
                    {
                        if (i == 0)
                        {
                            try {
                                getFireld()[y][x - 1] = 22;
                            }catch (IndexOutOfBoundsException ignored){}
                            try {
                                getFireld()[y][x + shipSize] = 22;
                            }catch (IndexOutOfBoundsException ignored){}
                            i++;
                        }
                        try {
                            getFireld()[y + i][x + j] = 22;
                        }catch (IndexOutOfBoundsException ignored){}
                    }
                }
            } else
            {
                for (int i = -1; i < 2; i++)
                {
                    for (int j = -1; j <= shipSize; j++)
                    {
                        if (i == 0)
                        {
                            try {
                                getFireld()[y - 1][x] = 22;
                            }catch (IndexOutOfBoundsException ignored){}
                            try {
                                getFireld()[y + shipSize][x] = 22;
                            }catch (IndexOutOfBoundsException ignored){}
                            i++;
                        }
                        try {
                            getFireld()[y + j][x + i] = 22;
                        }catch (IndexOutOfBoundsException ignored){}
                    }
                }
            }
        }
    }


    //Gibt das gesamte Feld aus. Nur zum System Testen gebaut.
    void printBoard()
    {
        System.out.println("\n");
        for (int i = 0; i <= Game.FIRELDSIZE; i++)
        {
            System.out.print(i+" | ");
        }
        System.out.print("\n");
        for (int i = 0; i < Game.FIRELDSIZE; i++)
        {
            System.out.print((i+1)+" | ");
            for (int j = 0; j < Game.FIRELDSIZE; j++)
            {
                System.out.print(getFireld()[i][j]+" | ");
            }
            System.out.print("\n");
        }
    }
}
