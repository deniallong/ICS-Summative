package games.connectfour;

import java.io.File;
import java.io.IOException;

import hsa.Console;
import utils.Utils;
import utils.ImageDisplayer;

public class Test
{
    public static int[][] search(int[][] searchArray , int target) 
    {
		//Variable Declaration
		int found, returnRowIndex;

		//Variable Initialisation
		found = 0;
		returnRowIndex = 0;
		
		//Notes down how many instances the array contains the target.
		for(int rowIndex = 0; rowIndex < searchArray.length; rowIndex++)
			for(int columnIndex = 0; columnIndex < searchArray[rowIndex].length; columnIndex++)
				if(searchArray[rowIndex][columnIndex] == target)
					found++;

		//Declares an array with corresponding dimensions.
		int returnArray[][] = new int[found][2];

		//Enters the coordinates where the target is found in the original array in the new array.
		for(int rowIndex = 0; rowIndex < searchArray.length; rowIndex++)
		{
			rowIndex++;

			for(int columnIndex = 0; columnIndex < searchArray[rowIndex].length; columnIndex++)
				if(searchArray[rowIndex][columnIndex] == target)
				{
					returnArray[returnRowIndex][0] = rowIndex;
					returnArray[returnRowIndex][1] = columnIndex;

					returnRowIndex++;
				}
		}

		//Returns the array of indices.
		return returnArray;
	}

	public static boolean inARow(int[] array, int target, int length)
	{
		int rowLength;
		
		rowLength = 0;

		for(int index = 0; index < array.length; index++)
		{
			boolean breakCondition;

			breakCondition = false;

			for(int consecutiveNumber = 0; consecutiveNumber < length && breakCondition == false; consecutiveNumber++)
				if(array[index + consecutiveNumber] == target)
					rowLength++;
				else
					breakCondition = true;
		}

		if(rowLength == length)
			return true;
		else
			return false;
	}

    static Console c;
    static ImageDisplayer i;
    static File boardFile;

    public static void main(String[] args) throws IOException
    {
        c = new Console();
        i = new ImageDisplayer();
        
        int array;
    }   
}
