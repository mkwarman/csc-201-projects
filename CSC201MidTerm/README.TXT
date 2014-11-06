PROJECT TITLE: Area Calculation Program (CSC 201 Midterm)
VERSION: 1.5
AUTHORS: Matt Warman
USER INSTRUCTIONS: Simply follow the program prompts


CHANGELOG:

Version 1.5
    Further improved output formatting
    Added lines printed upon user selection to exit

Version 1.4
    Made calcRectangleArea, calcTriangleArea, and calcCircleArea more effecient by
    eliminating an 'area' variable and instead reducing their computation code to one
    line - the same line that instructs them to return a variable.

Version 1.3
    Added all remaining comments and explanations/clarifications.
    Further formatted decimal output
    fixed a bug that could potentially cause an infinite "Please try again" loop

Version 1.2:
    Formatted decimal output, improved output clarity

Version 1.1
    Implemented "Press enter to continue"

Version 1.0
    Functioning program

Version Alpha/Pre-Alpha:
    Testing and development only


ANALYSIS:
    This program needed to be able to take in user input, determine what to do with it,
    calculate answers, and then display results. It also needed to include three methods
    of a given structure, and use Java's Math.PI in order to complete the aforementioned
    calculations
    
DESIGN:
    While building this program, I started to see that I would end up being forced to use
    a lot of loops and if statements in order to complete the given requirements as well as
    those which I wanted the program to conform to as well. I considered a few means of
    circumventing these limitations in order to develop clean and efficient code, and was
    eventually able to come up with a few ways to make that happen. One of these methods
    involved the development of a method (ha!) whose sole purpose was to facilitate an
    easy way to obtain and sanitize user input. I developed the method getDoubleInput
    which takes in strings for both the name of the shape as well as the name of the
    attribute of the shape to be input. It then uses the arguments passed into it to print
    a request to the user, asking for them to input a value. Then, conveniently, the method
    completes all the work of making sure that the input values are of the correct type and
    then returns that value to be passed into the appropriate variable in the main function.
    This makes getting clean user input as a double very easy and very efficient.
    
TESTING: 
    I tested my program by inputting invalid data types in multiple locations to make sure
    that the program would catch errors effectively, and give the user chances to try again.
    I also tested the program by making sure that the output answers were correct by manually
    calculating what they should be, then comparing that to those output by the program.