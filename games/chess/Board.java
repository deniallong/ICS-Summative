package games.chess;

import java.io.File;
import java.io.IOException;

import utils.*;

import games.chess.Pieces.*;
import games.chess.Structures.*;

public class Board 
{

    static File boardImage;
    static ImageDisplayer i;

    private int rows;
    private int cols;
    private ChessPiece[][] board;

    static 
    {
        i = new ImageDisplayer();

        boardImage = new File("resources/chess/images/chessboard.png");
    }

    public Board() throws IOException
    {

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

    public void display() throws IOException
    {

        i.display(boardImage, 0, 0);
        
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

    public boolean isValidMove(Pair start, Pair end, int playerTurn)
    {
        int startr;
        int startc;

        startr = start.a;
        startc = start.b;

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

    public int[][] getCheckedSpaces()
    {
        // TODO

        return new int[0][0];
    }
}
