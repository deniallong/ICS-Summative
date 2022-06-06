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

        initialize();

        for(int rowIndex = 0; rowIndex < board.length; rowIndex++)
        {
            for(int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++)
            {
                board[rowIndex][columnIndex] = 0;
                //board[rowIndex][columnIndex] = (int) (Math.random() * 5) + 1;
            }
        }
    }

    private static void initialize()
    {
        i = new ImageDisplayer();

        boardImage = new File("resources/connectfour/images/Connect4Board.png");
        yellowPieceImage = new File("resources/connectfour/images/Connect4YellowPiece.png");
        redPieceImage = new File("resources/connectfour/images/Connect4RedPiece.png");
    }

    public void displayPiece(int rowIndex, int columnIndex, int player) throws IOException
    {
        int xBase, yBase, width, height, xPosition, yPosition;
        
        xBase = 41;
        yBase = 308;
        width = 58;
        height = 58;

        xPosition = columnIndex * width + xBase;
        yPosition = yBase - rowIndex * height;

        if(player == 1)
        {
            i.display(yellowPieceImage, xPosition, yPosition);
        }
        else if(player == 2)
        {
            i.display(redPieceImage, xPosition, yPosition);
        }
    }

    public void display() throws IOException
    {
        i.display(boardImage, 0, 0);

        for(int rowIndex = 0; rowIndex < board.length; rowIndex++)
	    {
		    for(int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++)
            {
                if(board[rowIndex][columnIndex] != 0)
                {
                    displayPiece(rowIndex, columnIndex, board[rowIndex][columnIndex]);
                }
            }
	    }
    }
    
    public int[] getValidMoves()
    {
        int validMoves[] = new int[0];

        for(int columnIndex = 0; columnIndex < board[5].length; columnIndex++)
        {
            if(board[5][columnIndex] == 0)
            {
                validMoves = Utils.add(validMoves, columnIndex + 1);
            }
        }

        return validMoves;
    }
    
    public void getMove(int player, int input)
    {
        int move[] = new int[2];
        
        move[1] = input - 1;
        move[0] = Utils.search(Utils.getColumn(board, move[1]), 1).length + Utils.search(Utils.getColumn(board, move[1]), 2).length;

        board[move[0]][move[1]] = player;
    }

    public boolean checkWin(int player)
    {
        int failed;

        int column[] = new int[7];
        int diagonal[];

        failed = 0;

        for(int rowIndex = 0; rowIndex < board.length; rowIndex++)
        {
            if(Utils.inARow(board[rowIndex], player, 4))
            {
                return true;
            }
            else
            {
                failed++;
            }
        }

        for(int columnIndex = 0; columnIndex < board[columnIndex].length; columnIndex++)
        {
            if(Utils.inARow(column, player, 4))
            {
                return true;
            }
            else
            {
                failed++;
            }
        }
        
        //for(int )
        
        if(failed == 2)
        {    
            return false;
        }
    }
    
    public boolean checkTie()
    {
        int unfilled;

        unfilled = Utils.search(board, 0).length;

        if(unfilled == 0)
        {
            return true;
        }

        else
        {
            return false;
        }
    }
    
    public void winMessage(int player)
    {
        if(checkWin(1))
        {
            c.println("Yellow wins!");
        }
        
        else if(checkWin(2))
        {
            c.println("Red wins!");
        }
        
        else if(checkTie())
        {
            c.println("It's a tie.");
        }

        this.displayWin(player);
    }

    public void displayWin(int player)
    {
        
    }
}