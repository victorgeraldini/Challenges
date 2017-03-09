/*
 * Author: Victor Rezende Geraldini
 * E-mail: victorgeraldini@gmail.com
 * 
 * == Hell Triangle Challenge == 
 * 
 * Given a triangle of numbers, find the maximum total from top to bottom. Example:
 * 
 * 	  6
 * 	 3 5
 *  9 7 1
 * 4 6 8 4
 * 
 * In this triangle, the maximum total is 6 + 5 + 7 + 8 = 26.
 * An element can only be summed with one of the two nearest elements in the next row
 * so the element 3 in row 2 can be summed with 9 and 7, but not with 1.
 * 
 */
package hellTriangle;

import hellTriangle.HellTriangle;

/*
 * Tests the resources provided by the class 'HellTriangle'
 */
public class HellTriangleTest 
{
	/*
	 * Main class, in this case used to test:
	 * - Whether the triangle is or not valid;
	 * - If the sum of a known triangle is correct.
	 */
	public static void main(String[] args) 
	{
		//Creating a 'HellTriangle' object and the vector of elements to be inserted
		HellTriangle triangle;
		int[][] elements;
		
		//Test(1) to check if inserted elements form a triangle
		elements = new int[][]{};
		triangle = new HellTriangle(elements);
		if(triangle.isTriangle())
		{
			System.out.println("The elements " + elementsIntoString(elements) + " form a triangle!");
		}
		else 
			System.out.println("The elements " + elementsIntoString(elements) + " do not form a triangle!");
		
		//Test(2) to check if inserted elements form a triangle
				elements = new int[][]{{6}};
				triangle = new HellTriangle(elements);
				if(triangle.isTriangle())
				{
					System.out.println("The elements " + elementsIntoString(elements) + " form a triangle!");
				}
				else 
					System.out.println("The elements " + elementsIntoString(elements) + " do not form a triangle!");
				
		//Test(3) to check if inserted elements form a triangle
		elements = new int[][]{{6}, {3,2,1}};
		triangle = new HellTriangle(elements);
		if(triangle.isTriangle())
		{
			System.out.println("The elements " + elementsIntoString(elements) + " form a triangle!");
		}
		else 
			System.out.println("The elements " + elementsIntoString(elements) + " do not form a triangle!");
				
		//Test with elements that we know that form a triangle
		elements = new int[][]{{6},{3,5},{9,7,1},{4,6,8,4}};
		triangle = new HellTriangle(elements);
		if(triangle.isTriangle())
		{
			System.out.println("The elements " + elementsIntoString(elements) + " form a triangle!");
			//Check their sum. Number expected is 26.
			if(triangle.findMaxSum() == 26)
				System.out.println("\n The value expected as result, 26, was correctly found after the sum of the elements.");
			else
				System.err.println("\n The value expected as result, 26, was not found.");
		}
		else 
			System.out.println("The elements " + elementsIntoString(elements) + " do not form a triangle!");		
		
		elements = new int[][]{{6},{3,5},{9,7,1},{4,6,8,4}};
		
		System.out.println();

	}
	
	/*
	 * Method to build a string from an int[][] vector.
	 */
	public static String elementsIntoString(int[][] elements)
	{
		String str = "";
		
		if((elements != null))
		{
			str += "[";
			for(int i = 0; i < elements.length; i++)
			{
				str +="[";
				for(int j = 0; j < elements[i].length; j++)
				{
					str += (elements[i][j]);
					if(j != (elements[i].length - 1))
						str += ",";
				}
				str += "]";
			}
			str += "]";
		}
		
		return str;
	}

}
