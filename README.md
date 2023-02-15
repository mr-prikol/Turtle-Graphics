
# Turtle Graphics

Turtle Graphics is simple graphics library for Java. The project is inspired by Pyhton turtle library. This project is made in educational purposes

## Usage/Examples

Simple example: creating a square
```java
// Initializing the new turtle
Turtle turtle = new Turtle();
// Going forward and turning 90 degrees 4 times to make a square
for (int i = 0; i < 4; i++) {
        turtle.forward(100);
        turtle.right(90);
}
```
The statemants in the loop can also be overwritten in one statemant using chaining methods
```java
for (int i = 0; i < 4; i++)
    turtle.forward(100).right(90);
```
The output will be next:
![SquareExample](/img/square-example.png)

Let's take a look on more complicated example. The next script will create fractal called Sierpinski Triangle. The idea is to take a random point inside a triangle and choose random vertex of the triangle. A dot will be drawn in the middle between given point and the chosen vertex. Then repeat this assuming that new start location is the dot that we've drawn. \
Now let's implement it:
```java
// Creating vertexes of the triangle ABC
		final Point2D A = new Point2D.Double(-187, -150);
		final Point2D B = new Point2D.Double(0, 259);
		final Point2D C = new Point2D.Double(187, -150);

		// Initializing new screen with black background and adding new turtle to it
		final TurtleScreen screen = new TurtleScreen(Color.BLACK);
		final Turtle myTurtle = new Turtle(screen);
		
		// Moving the turtle to the first triangle vertex
		myTurtle
			.penUp()
			.goTo(A);
		
		// Drawing the triangle with white color
		myTurtle
			.setPenColor(Color.WHITE)
			.penDown()
			.goTo(B)
			.goTo(C)
			.goTo(A)
			.penUp();
		
		// Moving the turtle to random point inside triangle
		Point randomPointInsideTriangle = new Point(25, 18);
		myTurtle.goTo(randomPointInsideTriangle);
		
		// Creating the fractal
		// Using different color of dot depending on the vertex that we choose:
		// A - red, B - green, C - blue
		for (long i = 0L; i < 1_000_000L; i++) {
			
			// Choosing random triangle vertex
			switch ((int) (Math.random() * 3 + 1)) {
			case 1: // vertex A
				myTurtle
					.goTo((myTurtle.getX() + A.getX()) / 2.0, (myTurtle.getY() + A.getY()) / 2.0)
					.dot(1, Color.RED);
					break;
			case 2: // vertex B
				myTurtle
					.goTo((myTurtle.getX() + B.getX()) / 2.0, (myTurtle.getY() + B.getY()) / 2.0)
					.dot(1, Color.GREEN);
					break;
			case 3: // vertex C
				myTurtle
					.goTo((myTurtle.getX() + C.getX()) / 2.0, (myTurtle.getY() + C.getY()) / 2.0)
					.dot(1, Color.BLUE);
					break;
			}
		
		}
		
```
We get next output:
![FractalExample](/img/fractal-example.png)
