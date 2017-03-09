# Hell Triangle Challenge

The objective here was to create an algorithm able to complete the **Hell Triangle Challenge**, which is:
```
Given a triangle of numbers, find the maximum total from top to bottom
Example:

   6
  3 5
 9 7 1
4 6 8 4 

In this triangle the maximum total is 6 + 5 + 7 + 8 = 26. 
An element can only be summed with one of the two nearest elements in the next row so the element 3 
in row 2 can be summed with 9 and 7, but not with 1.
```
The solution I adopted is based on a recursive method, implemented in Java, able to easily calculate all
the sums possibilities able in a given "triangle". It is similar to calculate the max possible sum from a
binary tree, for example.
In this solution, there are methods to check whether the elements in the multidimensional array are or not
a valid triangle, and if the max sum of a valid triangle is or not correct.


## Why Java?
I decided to use java because it's a programming language which I already have some experience in working
with and because I thought that it would be simple to bring a solution for this challenge, which problems
consists basically on its logic implementation. I used java _1.8.0_111_.

## Execution
To execute the application, just clone or download the package to your computer and run the class `HellTriangleTest.java` (on terminal, for example, using _javac_, or opening and running the project in an IDE, like _Eclipse_).

