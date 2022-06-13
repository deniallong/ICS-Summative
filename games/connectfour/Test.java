package games.connectfour;

import utils.*;

public class Test {
    
    public static int[][] getDiagonals(int[][] board)
	{
		int diagonalIndex;

		int returnArray[][] = new int[2 * (board.length + board[0].length - 1)][0];

		diagonalIndex = 0;

        for(int dir = 1; dir >= -1; dir -= 2)
        {
            for(int row = 0; row >= 0 && row < board.length; row += 1)
            {
                int rowTemp = row;

                for(int col = 0; rowTemp >= 0 && rowTemp < board.length && col < board[row].length; col += 1)
                {
                    returnArray[diagonalIndex] = Utils.add(returnArray[diagonalIndex], board[rowTemp][col]);

                    rowTemp -= dir;
                }

                diagonalIndex++;
            }

            for(int col = 1; col < board[0].length; col++)
			{
				int colTemp = col;

				for(int row = (dir - 1) / (-2) * (board.length - 1); row >= 0 && row < board.length && colTemp < board[row].length; row += dir)
				{
					returnArray[diagonalIndex] = Utils.add(returnArray[diagonalIndex], board[row][colTemp]);

					colTemp++;
				}

				diagonalIndex++;
			}
        }

		return returnArray;
	}

    public static void main(String[] args)
    {
        int array[][] = {{1,2,3,4,5,6,7},
                         {7,6,5,4,3,2,1},
                         {1,2,3,4,5,6,7},
                         {7,6,5,4,3,2,1},
                         {1,2,3,4,5,6,7},
                         {7,6,5,4,3,2,1}};

        int diagonals[][] = getDiagonals(array);

        for(int row = 0; row < diagonals.length; row++)
        {
            for(int col = 0; col < diagonals[row].length; col++)
            {
                System.out.print(diagonals[row][col] + " ");
            }

            System.out.println();
        }
    }
}