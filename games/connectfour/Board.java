package games.connectfour;

import java.io.File;
import java.io.IOException;

import hsa.Console;
import utils.*;

public class Board
{
    //Declares the tools used.
    static ImageDisplayer i;
 
    //Declares the files used.
    static File boardImage;
    static File yellowPieceImage;
    static File redPieceImage;
    static File winFade;
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

        //Fills all the indices of the array with 0.
        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[row].length; col++)
            {
                board[row][col] = 0;
            }
        }

        //Creates new instance of utils.ImageDisplayer.
        i = new ImageDisplayer();

        //Initializes the files with their paths.
        boardImage = new File("resources/connectfour/images/Connect4Board.png");
        yellowPieceImage = new File("resources/connectfour/images/Connect4YellowPiece.png");
        redPieceImage = new File("resources/connectfour/images/Connect4RedPiece.png");
        winFade = new File("resources/connectfour/images/WinFade.png");
        yellowWin = new File("resources/connectfour/images/Player1WinMessage.png");
        redWin = new File("resources/connectfour/images/Player2WinMessage.png");
        tie = new File("resources/connectfour/images/TieMessage.png");
    }

    public void displayPiece(int row, int col, int player) throws IOException
    {
        //Variable Declaration
        int xMargin;
        int yMargin;
        int width;
        int height;
        int xPosition;
        int yPosition;
        
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
        move[0] = Utils.search(getColumns()[move[1]], 1).length + Utils.search(getColumns()[move[1]], 2).length;

        //Puts the player's number in the correct index of "board".
        board[move[0]][move[1]] = player;
    }

    public int[][] getRows()
    {
        return board;
    }

    public int[][] getColumns()
	{
		//Declares an array.
		int returnArray[][] = new int[board[0].length][board.length];

		//Enters all elements in the specified column of a 2 dimensional array into the previously declared array.
		for(int row = 0; row < returnArray.length; row++)
		{
			for(int col = 0; col < returnArray[row].length; col++)
			{
				returnArray[row][col] = board[col][row];
			}
		}
		
		//Returns the new array.
		return returnArray;
	}

    public int[][] getDiagonals()
	{
		//Variable Declaration
		int dia;
        int rowTemp;
        
        //Declares an array.
		int returnArray[][] = new int[2 * (board.length + board[0].length - 1)][0];

        //Variable Initialization
		dia = 0;

        //Cycles through both directions of diagonals.
        for(int dir = 1; dir >= -1; dir -= 2)
        {
            //Cycles through row and column coordinates.
            for(int row = 0; row >= 0 && row < board.length; row += 1)
            {
                //Initializes temporary row variable.
                rowTemp = row;

                //Adds an array of diagonals based on the row.
                for(int col = 0; rowTemp >= 0 && rowTemp < board.length && col >= 0 && col < board[row].length; col += 1)
                {
                    returnArray[dia] = Utils.add(returnArray[dia], board[rowTemp][col]);

                    rowTemp -= dir;
                }

                dia++;

                //Changes the value of temporary row variable. The expression gives board.length - 1 when dir is 1 and 0 when dir is -1.
                rowTemp = (board.length - 1) * (1 + dir) / 2;

                //Adds an array that corresponds to the first array.
                for(int col = board[row].length - 1 - row; rowTemp >= 0 && rowTemp < board.length && col >= 0 && col < board[row].length; col += 1)
                {
                    returnArray[dia] = Utils.add(returnArray[dia], board[rowTemp][col]);

                    rowTemp -= dir;
                }

                dia++;
            }
        }

        //Returns the array of diagonals.
		return returnArray;
	}


    public boolean checkWin(int player)
    {
        //Declares arrays.
        int arrayToCheck[][] = new int[0][0];
        arrayToCheck = Utils.add(arrayToCheck, getRows());
        arrayToCheck = Utils.add(arrayToCheck, getColumns());
        arrayToCheck = Utils.add(arrayToCheck, getDiagonals());

        //Loops through every row, column, and diagonal of "board" for the same player's number appearing 4 times consecutively.
        for(int index = 0; index < arrayToCheck.length; index++)
        {
            if(Utils.consecutive(arrayToCheck[index], player, 4))
            {
                    return true;
            }
        }
        
        return false;
    }
    
    public void winMessage(Console c) throws IOException
    {
        i.display(winFade, 0, 0);
        
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
        else
        {
            c.println("\nIt's a tie.");
            i.display(tie, 0, 0);
        }
    }
}