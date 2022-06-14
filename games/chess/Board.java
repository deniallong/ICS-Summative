package games.chess;

import java.io.File;
import java.io.IOException;

import utils.*;

import games.chess.Pieces.*;
import games.chess.Structures.*;

public class Board 
{

    // Declaring variables
    static File boardImage;
    static File whiteWinImage;
    static File blackWinImage;
    static File tieImage;
    static ImageDisplayer i;

    public int rows;
    public int cols;

    public ChessPiece[][] board;

    static 
    {
        // Initializing variables
        i = new ImageDisplayer();

        boardImage = new File("resources/chess/images/chessboard.png");
        whiteWinImage = new File("resources/chess/images/whitewin.png");
        blackWinImage = new File("resources/chess/images/blackwin.png");
        tieImage = new File("resources/chess/images/tie.png");
    }

    public Board() throws IOException
    {
        // Initializing variables
        this.rows = 8;
        this.cols = 8;
        this.board = new ChessPiece[8][8];

        // Initializing board
        // Placing pawns
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(0, 1, i);
            board[6][i] = new Pawn(1, 6, i);
        }

        // Placing Rooks
        board[0][0] = new Rook(0, 0, 0);
        board[0][7] = new Rook(0, 0, 7);
        board[7][0] = new Rook(1, 7, 0);
        board[7][7] = new Rook(1, 7, 7);

        // Placing Knight
        board[0][1] = new Knight(0, 0, 1);
        board[0][6] = new Knight(0, 0, 6);
        board[7][1] = new Knight(1, 7, 1);
        board[7][6] = new Knight(1, 7, 6);

        // Placing Bishops
        board[0][2] = new Bishop(0, 0, 2);
        board[0][5] = new Bishop(0, 0, 5);
        board[7][2] = new Bishop(1, 7, 2);
        board[7][5] = new Bishop(1, 7, 5);

        // Placing Kings
        board[0][4] = new King(0, 0, 4);
        board[7][4] = new King(1, 7 ,4);

        // Placing Queens
        board[0][3] = new Queen(0, 0, 3);
        board[7][3] = new Queen(1, 7, 3);
    }

    // Displays the board and all its pieces
    public void display() throws IOException
    {

        // Displaying the board images
        i.display(boardImage, 0, 0);
        
        // Calling the display method on each piece
        for (int row = 0; row < board.length; row++) 
        {
            for (int col = 0; col < board[row].length; col++)
            {
                if (board[row][col] != null)
                { 
                    board[row][col].display();
                }
            }
        }
    }

    // Checking if the given two pairs are a valid move
    public boolean isValidMove(Pair start, Pair end, int playerTurn)
    {
        // Declaring Variables
        int startr;
        int startc;

        // Initializing variables
        startr = start.a;
        startc = start.b;

        // Checking if the move is within the valid moves of the piece
        if (board[startr][startc] != null && board[startr][startc].getPlayer() == playerTurn)
        {
            Pair[] moves;

            moves = board[startr][startc].getMoves(board);

            for (int i = 0; i < moves.length; i++)
            {
                if (moves[i].equals(end))
                {
                    return true;
                }
            }

            return false;
        } 
        else 
        {
            return false;
        }
    }

    // Getter for the board object
    public ChessPiece[][] getBoard()
    {
        return board;
    }

    // Displays the win message
    public static void displayWin(int player) throws IOException
    {
        if (player == 0)
        {
            i.display(whiteWinImage, 0, 0);
        }
        else 
        {
            i.display(blackWinImage, 0, 0);
        }
    }

    // Displays a tie message
    public static void displayTie() throws IOException
    {
        i.display(tieImage, 0, 0);
    }
}