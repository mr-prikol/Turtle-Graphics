package turtlegraphics;

import java.awt.Color;

/**
 * This class represents the 2D space in which the {@link Turtle} is
 * moving and drawing. One screen can keep several turtles
 * simultaneously.
 * 
 * @since 1.0
 * @author MrPrikol
 * @see Turtle
 */
public class TurtleScreen {
	
	/**
	 * @since 1.0
	 */
	private static final int DEFAULT_WIDTH = 600;

	/**
	 * @since 1.0
	 */
	private static final int DEFAULT_HEIGHT = 600;

	/**
	 * 
	 * @since 1.0
	 */
	private final TurtleWindow window;

	/**
	 * @since 1.0
	 */
	private final int width;

	/**
	 * @since 1.0
	 */
	private final int height;

	/**
	 * Constructs new {@code TurtleScreen} with specified size and
	 * background color.
	 * 
	 * @param width
	 * @param height
	 * @param background
	 * @since 1.0
	 */
	public TurtleScreen(int width, int height, Color background) {
		this.width = width;
		this.height = height;
		this.window = new TurtleWindow(width, height, background);
	}

	/**
	 * Constructs new {@code TurtleScreen} with specified size and white
	 * background.
	 * 
	 * @param width
	 * @param height
	 * @since 1.0
	 */
	public TurtleScreen(int width, int height) {
		this(width, height, Color.WHITE);
	}

	/**
	 * Constructs new {@code TurtleScreen} with default size (600 x 600)
	 * and specified background color.
	 * 
	 * @param background
	 * @since 1.0
	 */
	public TurtleScreen(Color background) {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, background);
	}

	/**
	 * Constructs new {@code TurtleScreen} with default size (600 x 600)
	 * and white background.
	 * 
	 * @since 1.0
	 */
	public TurtleScreen() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT, Color.WHITE);
	}

	/**
	 * Draws the line on the screen from (x1, y1) point to (x2, y2) point
	 * with specified color of the line.
	 * 
	 * @param x1 - X coordinate of start point
	 * @param y1 - Y coordinate of start point
	 * @param x2 - X coordinate of end point
	 * @param y2 - Y coordinate of end point
	 * @param c  - color of the line
	 * @since 1.0
	 */
	void drawLine(double x1, double y1, double x2, double y2, Color c) {
		// Converting coordinates from Turtle's coordinate system
		// to TurtleScreen's coordinate system
		x1 = convertXCoordinate(x1);
		y1 = convertYCoordinate(y1);
		x2 = convertXCoordinate(x2);
		y2 = convertYCoordinate(y2);

		this.window.drawLine(x1, y1, x2, y2, c);
	}

	/**
	 * Draws a dot on the screen with the center in (x, y), the specified
	 * diameter and with specified color.
	 * 
	 * @param x        - X coordinate of the center of the dot
	 * @param y        - Y coordinate of the center of the dot
	 * @param diameter - diameter of the dot
	 * @param c        - color of the dot
	 * @since 1.0
	 */
	void drawDot(double x, double y, double diameter, Color c) {
		// Subtracting radius from X coordinate and adding it to
		// Y coordinate because TurtleWindow.drawDot requires
		// coordinates of the upper-left corner of the framing rectangle
		x = convertXCoordinate(x - diameter / 2.0);
		y = convertYCoordinate(y + diameter / 2.0);

		this.window.drawDot(x, y, diameter, c);
	}

	/**
	 * The {@link Turtle} uses system coordinate with (0, 0) in the center
	 * of the screen, X axis directing right, Y axis directing up.
	 * The {@link TurtleWindow} uses system coordinate with (0, 0) in
	 * the left top corner of the screen,
	 * X axis directing right, Y axis directing down.
	 * Converting the X coordinate from {@code Turtle}'s coordinate
	 * system to the {@code TurtleWindow}'s.
	 * 
	 * @param x - X coordinate of turtle on coordinate system with (0, 0)
	 *          in the center.
	 * @return X coordinate of the turtle on coordinate system with (0, 0)
	 *         in the left top corner.
	 * @since 1.0
	 */
	private double convertXCoordinate(double x) {
		// Adding half of the screen's width because it is the difference
		// between X coordinates of the coordinate systems' origins
		return x + this.width / 2.0;
	}

	/**
	 * The {@link Turtle} uses system coordinate with (0, 0) in the center
	 * of the screen, X axis directing right, Y axis directing up.
	 * The {@link TurtleWindow} uses system coordinate with (0, 0) in
	 * the left top corner of the screen,
	 * X axis directing right, Y axis directing down.
	 * Converting the Y coordinate from {@code Turtle}'s coordinate sytem
	 * to the {@code TurtleWindow}'s.
	 * 
	 * @param y - Y coordinate of turtle on coordinate system with (0, 0)
	 *          in the center.
	 * @return Y coordinate of the turtle on coordinate system with (0, 0)
	 *         in the left top corner.
	 * @since 1.0
	 */
	private double convertYCoordinate(double y) {
		// Multiplying y by -1 because axes' directions are opposite
		// Adding half of the screen's height because it is the difference
		// between Y coordinates of the coordinate systems' origins
		return -y + this.height / 2.0;
	}

	/**
	 * The {@link Turtle} measures the angle between the turtle's
	 * orientation and positive direction of the Y axis. Uses degrees.
	 * The {@link TurtleWindow} measures the angle between the turtle's
	 * orientation and positive direction of the X axis. Uses radians.
	 * Converting the angle from {@code Turtle}'s coordinate system
	 * to the {@code TurtleWindow}'s.
	 * 
	 * @param angle - in degrees, between turtle and positive direction of
	 *              Y axis.
	 * @return angle in radians between turtle and positive direction of X
	 *         axis.
	 * @since 1.0
	 */
	static double convertDegrees(double angle) {
		return Math.toRadians(90 - angle);
	}

}
