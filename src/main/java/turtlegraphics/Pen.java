package turtlegraphics;

import java.awt.Color;

/**
 * This class represents a pen which has color and width. It is used
 * by {@link Turtle} to draw. The pen can be put down to enable
 * drawing, or be picked up to disable drawing using {@code isDown}
 * field.
 * 
 * @since 1.0
 * @author MrPrikol
 */
final class Pen {

	/**
	 * This field indicates whether turtle will paint something or no.
	 * <br>
	 * {@code true} - the turtle paints. <br>
	 * {@code false} - the turtle does not paint.
	 * 
	 * @since 1.0
	 */
	private boolean isDown;

	/**
	 * The field defines in which color the turtle will draw.
	 * 
	 * @since 1.0
	 */
	private Color color;

	/**
	 * This field specifies the width of the lines that the turtle draws.
	 * 
	 * @since 1.0
	 */
	private double width;

	/**
	 * Constructs and initializes the Pen. It is set down, the color is
	 * black, the width is 1.
	 * 
	 * @since 1.0
	 */
	public Pen() {
		this.isDown = true;
		this.color = Color.BLACK;
		this.width = 1.0;
	}

	/**
	 * @return {@code true} if the pen is put down. {@code false}
	 *         otherwise.
	 */
	public boolean isDown() {
		return this.isDown;
	}

	/**
	 * Puts the pen down or picks it up.
	 * 
	 * @param isDown - {@code true} for putting the pen down.
	 *               {@code false} for picking it up
	 */
	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	/**
	 * @return {@code color} of the pen.
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Sets new color for the pen.
	 * 
	 * @param color - new {@code Color} for the pen
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return {@code width} of the pen.
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * Sets new width for the pen.
	 * 
	 * @param width - must be greater then 0
	 */
	public void setWidth(double width) {
		this.width = width;
	}

}
