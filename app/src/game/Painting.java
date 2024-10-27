package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Painting class represents a painting object in the game.
 * It includes properties for the painting's position, image, and collected
 * status.
 * Methods are provided to load the image, draw the painting, get its bounds,
 * and manage its collected state.
 */
public class Painting {
    private int x;
    private int y;
    private Image paintingImage;
    private boolean collected;

    /**
     * Constructs a new Painting object with the specified position and image path.
     *
     * @param x         the x-coordinate of the painting
     * @param y         the y-coordinate of the painting
     * @param imagePath the path to the image file for the painting
     */
    public Painting(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.collected = false;
        loadImage(imagePath);
    }

    private void loadImage(String imagePath) {
        try {
            paintingImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading painting image: " + imagePath);
        }
    }

    /**
     * Draws the painting on the screen if it has not been collected.
     *
     * @param g the Graphics2D object used for drawing the painting
     */
    public void draw(Graphics2D g) {
        if (!collected && paintingImage != null) {
            g.drawImage(paintingImage, x, y, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, paintingImage.getWidth(null), paintingImage.getHeight(null));
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;
    }

    public void reset() {
        collected = false;
    }
}
