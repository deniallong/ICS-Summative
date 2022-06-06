package games.connectfour;

import java.io.IOException;

import hsa.Console;
import utils.Utils;

public class ConnectFour
{
	static Console c;
    private static Board b;

    public static void run() throws IOException
    {
        int move;

        c = new Console();
        b = new Board();

        b.display();
        c.println("Each player will enter the column they want to place their piece when prompted. Columns are numbered 1 to 7 from left to right. The first player to get 4  pieces in a row, column, or diagonal, wins.");
        
        for(int moveNumber = 0; moveNumber < 5; moveNumber++)
        {
            for(int player = 1; player <= 2; player++)
            {
                c.println("\nPlayer " + player + " move.");
                b.getMove(player, Utils.input("Enter the column you want to drop your piece: ", 1,7, c));
                b.display();
            }
        }

        /* while(b.checkWin(1) == false && b.checkWin(2) == false && b.checkTie() == false)
        {
            for(int player = 1; player <= 2; player++)
            {
                c.println("\nPlayer " + player + " move.");
                b.getMove(player, Utils.input("Enter the column you want to drop your piece: ", 1,7, c));
                b.display();
            }
        }

        if(b.checkWin(1))
            c.println("Yellow wins!");
        
        else if(b.checkWin(2))
            c.println("Red wins!");
        
        else if(b.checkTie())
            c.println("It's a tie."); */
       
    }
}