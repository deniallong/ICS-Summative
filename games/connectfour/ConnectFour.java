package games.connectfour;

import java.io.IOException;

import hsa.Console;
import utils.Utils;

public class ConnectFour
{
	static Console c;
    static Board b;

    public static void run() throws IOException
    {
        b = new Board();
        c = new Console();

        b.display();
        c.println("Each player will enter the number of the column they want to place their piece when prompted. The first player to get 4  pieces in a row, column, or diagonal, wins.");
        
        while(b.checkWin(1) == false && b.checkWin(2) == false && b.checkTie() == false)
        {
            for(int player = 1; player <= 2 && b.checkWin(1) == false; player++)
            {
                c.print("\nPlayer " + player + " move: ");
                b.getMove(player, Utils.input("", b.getValidMoves(), c));
                b.display();
            }
        }

        b.winMessage(c);
    }
}