package games.connectfour;

import java.io.IOException;

import hsa.Console;
import utils.Utils;

public class ConnectFour
{
    static Console c;
    static Board board;

    //Variable Declaration
    static int input;

    //Run method that throws IOException and InterruptedException.
    public static void run() throws IOException, InterruptedException
    {
        //Instantiates Board and Console.
        board = new Board();
        c = new Console(30, 75);

        //Displays the board.
        board.display();
        c.println("Each player will enter the number of the column they want to place their piece when prompted. The first player to get 4  pieces in a row, column, or diagonal, wins. Enter 0 to quit the game and return to the main menu.");
        
        //Cycles until the board is filled or one side wins.
        for(int move = 1; board.checkWin() == 0 && move <= 42; move += 2)
        {
            //Cycles through both players' moves.
            for(int player = 1; player <= 2 && board.checkWin() != 1; player++)
            {
                //Prompts for the move.
                c.print("\nPlayer " + player + " move: ");
                input = Utils.input("", board.getValidMoves(), c);

                //Puts the move on the board and displays the board once more.
                board.getMove(player, input);
                board.display();
            }
        }

        //Tells the user who has won or if they have tied.
        board.winMessage(c);

        //Prompts the user to return to the main menu.
        c.print("\nPress any key to go back to the main menu. ");
        c.getChar();
    }
}