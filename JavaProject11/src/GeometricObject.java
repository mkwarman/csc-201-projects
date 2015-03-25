/**
 * Should construct geometric objects
 * 
 * Matt Warman
 * 1.2
 */

public class GeometricObject {
	
	private String color; // hold the string containing the color of the object
	private Boolean filled; // hold the value that determines whether the object is filled or not
	private java.util.Date dateCreated; // hold the value showing the date that the object was created
	
	GeometricObject() // create an object with default parameters
	{
		filled = false; // the default value of an object will be not filled
		color = "null"; // the default color name of an object will be "null"
		dateCreated = new java.util.Date(); // store the date created
	}
	
	GeometricObject(String colorInput, Boolean filledInput) // create the object with given attributes
	{
		color = colorInput; // set the color to that which is given
		filled = filledInput; // set the filled status to that which is given
		dateCreated = new java.util.Date(); // store the date created
	}
	
	void setColor(String newColor) // change the color of the object
	{
		color = newColor;
	}
	String getColor() // read the color of the object
	{
		return color;
	}
	
	void setFilled(Boolean newFilled) // change whether or not the object is filled
	{
		filled = newFilled;
	}
	
	Boolean isFilled() // read whether or not the object is filled
	{
		return filled;
	}

	java.util.Date getDateCreated() // read the creation date of the object
	{
		return dateCreated;
	}
	
	public String toString() // output object information to a string
	{
		if (filled) // if the object is filled
		{
			return ("This object is filled\nThis object is of the color: " + color + "\nThis object was created on: " + dateCreated + "\n\n");

		}
		else // if the object is NOT filled
		{
			return ("This object is not filled\nThis object is of the color: " + color + "\nThis object was created on: " + dateCreated + "\n\n");
		}
	}
	
}
