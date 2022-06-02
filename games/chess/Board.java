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
            board[1][i] = new Pawn(0);
            board[6][i] = new Pawn(1);
        }
    }

    public void display() throws IOException
    {

        i.display(boardImage, 0, 0);
        board[1][0].display(1, 0);
    }

    public int[][] getCheckedSpaces()
    {
        // TODO

        return new int[0][0];
    }
}
