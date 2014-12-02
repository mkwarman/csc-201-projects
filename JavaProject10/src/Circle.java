/**
 * Circle object for use in TestCircle.
 * 
 * Matt Warman
 * 1.1
 */

public class Circle {
	
	double centerX; // represents the x coordinates of the center of the circle
	double centerY; // represents the y coordinates of the center of the circle
	double radius; // represents the radius of the circle
	
	Circle() // Constructor used when no arguments are passed
	{
		centerX = 0.0; // set default value to 0
		centerY = 0.0; // set default value to 0
		radius = 1.0; // set default value to 1
	}
	
	Circle(double setCenterX, double setCenterY, double setRadius)
	{
		centerX = setCenterX; // set to given value
		centerY = setCenterY; // set to given value
		radius = setRadius; // set to given value
	}
	
	double getCenterX() // get method for centerX
	{
		return centerX;
	}
	
	double getCenterY() // get method for centerY
	{
		return centerY;
	}
	
	double getRadius() // get method for radius
	{
		return radius;
	}

	double calculateArea() // calculate and return circle area
	{
		return (Math.PI * (radius*radius)); // return pi*r^2
	}
	
	double calculatePerimeter() // calculate and return the circle circumference
	{
		return (2.0 * Math.PI * radius); // return 2*pi*r
	}
	
	boolean pointInsideCircle(double x, double y) // return true if the specified point lies within the circle
	{
		// if the distance between the point and the center of the circle is less than the radius, return true.
		// IF √(〖(x1-x2)〗^2+〖(y1-y2)〗^2 ) < radius THEN return true
		if (Math.sqrt(Math.pow((centerX - x) , 2) + Math.pow((centerY - y) , 2)) < radius)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	boolean circleInsideCircle(Circle circle) // return true if the specified circle lies completely inside of this circle
	{
		double x = circle.getCenterX(); // set x to be the input circle's center X coordinate
		double y = circle.getCenterY(); // set y to be the input circle's center Y coordinate
		double r = circle.getRadius(); // set r to be the input circle's radius
		
		// if the distance between the centers of the circles is less than the radius of the first minus the second, return true.
		// IF √(〖(x1-x2)〗^2+〖(y1-y2)〗^2 ) < (radius1 - radius2) THEN return true
		if (Math.sqrt(Math.pow((centerX - x) , 2) + Math.pow((centerY - y) , 2)) < (radius - r))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	boolean circleOverlapsCircle(Circle circle) // return true if the specified circle lies completely inside of this circle
	{
		double x = circle.getCenterX(); // set x to be the input circle's center X coordinate
		double y = circle.getCenterY(); // set y to be the input circle's center Y coordinate
		double r = circle.getRadius(); // set r to be the input circle's radius
		
		
		// if the distance between the centers of the circles is less than or equal to the radius of the first plus the second, return true.
		// IF √(〖(x1-x2)〗^2+〖(y1-y2)〗^2 ) <= (radius1 + radius2) THEN return true
		if (Math.sqrt(Math.pow((centerX - x) , 2) + Math.pow((centerY - y) , 2)) <= (radius + r))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString() // return a string containing circle information
	{
		return ("\nThis circle's center X coordinate lies at: " + centerX +
				"\nThis circle's center Y coordinate lies at: " + centerY +
				"\nThis circle's radius is: " + radius + "\n");
	}
	
	double distance (double x1, double y1, double x2, double y2) // return the distance between two given points
	{
		return Math.sqrt(Math.pow((x1 - x2) , 2) + Math.pow((y1 - y2) , 2)); // return √(〖(x1-x2)〗^2+〖(y1-y2)〗^2 )
	}
}
