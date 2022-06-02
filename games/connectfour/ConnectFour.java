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

        Board.display();
    }
}