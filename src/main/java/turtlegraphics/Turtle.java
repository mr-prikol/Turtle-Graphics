package turtlegraphics;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * A turtle is a cursor that is moved on the {@link TurtleScreen}
 * while it draws. The turtle can be moved in various directions, and
 * its movements can be controlled by the programmer. While the turtle
 * is moving, it is drawing on the screen. The {@code Turtle} class
 * provides methods for controlling the movement of the turtle,
 * turning the turtle left and right, lifting and lowering the
 * {@link Pen}, and changing the pen's color and width. To create a
 * turtle, you need to create an instance of this class and associate
 * it with a {@link TurtleScreen}. Several turtles can be in the same
 * {@code TurtleScreen}, but not vice versa. The turtle uses
 * coordinate system with origin (0, 0) in the middle of the
 * {@code TurtleScreen}. The X axis directed right, the Y axis
 * directed up.
 *
 * @since 1.0
 * @author MrPrikol
 * @see TurtleScreen
 */
public final class Turtle implements Cloneable {

	/**
	 * The window in which the turtle is drawing. The turtle is associated
	 * with the screen when it is being constructed and it is impossible
	 * to change the screen after constructing the turtle.
	 */
	private final TurtleScreen screen;

	/**
	 * The X coordinate of this {@code Turtle} on it's screen.
	 * 
	 * @since 1.0
	 */
	private double x;

	/**
	 * The Y coordinate of this {@code Turtle} on it's screen.
	 * 
	 * @since 1.0
	 */
	private double y;

	/**
	 * The angle between turtle's orientation and the positive direction
	 * of y axis. The angle is measured in degrees. When turning clockwise
	 * the angle is being increased.
	 * 
	 * @since 1.0
	 */
	private double angle;

	/**
	 * The pen that the turtle is holding to draw. When picked up the
	 * turtle does not draw. It also holds the color and width of the
	 * turtle's paintings.
	 * 
	 * @since 1.0
	 */
	private Pen pen;

	/**
	 * Constructs new turtle in the center of the {@code screen}. New
	 * turtle's coordinates are (0, 0). The turtle is facing forward, to
	 * the positive direction of Y axis. The pen is put down, it's color
	 * is black, width is 1.
	 * 
	 * @param screen - {@link TurtleWindow} in which new turtle will be
	 *               constructed
	 * @throws NullPointerException if the {@code screen} is null.
	 * @since 1.0
	 */
	public Turtle(TurtleScreen screen) {
		if (screen == null)
			throw new NullPointerException("screen must not be null");

		this.screen = screen;
		this.x = 0.0;
		this.y = 0.0;
		this.angle = 0.0;
		this.pen = new Pen();
	}

	/**
	 * Constructs new {@link TurtleWindow} and puts new turtle in it.
	 * 
	 * @since 1.0
	 */
	public Turtle() {
		this(new TurtleScreen());
	}

	/**
	 * @return current X coordinate of this turtle on it's screen.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * @return current Y coordinate of this turtle on it's screen.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Returns current direction of the turtle - the angle between the
	 * turtle's orientation and positive direction of the Y axis.
	 * 
	 * @return angle in degrees in the range 0 through 360 inclusive.
	 */
	public double getAngle() {
		return this.angle;
	}

	// Next methods return reference to current turtle instance to allow
	// chaining methods.

	/**
	 * Turns the turtle on {@code angle} degrees clockwise.
	 * 
	 * @param angle - in degrees
	 * @return reference to this object.
	 */
	public Turtle right(double angle) {
		this.angle += angle;
		while (this.angle < 0.0)
			this.angle += 360.0;
		this.angle %= 360.0;

		return this;
	}

	/**
	 * Turns the turtle on {@code angle} degrees counterclockwise.
	 * 
	 * @param angle - in degrees
	 * @return reference to this object.
	 */
	public Turtle left(double angle) {
		this.angle -= angle;
		while (this.angle < 0.0)
			this.angle += 360.0;
		this.angle %= 360.0;
		return this;
	}

	/**
	 * Moves the turtle on {@code distance} pixels forward. If the
	 * turtle's pen is down, it will draw a line on its path.
	 * 
	 * @param distance
	 * @return reference to this object.
	 */
	public Turtle forward(double distance) {

		// Calculating new coordinates of the turtle considering it's
		// direction.
		double newX = (this.x
				+ distance * Math.cos(TurtleScreen.convertDegrees(this.angle)));
		double newY = (this.y
				+ distance * Math.sin(TurtleScreen.convertDegrees(this.angle)));

		// If turtle's pen is down, it must draw the line on it's path.
		if (this.pen.isDown())
			this.screen.drawLine(this.x, this.y, newX, newY,
					this.pen.getColor());

		this.x = newX;
		this.y = newY;

		return this;
	}

	/**
	 * Moves the turtle to the specified point. If the turtle's pen is
	 * down, it will draw a line on its path.
	 * 
	 * @param x - X coordinate of the point of destination
	 * @param y - Y coordinate of the point of destination
	 * @return reference to this object.
	 */
	public Turtle goTo(double x, double y) {

		double newX = x;
		double newY = y;

		// If turtle's pen is down, it must draw the line on it's path.
		if (this.pen.isDown())
			this.screen.drawLine(this.x, this.y, newX, newY,
					this.pen.getColor());

		this.x = newX;
		this.y = newY;

		return this;
	}

	/**
	 * Moves the turtle to the specified point. If the turtle's pen is
	 * down, it will draw a line on its path.
	 * 
	 * @param destination
	 * @return reference to this object.
	 */
	public Turtle goTo(Point2D destination) {
		return goTo(destination.getX(), destination.getY());
	}

	/**
	 * Picks up the turtle's pen. Disables drawing.
	 * 
	 * @return reference to this object.
	 */
	public Turtle penUp() {
		this.pen.setDown(false);
		return this;
	}

	/**
	 * Puts down the turtle's pen. Enables drawing.
	 * 
	 * @return reference to this object.
	 */
	public Turtle penDown() {
		this.pen.setDown(true);
		return this;
	}

	/**
	 * Sets new color for turtle's pen.
	 * 
	 * @param c - new color of the pen
	 * @return reference to this object.
	 */
	public Turtle setPenColor(Color c) {
		this.pen.setColor(c);
		return this;
	}

	/**
	 * Draws a dot on turtle's current location.
	 * 
	 * @param diameter
	 * @param color
	 * @return reference to this object.
	 */
	public Turtle dot(double diameter, Color c) {
		this.screen.drawDot(x, y, diameter, c);
		return this;
	}

	/**
	 * Draws a dot on turtle's current location. Uses the pen's color for
	 * the dot's color.
	 * 
	 * @param diameter
	 * @return reference to this object.
	 */
	public Turtle dot(double diameter) {
		return dot(diameter, this.pen.getColor());
	}

	/**
	 * Draws a dot on turtle's current location. The dot's diameter is 4
	 * pixels.
	 * 
	 * @param color
	 * @return reference to this object.
	 */
	public Turtle dot(Color color) {
		return dot(DEFAULT_DOT_DIAMETER, color);
	}

	/**
	 * Draws a dot on turtle's current location. The dot's diameter is 4
	 * pixels. The color is the same as pen's color.
	 * 
	 * @return reference to this object.
	 */
	public Turtle dot() {
		return dot(DEFAULT_DOT_DIAMETER);
	}

	private static final double DEFAULT_DOT_DIAMETER = 4.0;

}
