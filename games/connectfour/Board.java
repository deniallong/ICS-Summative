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
    private int Positions[][];
    
    Board()
    {
        board = new int[7][7];

        for(int rowIndex = 0; rowIndex < 7; rowIndex++)
            for(int columnIndex = 0; columnIndex < 7; columnIndex++)
                board[rowIndex][columnIndex] = 0;
    }

    public static void initialize() {
        c = new Console();
        i = new ImageDisplayer();

        boardImage = new File("resources/connectfour/images/Connect4Board.png");
        yellowPieceImage = new File("resources/images/Connect4YellowPiece.png");
        redPieceImage = new File("resources/images/Connect4RedPiece.png");
    }

    public void display() throws IOException
    {
        c.clear();

        i.display(boardImage, 0, 0, c);

        for(int rowIndex = 0; rowIndex < board.length; rowIndex++)
	    {
		    for(int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++)
                displayPiece(rowIndex, columnIndex, board[rowIndex][columnIndex]);
        
	        c.println();
	    }
    }

    public void displayPiece(int rowIndex, int columnIndex, int player) throws IOException
    {
        int xPosition, yPosition;
        
        xPosition = rowIndex * 75;
        yPosition = columnIndex * 75;

        if(player == 1)
            i.display(yellowPieceImage, xPosition, yPosition, c);
        else if(player == 2)
            i.display(redPieceImage, xPosition, yPosition, c);
    }
    
    public boolean checkWin(int player)
    {
        int inRow[];
        int inColumn[];
        int inDiagonal[];

        for(int rowIndex = 0; rowIndex < board.length; rowIndex++)
        {
            inRow = Utils.search(board[rowIndex], player);
            if(hasConsecutive(board[rowIndex]) = true)
        }
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