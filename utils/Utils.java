package utils;

import hsa.Console;

public class Utils
{
    static Console c;

	public static int input(String prompt, int[] choices, Console c) 
    {
		//Variable Declaration
		int input;
	
		//Prompts user to enter an integer.
		c.print(prompt);
		input = c.readInt();
	
		//Checks if integer is one of the valid choices.
		if(search(choices, input).length >= 1)
			//Returns the valid input.
			return input;
			
		else
		{
			//Outputs the valid choices and prompts user to try again.
			c.println("\nYour input is invalid. Inputs must be an integer in this set: ");

			for(int index = 0; index < choices.length - 1; index++)
			{
				c.print(choices[index] + ", ");
			}
			
			c.println("or " + choices[choices.length - 1] + ".");
			c.print("Try a valid input: ");
			
			//Returns the method.
			return input("", choices, c);
		}
    }

    public static int[] add(int[] array, int addition)
	{
		//Declares the array.
		int returnArray[] = new int[array.length + 1];

		//Enters all the elements of the old array into the new array.
		for(int index = 0; index < array.length; index++)
		{
			returnArray[index] = array[index];
		}
		
		//Adds the new element to the end of the array.
		returnArray[array.length] = addition;

		//Returns the new array.
		return returnArray;
	}

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
	}

	public static int[] search(int[] searchArray , int target) 
    {
		//Variable Declaration
		int returnArray[] = new int[0];

		//Adds the instances where the target is found to the array.
		for(int index = 0; index < searchArray.length; index++)
		{            
			if(searchArray[index] == target)
				returnArray = add(returnArray, index);
		}

		//Returns the array of indices.
		return returnArray;
    }

	public static boolean consecutive(int[] array, int target, int length)
	{
		//Variable Declaration
		int rowLength;

		//Variable Initialization
		rowLength = 0;

		//Cycles through the array, noting down how long rows are and setting the count back to 0 when the row is broken.
		for(int index = 0; index < array.length; index++)
		{
			if(array[index] == target)
			{
				rowLength++;

				if(rowLength == length)
				{
					return true;
				}
			}

			else
			{
				rowLength = 0;
			}
		}

		//Returns false (if true is not returned from the code above first).
		return false;
	}
}