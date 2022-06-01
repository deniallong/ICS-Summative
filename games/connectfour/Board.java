package games.connectfour;

import java.io.File;
import java.io.IOException;

import java.awt.Color;
import java.awt.Image;

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
    private int yellowPositions[][];
    private int redPositions[][];
    
    Board()
    {
        board = new int[7][7];

        for(int rowIndex = 0; rowIndex < 7; rowIndex++)
            for(int columnIndex = 0; columnIndex < 7; columnIndex++)
                board[rowIndex][columnIndex] = 0;
    }

    public void displayCoin(int[] move, int player)
    {
        String piece;
        
        if(colour == "y")
            piece = "";
        else if(colour == "r")
            piece = "";
        
        i.display();
    }

    public void display() throws IOException
    {
        c.clear();

        i.display(boardImage, 0, 0, c);

        for(int rowIndex = 0; rowIndex < board.length; rowIndex++)
	    {
		    for(int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++)
                displayCoin(rowIndex, columnIndex, board[rowIndex][columnIndex]);
        
	        c.println();
	    }
    }
    
    public boolean checkWin()
    {
        
    }
    
    public boolean checkTie()
    {
        int unfilled;

        unfilled = 0;
        
        for(int rowIndex = 0; rowIndex < 7; rowIndex++)
            for(int columnIndex = 0; columnIndex < 7; columnIndex++)
                if(board[rowIndex][columnIndex] == 0)
                    unfilled++;

        if(unfilled == 0)
            return true;

        else
            return false;
    }

    public void getMove(int player, int[] move)
    {
        Utils.input("Enter the column you want to drop your piece: ", 1, 7);
        
        board[move[0]][move[1]] = player;
    }

    public static void initialize() {
        c = new Console();
        i = new ImageDisplayer();

        boardImage = new File("resources/connectfour/images/Connect4Board.png");
        yellowPieceImage = new File("resources/images/Connect4YellowPiece.png")
        redPieceImage = new File("resources/images/Connect4RedPiece.png");
    }
}