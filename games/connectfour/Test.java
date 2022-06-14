package games.connectfour;

import utils.*;

public class Test {
    
    public static int[][] getDiagonals(int[][] board)
	{
		//Variable Declaration
		int dia;
        int rowTemp;
        
        //Declares a matrix.
		int returnArray[][] = new int[2 * (board.length + board[0].length - 1)][0];

        //Variable Initialization
		dia = 0;

        //Cycles through both directions of diagonals.
        for(int dir = 1; dir >= -1; dir -= 2)
        {
            //Cycles through row and column coordinates.
            for(int row = 0; row >= 0 && row < board.length; row += 1)
            {
                //Cycles through both corners of the matrix.
                for(int side = -1; side <= 1; side += 2)
                {
                    //Initializes a temporary row variable.
                    rowTemp = Utils.positiveNegativeAlternate(row, Utils.positiveNegativeAlternate(0, board.length - 1, dir), side);

                    //Cycles through the array, adding diagonals to the returned matrix.
                    for(int col = Utils.positiveNegativeAlternate(0, board[row].length - row - 1, side); rowTemp >= 0 && rowTemp < board.length && col >= 0 && col < board[row].length; col += 1)
                    {
                        returnArray[dia] = Utils.add(returnArray[dia], board[rowTemp][col]);

                        rowTemp -= dir;
                    }

                    dia++;
                }
            }
        }

        //Returns the matrix of diagonals.
		return returnArray;
	}

    public static void debugArray(int [][]array) {
        System.out.println("Checking arrays:");
        for(int i = 0; i < array.length; i++) {
		    for (int j=0; j<array[i].length; j++)
			{
				System.out.print(array[i][j]);
                System.out.print(" ");
			} 
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        int array[][] = new int[6][7];

        for(int row = 0; row < array.length; row++)
        {
            for(int col = 0; col < array[row].length; col++)
            {
                array[row][col] = row * 7 + col;
                System.out.print(array[row][col] + " ");
            }
            
            System.out.println();
        }

        int diagonals[][] = getDiagonals(array);
        debugArray(diagonals);
    }
}