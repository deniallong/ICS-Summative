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
   //c = new Console();
        
        //Board.initialize();
        b = new Board();
        b.display();

        for(int rowIndex = 0; rowIndex < 6; rowIndex++)
        {
            for(int columnIndex = 0; columnIndex < 7; columnIndex++)
            {
                b.displayPiece(rowIndex, columnIndex, 2);
            }
        }
        

        b.print();

        /*
        while(b.checkWin(1) == false && b.checkWin(2) == false && b.checkTie() == false)
        {
            for(int player = 1; player <= 2; player++)
            {
                move = Utils.input("Enter the column you want to drop your piece", 1, 7);
                b.getMove(player, move);
                b.display();
            }
        }

        if(b.checkWin(1))
            c.println("Yellow wins!");
        
        else if(b.checkWin(2))
            c.println("Red wins!");
        
        else if(b.checkTie())
            c.println("It's a tie.");
        */
    }
}