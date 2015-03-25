/**
 * Use this given program to test the GeometricObject and Triangle classes
 * 
 * 
 * Comments added by Matt Warman
 */

public class DemoTriangle 
{
	public static void main(String[] args) {
		Triangle triangle = new Triangle(1, 1.5, 1); // create a new triangle with sides of length 1, 1.5, and 1.
		System.out.println(triangle); // print information about the triangle

		triangle.setColor("yellow"); // change the triangle's color yellow
		triangle.setFilled(true); // set the triangle as filled

		System.out.println(triangle); // print information about the triangle
	}
}
