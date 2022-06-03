package games.connectfour;

import java.io.File;
import java.io.IOException;

import hsa.Console;
import utils.Utils;
import utils.ImageDisplayer;

public class ConnectFour
{
	static Console c;

    private static Board b;

    public static void run() throws IOException
    {
        c = new Console();
        
        Board.initialize();
        b = new Board();

        b.display();
    }
}