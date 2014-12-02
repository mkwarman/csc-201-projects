/**
 * Circle object tester.
 * 
 * Matt Warman 
 * 1.1
 */

public class TestCircle {
	
	public static void main(String args[])
	{
		Circle c1 = new Circle(); // create circle with default characteristics (center at (0,0) and radius 1)
		System.out.print("Circle 1 (\"c1\"):" + c1.toString() + "\n"); // Print information for circle c1
		
		Circle c2 = new Circle(1.5, 0, 3); // create circle with center at (1.5, 0) and radius 3
		System.out.print("Circle 2 (\"c2\"):" + c2.toString() + "\n"); // Print information for circle c2
		
		Circle c3 = new Circle(2, 2.5, 3.5); // create circle with center at (2, 2.5) and radius 3.5
		System.out.print("Circle 3 (\"c3\"):" + c3.toString() + "\n"); // Print information for circle c3
		
		Circle c4 = new Circle(3, 3, 1); // create circle with center (3, 3) and radius 1 
		System.out.print("Circle 4 (\"c4\"):" + c4.toString() + "\n"); // Print information for circle c4
		
		System.out.print("\n"); // for the sake a readability)
		
		pointInside(c2, 2.0, 1.5, "c2"); // determine whether the point (2, 1.5) lies within the circle c2
		pointInside(c1, 1.7, 2.3, "c1"); // determine whether the point (1.7, 2.3) lies within the circle c1
		circleContains(c2, c1, "c2", "c1"); // determine whether the circle c2 contains the circle c1
		circleContains(c3, c2, "c3", "c2"); // determine whether the circle c3 contains the circle c2
		circleOverlap(c1, c2, "c1", "c2"); // determine whether circle 1 and circle 2 overlap
		circleOverlap(c1, c4, "c1", "c4"); // determine whether circle 1 and circle 4 overlap
	}
	
	public static void pointInside(Circle circle, double x, double y, String name) // output whether or not the given point is with the given circle
	{
		System.out.print("The point (" + x + ", " + y + ") ");
		if (circle.pointInsideCircle(x, y))
		{
			System.out.print("lies "); // if the point lies within the circle
		}
		else 
		{
			System.out.print("does not lie "); // if the point does NOT lie within the circle
		}
		System.out.print("within circle \"" + name + "\".\n"); 
	}
	
	public static void circleContains(Circle circle1, Circle circle2, String name1, String name2) // output whether or not the given circle2 lies within the given circle2
	{
		System.out.print("The circle \"" + name1 + "\"");
		if (circle1.circleInsideCircle(circle2)) // 
		{
			System.out.print(" contains "); // if circle1 contains circle 2
		}
		else
		{
			System.out.print(" does not contain "); // if circle1 does NOT contain circle 2
		}
		System.out.print("circle \"" + name2 + "\".\n");
	}
	
	public static void circleOverlap(Circle circle1, Circle circle2, String name1, String name2) // output whether or not the given circle1 and the given circle2 overlap
	{
		System.out.print("The circles \"" + name1 + "\" and \"" + name2 + "\" ");
		if (!circle1.circleOverlapsCircle(circle2))
		{
			System.out.print("do not "); // if the circles do NOT overlap
		}
		System.out.print("overlap.\n");
	}
}
