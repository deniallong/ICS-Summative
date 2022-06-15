package games.connectfour;

import java.io.IOException;

import hsa.Console;
import utils.Utils;

public class ConnectFour
{
	static Console c;
    static Board board;

    public static void run() throws IOException, InterruptedException
    {
        board = new Board();
        c = new Console(30, 75);

        board.display();
        c.println("Each player will enter the number of the column they want to place their piece when prompted. The first player to get 4  pieces in a row, column, or diagonal, wins.");
        
        for(int move = 1; board.checkWin() == 0 && move <= 42; move += 2)
        {
            for(int player = 1; player <= 2 && board.checkWin() != 1; player++)
            {
                c.print("\nPlayer " + player + " move: ");
                board.getMove(player, Utils.input("", board.getValidMoves(), c));
                board.display();
            }
        }

        board.winMessage(c);

        c.print("\nPress any key to go back to the main menu");
        c.getChar();
    }
}