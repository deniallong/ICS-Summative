package games.chess.Pieces;

abstract class ChessPiece 
{
    
    private int row;
    private int col;

    public ChessPiece(int row, int col) 
    {
        this.row = row;
        this.col = col;
    }

    public int[] getMoves;
}
