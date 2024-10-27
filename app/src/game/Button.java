package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Represents a clickable button in the game.
 */
public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle bounds;
    private Image buttonImage;

    /**
     * Constructs a new Button with the specified position, size, and text.
     * Loads an image to use as the button background.
     *
     * @param x         the x-coordinate of the button
     * @param y         the y-coordinate of the button
     * @param width     the width of the button
     * @param height    the height of the button
     * @param imagePath the path to the button image
     */
    public Button(int x, int y, int width, int height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(x, y, width, height);

        // Load button image
        try {
            buttonImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading button image.");
        }
    }

    /**
     * Draws the button on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    public void draw(Graphics2D g2d) {
        // Draw button image or rectangle if image is not available
        g2d.drawImage(buttonImage, x, y, width, height, null);

        // Draw text
        g2d.setColor(Color.BLACK);
    }

    public boolean isClicked(int mouseX, int mouseY) {
        return bounds.contains(mouseX, mouseY);
    }

    /**
     * Sets the position of the button.
     *
     * @param x the new x-coordinate of the button
     * @param y the new y-coordinate of the button
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.setLocation(x, y); // Update bounds with new position
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
