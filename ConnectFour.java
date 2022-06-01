import hsa.Console;
import java.awt.*;

public class ConnectFour
{
    static Console c;

    static Board b;

    public static void main(String[] args)
    {
        b = new Board();
    }
}

class Board
{
    static Console c;
    
    static String board[][];

    static int yellowPositions;
    static int redPositions;
    
    Board()
    {
        board = new String[7][7];

        for(int rowIndex = 0; rowIndex < 7; rowIndex++)
            for(int columnIndex = 0; columnIndex < 7; columnIndex++)
                board[rowIndex][columnIndex] = "0";
    }

    public static void print()
    {
        c.clear();

        for (int rowIndex = 0; rowIndex < board.length; rowIndex++)
	    {
		    c.print(board[rowIndex][0] + " ");

		    for (int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++)
		        c.print(board[rowIndex][columnIndex] + " ");
        
	    c.println();
	    }
    }
    
    public static boolean checkYellowWin()
    {
        
    }

    public static boolean checkRedWin()
    {

    }
    
    public static boolean checkTie()
    {
        int unfilled;

        unfilled = 0;
        
        for(int rowIndex = 0; rowIndex < sideLength; rowIndex++)
            for(int columnIndex = 0; columnIndex < sideLength; columnIndex++)
                if(board[rowIndex][columnIndex] == "0")
                    unfilled++;

        if(unfilled == 0)
            return true;

        else
            return false;
    }

    public static String[][] getMove(String player, int[] move)
    {
        board[move[0]][move[1]] = player;
        return board;
    }

    public static int[] getMove(String player)
    {
        input("Enter the column you want to drop your piece: ", 1, 7);

    }
}