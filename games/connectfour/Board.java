package games.connectfour;

import java.io.File;
import java.io.IOException;

import hsa.Console;
import utils.Utils;
import utils.ImageDisplayer;

public class Board
{
    static ImageDisplayer i;
    static Console c;

    static File boardImage;
    static File yellowPieceImage;
    static File redPieceImage;

    private int board[][];
    
    Board()
    {
        board = new int[6][7];

        for(int rowIndex = 0; rowIndex < board.length; rowIndex++)
            for(int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++)
                board[rowIndex][columnIndex] = 0;
    }

    public static void initialize()
    {
        i = new ImageDisplayer();

        boardImage = new File("resources/connectfour/images/Connect4Board.png");
        yellowPieceImage = new File("resources/connectfour/images/Connect4YellowPiece.png");
        redPieceImage = new File("resources/connectfour/images/Connect4RedPiece.png");
    }

    public void display() throws IOException
    {
        i.display(boardImage, 0, 0);

        for(int rowIndex = 0; rowIndex < board.length; rowIndex++)
	    {
		    for(int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++)
                if(board[rowIndex][columnIndex] != 0)
                    displayPiece(rowIndex, columnIndex, board[rowIndex][columnIndex]);
	    }
    }

    public void displayPiece(int rowIndex, int columnIndex, int player) throws IOException
    {
        int xPosition, yPosition;
        
        xPosition = rowIndex * 63;
        yPosition = 317 - columnIndex * 75;

        if(player == 1)
            i.display(yellowPieceImage, xPosition, yPosition);
        else if(player == 2)
            i.display(redPieceImage, xPosition, yPosition);
    }
    
    public boolean checkWin(int player)
    {
        int failed;

        int column[] = new int[7];
        int diagonal[];

        failed = 0;

        for(int rowIndex = 0; rowIndex < board.length; rowIndex++)
            if(Utils.inARow(board[rowIndex], player, 4))
                return true;
            else
                failed++;

        for(int columnIndex = 0; columnIndex < board[columnIndex].length; columnIndex++)
            if(Utils.inARow(column, player, 4))
                return true;
            else
                failed++;
        
        for(int )
        
        if(failed == 3)        
            return false;
    }
    
    public boolean checkTie()
    {
        int unfilled;

        unfilled = Utils.search(board, 0).length;

        if(unfilled == 0)
            return true;

        else
            return false;
    }

    public void getMove(int player)
    {
        int move[] = new int[2];

        move[0] = Utils.input("Enter the column you want to drop your piece: ", 1, 7);
        move[1] = Utils.search(board[move[0]], player).length;

        board[move[0]][move[1]] = player;
    }
    
}