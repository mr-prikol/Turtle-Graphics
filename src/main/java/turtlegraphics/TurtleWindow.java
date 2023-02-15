package turtlegraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class contains window with {@link Turtle}'s drawings.
 * Public class that connects {@code TurtleWindow} with the turtle
 * is {@link TurtleScreen}.
 * 
 * @since 1.0
 * @author MrPrikol
 * @see TurtleScreen
 */
@SuppressWarnings("serial")
final class TurtleWindow extends JPanel {

	/**
	 * Stores all drawings.
	 * @since 1.0
	 */
	private Image buffer;

	/**
	 * Constructs new screen for the {@link Turtle} objects with specified
	 * width, height and background color.
	 * 
	 * @param width      - must be greater then 0 and less or equal then
	 *                   width of user's monitor
	 * @param height     - must be greater then 0 and less or equal to
	 *                   height of user's monitor
	 * @param background - color of the background
	 * @since 1.0
	 */
	public TurtleWindow(int width, int height, Color background) {

		this.setBackground(background);
		this.setSize(width, height);

		// Creating a window
		JFrame frame = new JFrame("Turtle Graphics");

		// Getting an icon from resource folder
		ImageIcon icon = new ImageIcon(this.getClass().getClassLoader()
				.getResource("turtle_icon.png"));
		frame.setIconImage(icon.getImage());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Adding additional 17 pixels to frame size because of title bar
		frame.setSize(width, height + 17);

		frame.setResizable(false);

		// Placing the window in the middle of the user's screen
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);

		// Creating image filled with background color
		buffer = createImage(getWidth(), getHeight());
		Graphics2D g2D = (Graphics2D) buffer.getGraphics();
		g2D.setColor(background);
		g2D.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Draws a line on {@code buffer} image with start at (x1, y1) and end
	 * in (x2, y2) with specified color.
	 * 
	 * @param x1 - X coordinate of start point of the line
	 * @param y1 - Y coordinate of start point of the line
	 * @param x2 - X coordinate of end point of the line
	 * @param y2 - Y coordinate of end point of the line
	 * @param c  - color of the line
	 * @since 1.0
	 */
	void drawLine(double x1, double y1, double x2, double y2, Color c) {

		// Drawing the line on the buffer image
		Graphics2D g2D = (Graphics2D) this.buffer.getGraphics();
		Line2D line = new Line2D.Double(x1, y1, x2, y2);
		g2D.setColor(c);
		g2D.draw(line);

		// Updating the image in the window to force the line to appear
		// on the screen using JPanel's repaint() method
		super.repaint();
	}

	/**
	 * Draws a dot on {@code buffer} image with center in (x, y),
	 * specified diameter and color.
	 * 
	 * @param x        - X coordinate of dot center
	 * @param y        - Y coordinate of dot center
	 * @param diameter
	 * @param c
	 * @since 1.0
	 */
	void drawDot(double x, double y, double diameter, Color c) {

		// Drawing a dot on the buffer image
		Graphics2D g2D = (Graphics2D) this.buffer.getGraphics();
		Ellipse2D dot = new Ellipse2D.Double(x, y, diameter, diameter);
		g2D.setColor(c);
		g2D.fill(dot);

		// Updating the image in the window to force the dot to appear
		// on the screen using JPanel's repaint() method
		super.repaint();
	}

	// Updating the screen. Invoked automatically by Swing framework
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(buffer, 0, 0, this);
	}

}
