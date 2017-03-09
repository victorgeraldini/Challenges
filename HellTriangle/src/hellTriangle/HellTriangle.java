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

/*
 * Class that defines the triangle used in this challenges and some methods to manipulate it.
 */
public class HellTriangle 
{
	private int[][] elements;	//Elements of the triangle
	private boolean isTriangle;	//Variable assigned at the constructor that defines if the object is or not a valid triangle.
	
	/*
	 * Constructor of the class. Receive the elements to build the triangle and, if they are valid, set 'isTriangle' to true.
	 */
	public HellTriangle(int[][] elements)
	{
		if(isTriangle(elements))
		{
			setElements(elements);
			this.isTriangle = true;
		}
		else
		{
			this.isTriangle = false;
		}
	}
	
	/*
	 * Returns true if the built triangle is valid.
	 */
	public boolean isTriangle()
	{
		return this.isTriangle;
	}
	
	/*
	 * Checks if the elements inserted form a valid triangle.
	 */
	private boolean isTriangle(int [][] elements)
	{
		//Returns false if elements are null or the vector length is 0. Also considers a vector of a single element an invalid triangle.
		if((elements == null) || (elements.length == 0) || (elements.length == 1))
		{	
			return false;
		}	
		//Criteria to be a triangle: Each 'i+1' position of the vector must have 1 more element than the previous position. For
		//example, a vector where the length of i[0] == 1 and length of i[1] == 2 is valid.
		else for(int i = 0; i < elements.length; i++)
		{
			if(i == 0)
			{
				//If the number of elements in the first position of the vector is different of 1, than it is an invalid triangle.
				if(elements[0].length != 1)
				{
					return false;
				}
			}
			else if ((elements[i].length) != (elements[i-1].length + 1))
			{
				return false;
			}
		}
		
		//If all checked elements satisfied the above criteria, then the triangle is valid.
		return true;
	}
	
	/*
	 * Returns the array with the triangle elements.
	 */
	public int[][] getElements()
	{
		if(this.isTriangle)
			return this.elements;
		else
			return null;
	}
	
	/*
	 * Sets the elements of the triangle.
	 */
	private void setElements(int[][] elements)
	{
		this.elements = elements;
	}
	
	/*
	 * Finds the maximum sum of the triangle from its root (first element). Uses the recursive method findMaxSum(int, int)
	 * as its auxiliar to make the calculations.
	 */
	public int findMaxSum()
	{
		if(this.isTriangle)
		{
			int maxLeft;
			int maxRight;
			
			//Checks whether the max sum comes from the left or the right side of the tree/triangle.
			maxLeft = findMaxSum(1, 0);
			maxRight = findMaxSum(1, 1);
			
			if(maxLeft > maxRight)
			{
				return getElements()[0][0] + maxLeft;
			}
			else 
			{
				return getElements()[0][0] + maxRight;
			}
		}
		else return 0;
	}
	
	/*
	 * Recursive method used to calculate the max sum of the elements on the tree/triangle. In an int[][] vector,
	 * we consider height and index as int[height][index].
	 */
	private int findMaxSum(int height, int index) 
	{
		int maxLeft;
		int maxRight;
		
		//If not the max height of tree/triangle, return value + (max sum of subsequest nodes).
		if(height < getElements().length - 1)
		{
			//Check whether the left or right side has the max sum.
			maxLeft = findMaxSum((height + 1), index);
			maxRight = findMaxSum((height + 1), (index + 1));
			
			if(maxLeft > maxRight)
			{
				return getElements()[height][index] + maxLeft;
			}
			else 
			{
				return getElements()[height][index] + maxRight;
			}
		}
		//If is the max height of the tree/triangle, return the value itself.
		else
		{
			return getElements()[height][index];
		}
	}

}
