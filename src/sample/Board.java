package sample;

public class Board
{
    /********************************************
     * Codes:   0   -   Space
     *          1   -   Ship
     *          22  -   Needed Space
     *          404 -   Destroyed Ship
     */

    private int[][] fireld = new int[Game.FIRELDSIZE][Game.FIRELDSIZE];

    public int[][] getFireld() { return fireld; }
    public void setFireld(int[][] fireld) { this.fireld = fireld; }

    //Kontrolliert, ob an dieser Stelle 0 steht. Wenn nicht, dann gibt es false zurück.
    boolean checkForSpace(int x,int y,int shipSize,char hv)
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
        if (checkForSpace(x,y,shipSize,hv))
        {
            getFireld()[y][x] = 1;
            if (hv == 'H')
            {
                for (int i = 1; i < shipSize; i++)
                {
                    getFireld()[y][x+i] = 1;
                }
            }
            else
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
        }
    }

    //Legt die Stellen fest, wo man kein Schiff platzieren darf/soll/kann.
    //(Damit sind diese Abstände gemeint, die man zwischen den Schiffen halten muss.)
    void placeNeededSpace(int x, int y, int shipSize, char hv)
    {
        if (hv == 'H')
        {
            try {
                getFireld()[y][x - 1] = 22;
            }catch (IndexOutOfBoundsException ignored) {}
            try {
                getFireld()[y+1][x] = 22;
            }catch (IndexOutOfBoundsException ignored) {}
            try{
                getFireld()[y-1][x] = 22;
            }catch (IndexOutOfBoundsException ignored) {}
            try {
                getFireld()[y - 1][x - 1] = 22;
            }catch (IndexOutOfBoundsException ignored) {}
            try {
                getFireld()[y + 1][x - 1] = 22;
            }catch (IndexOutOfBoundsException ignored) {}
            for (int i = 1; i < shipSize; i++)
            {
                try{
                    getFireld()[y+1][x+i] = 22;
                }catch (IndexOutOfBoundsException ignored) {}
                try{
                    getFireld()[y-1][x+i] = 22;
                }catch (IndexOutOfBoundsException ignored) {}
                if (i+1 >= shipSize)
                {
                    try{
                        getFireld()[y][x+i+1] = 22;
                    }catch (IndexOutOfBoundsException ignored) {}
                    try{
                        getFireld()[y-1][x+i+1] = 22;
                    }catch (IndexOutOfBoundsException ignored) {}
                    try{
                        getFireld()[y+1][x+i+1] = 22;
                    }catch (IndexOutOfBoundsException ignored) {}
                }
            }
        }
        else
        {
            try{
                getFireld()[y-1][x] = 22;
            }catch (IndexOutOfBoundsException ignored) {}
            try{
                getFireld()[y][x+1] = 22;
            }catch (IndexOutOfBoundsException ignored) {}
            try{
                getFireld()[y][x-1] = 22;
            }catch (IndexOutOfBoundsException ignored) {}
            try{
                getFireld()[y-1][x-1] = 22;
            }catch (IndexOutOfBoundsException ignored) {}
            try{
                getFireld()[y-1][x+1] = 22;
            }catch (IndexOutOfBoundsException ignored) {}
            for (int i = 1; i < shipSize; i++)
            {
                try{
                    getFireld()[y+i][x+1] = 22;
                }catch (IndexOutOfBoundsException ignored) {}
                try{
                    getFireld()[y+i][x-1] = 22;
                }catch (IndexOutOfBoundsException ignored) {}
                if (i+1 >= shipSize)
                {
                    try{
                        getFireld()[y+i+1][x] = 22;
                    }catch (IndexOutOfBoundsException ignored) {}
                    try{
                        getFireld()[y+i+1][x-1] = 22;
                    }catch (IndexOutOfBoundsException ignored) {}
                    try{
                        getFireld()[y+i+1][x+1] = 22;
                    }catch (IndexOutOfBoundsException ignored) {}
                }
            }
        }
    }


    void printBoard()
    {
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
