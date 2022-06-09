package utils;

public class Test {
    public static void main(String[] args)
    {
        int array[][] = {{1,2,3,4,5,6,7},
                         {7,6,5,4,3,2,1},
                         {1,2,3,4,5,6,7},
                         {7,6,5,4,3,2,1},
                         {1,2,3,4,5,6,7},
                         {7,6,5,4,3,2,1}};

        int diagonals[][] = Utils.getDiagonals(array);

        for(int rowIndex = 0; rowIndex < diagonals.length; rowIndex++)
        {
            for(int columnIndex = 0; columnIndex < diagonals[rowIndex].length; columnIndex++)
            {
                System.out.print(diagonals[rowIndex][columnIndex] + " ");
            }

            System.out.println();
        }
    }
}
