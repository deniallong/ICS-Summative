package games.connectfour;

import hsa.Console;
import java.awt.*;

public class ConnectFour
{
    static Console c;

    static Board b;

    public void run()
    {
        Board.initialize();

        b = new Board();
    }
}