/**
 * Should construct triangular geometric objects
 * 
 * Matt Warman
 * 1.1
 */

public class Triangle extends GeometricObject {
	
	private double side1; // hold the length of side 1
	private double side2; // hold the length of side 2
	private double side3; // hold the length of side 3
	
	Triangle() // construct a default triangle
	{
		side1 = side2 = side3 = 1; // set all side lengths to a default of 1
	}
	
	Triangle(double setSide1, double setSide2, double setSide3) // construct a triangle with custom side lengths
	{
		side1 = setSide1; // set side 1 to the input value
		side2 = setSide2; // set side 2 to the input value
		side3 = setSide3; // set side 3 to the input value
	}
	
	void setSide1(double newSideLength) // change the length of side 1
	{
		side1 = newSideLength; // set the length of side1 to the new input value
	}
	
	double getSide1() // read the length of side 1
	{
		return side1; // return the length of side 1
	}
	
	void setSide2(double newSideLength) // change the length of side 2
	{
		side2 = newSideLength; // set the length of side2 to the new input value
	}
	
	double getSide2() // read the length of side 2
	{
		return side2; // return the length of side 2
	}
	
	void setSide3(double newSideLength) // change the length of side 3
	{
		side3 = newSideLength; // set the length of side3 to the new input value
	}
	
	double getSide3() // read the length of side 3
	{
		return side3; // return the length of side 3
	}
	
	double calculateArea() // calculate the area of the triangle
	{
		double s = (side1 + side2 + side3)/2; // define variable s to make area calculation easier (equation is given)
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side1)); // return the calculated area (equation is given)
	}
	
	double calculatePerimeter() // calculate the length of the perimeter of the triangle
	{
		return (side1 + side2 + side3); // return the calculated perimeter of the triangle (equation is given)
	}

	
	public String toString() // output triangle information to a string
	{
		if (isFilled()) // if the triangle is filled
		{
			return ("This triangle has sides of lengths:\nSide 1: " + side1 + "\nSide 2: " + side2 + "\nSide 3: " + side3 +
					"\nThe color is: " + getColor() + "\nThe triangle is filled\nThe area of the triangle is: " + calculateArea() + 
					"\nThe perimeter of the triangle is: " + calculatePerimeter() + "\n\n"); // return triangle information
			
		}
		else // if the triangle is NOT filled
		{
			return ("This triangle has sides of lengths:\nSide 1: " + side1 + "\nSide 2: " + side2 + "\nSide 3: " + side3 +
					"\nThe color is: " + getColor() + "\nThe triangle is not filled\nThe area of the triangle is: " + calculateArea() + 
					"\nThe perimeter of the triangle is: " + calculatePerimeter() + "\n\n"); // return triangle information
		}
	}
		
}
