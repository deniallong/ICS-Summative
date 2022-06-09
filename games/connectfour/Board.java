package games.connectfour;

import java.io.File;
import java.io.IOException;

import hsa.Console;
import utils.*;

public class Board
{
    //Declares the tools used.
    static ImageDisplayer i;
    static Console c;

    //Declares the files used.
    static File boardImage;
    static File yellowPieceImage;
    static File redPieceImage;
    static File yellowWin;
    static File redWin;
    static File tie;

    //Declares a 2 dimensional array "board".
    private int board[][];
    
    //Constructor that executes once a an instance of Board is created.
    Board()
    {
        //Initializes "board".
        board = new int[6][7];

        //Calls the Board.initialize method.
        initialize();

        //Fills all the indices of the array with 0.
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[row].length; col++)
            {
                board[row][col] = 0;
            }
        }
    }

    private static void initialize()
    {
        //Creates new instance of utils.ImageDisplayer.
        i = new ImageDisplayer();

        //Initializes the files with their paths.
        boardImage = new File("resources/connectfour/images/Connect4Board.png");
        yellowPieceImage = new File("resources/connectfour/images/Connect4YellowPiece.png");
        redPieceImage = new File("resources/connectfour/images/Connect4RedPiece.png");
        yellowWin = new File("resources/connectfour/images/Player1WinMessage.png");
        redWin = new File("resources/connectfour/images/Player2WinMessage.png");
        tie = new File("resources/connectfour/images/TieMessage.png");
    }

    public void displayPiece(int row, int col, int player) throws IOException
    {
        //Variable Declaration
        int xMargin, yMargin, width, height, xPosition, yPosition;
        
        //Variable Initialization
        xMargin = 41;
        yMargin = 358;
        width = 58;
        height = 58;

        xPosition = col * width + xMargin;
        yPosition = yMargin - row * height;

        //Decides to display yellow or red pieces depending on what player is in the space.
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
        //Displays the image of the board.
        i.display(boardImage, 0, 0);

        //Displays the corresponding piece or nothing in each index of "board".
        for(int row = 0; row < board.length; row++)
	    {
		    for(int col = 0; col < board[row].length; col++)
            {
                if(board[row][col] != 0)
                {
                    displayPiece(row, col, board[row][col]);
                }
            }
	    }
    }
    
    public int[] getValidMoves()
    {
        //Declares an empty array.
        int validMoves[] = new int[0];

        //Adds the numbers of the columns where pieces can be dropped.
        for(int col = 0; col < board[5].length; col++)
        {
            if(board[5][col] == 0)
            {
                validMoves = Utils.add(validMoves, col + 1);
            }
        }

        //Returns the array of columns.
        return validMoves;
    }
    
    public void getMove(int player, int input)
    {
        //Declares an array of 2 elements.
        int move[] = new int[2];
        
        //Initializes each index of the array with the coordinates.
        move[1] = input - 1;
        move[0] = Utils.search(Utils.getColumn(board, move[1]), 1).length + Utils.search(Utils.getColumn(board, move[1]), 2).length;

        //Puts the player's number in the correct index of "board".
        board[move[0]][move[1]] = player;
    }

    public boolean checkWin(int player)
    {
        //Loops through every row of "board" for the same player's number appearing 4 times consecutively.
        for(int row = 0; row < board.length; row++)
        {
            if(Utils.consecutive(board[row], player, 4))
            {
                return true;
            }
        }

        //Loops through every column of "board" for the same player's number appearing 4 times consecutively.
        for(int col = 0; col < board[0].length; col++)
        {
            if(Utils.consecutive(Utils.getColumn(board, col), player, 4))
            {
                return true;
            }
        }

        //Loops through every diagonal of "board" for the same player's number appearing 4 times consecutively.
        for(int diagonalIndex = 0; diagonalIndex < Utils.getDiagonals(board).length; diagonalIndex++)
        {
            if(Utils.consecutive(Utils.getDiagonals(board)[diagonalIndex], player, 4))
            {
                return true;
            }
        }
        
        return false;
    }

    public boolean checkTie()
    {
        //Checks if there are any valid moves. If none, that means all spaces are filled and there is a tie.
        if(getValidMoves().length == 0)
        {
            return true;
        }

        return false;
    }
    
    public void winMessage(Console c) throws IOException
    {
        //Displays the win message for player 1 if they win.
        if(checkWin(1))
        {
            c.println("\nPlayer 1 wins!");
            i.display(yellowWin, 0, 0);
        }
        
        //Displays the win message for player 2 if they win.
        else if(checkWin(2))
        {
            c.println("\nPlayer 2 wins!");
            i.display(redWin, 0, 0);
        }
        
        //Displays the tie message when there is a tie.
        else if(checkTie())
        {
            c.println("\nIt's a tie.");
            i.display(tie, 0, 0);
        }
    }
}