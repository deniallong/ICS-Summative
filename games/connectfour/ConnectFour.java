package games.connectfour;

import hsa.Console;
import java.awt.*;

public class ConnectFour
{
    static Console c;

    static Board b;

    public void run()
    {
        c = new Console();
        
        Board.initialize();
        b = new Board();

        while(Board.checkWin(1) == false || Board.checkWin(2) = false || Board.checkTie() == false)
        {
            Board.display();
        }
    }
}