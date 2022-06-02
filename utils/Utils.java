package utils;

import hsa.Console;

public class Utils
{
    static Console c;

    public static int input(String prompt, int low, int high) 
    {
		//Variable Declaration
		int input;
	
		//Prompts user to enter an integer.
		c.print(prompt);
		input = c.readInt();
	
		//Checks if integer is within the valid range.
		if(input >= low && input <= high)
			//Returns the valid input.
			return input;
			
		else
		{
			//Outputs the valid range and prompts user to try again.
			c.println("Your input is invalid. Inputs must be an integer between " + low + " and " + high + ", inclusive.");
			c.print("Try a valid input: ");
			
			//Returns the method.
			return input("", low, high);
		}
    } 

    public static int[] add(int[] array, int addition)
	{
		//Declares the array.
		int returnArray[] = new int[array.length + 1];

		//Enters all the elements of the old array into the new array.
		for(int index = 0; index < array.length; index++)
			returnArray[index] = array[index];
		
		//Adds the new element to the end of the array.
		returnArray[array.length] = addition;

		//Returns the new array.
		return returnArray;
	}

<<<<<<< HEAD
	public static int[] search(int[] searchArray , int target) 
    {
		//Variable Declaration
		int found, returnIndex;

		//Variable Initialisation
		found = 0;
		returnIndex = 0;
		
		//Notes down how many instances the array contains the target.
		for(int index = 0; index < searchArray.length; index++)
		{            
			if(searchArray[index] == target)
				found++;
		}

		//Declares an array with corresponding length.
		int returnArray[] = new int[found];

		//Enters the indices where the target is found in the original array in the new array.
		for(int index = 0; index < searchArray.length; index++)
		{
			if(searchArray[index] == target)
			{
				returnArray[returnIndex] = index;
				returnIndex++;
			}
		}

		//Returns the array of indices.
		return returnArray;
    }

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

	//change this later
	public static int[] sort(int[] array)
	{               
		//Variable Declaration.
		int a, b, t;

		//Loops therough the array, switching elements that are out of order until the array is sorted.
		for(a = 1; a < array.length; a++) 
			for(b = array.length - 1; b >= a; b--) 
				if(array[b - 1] > array[b])
				{
					t = array[b - 1];
					array[b - 1] = array[b];
					array[b] = t;
				}
		
		//Returns the array.
		return array;
	}

	public static boolean inARow(int[] array, int target, int length)
	{
		int rowLength;
		
		rowLength = 0;

		for(int index = 0; index < array.length; index++)
		{
			for(int consecutiveNumber = 0; consecutiveNumber < length; consecutiveNumber++)
				if(array[index + consecutiveNumber] == target)
					rowLength++;
				
			if(rowLength == length)
				return true;
		}
=======
	public static Pair[] add(Pair[] array, Pair addition)
	{
		//Declares the array.
		Pair returnArray[] = new Pair[array.length + 1];

		//Enters all the elements of the old array into the new array.
		for(int index = 0; index < array.length; index++)
			returnArray[index] = array[index];
		
		//Adds the new element to the end of the array.
		returnArray[array.length] = addition;

		//Returns the new array.
		return returnArray;
>>>>>>> e10333e17f34e48b03e61559ed260bcf00202079
	}
}
