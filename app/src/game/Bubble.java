package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * The Bubble class represents a bubble that can move upwards on the screen.
 * It has properties such as position, radius, speed, and color.
 */
public class Bubble {
    private int x;
    private int y;
    private int radius;
    private int speedY;
    private Color color;
    private int screenHeight;

    /**
     * Constructs a new Bubble object with random position, radius, speed, and color.
     *
     * @param screenWidth  the width of the screen
     * @param screenHeight the height of the screen
     */
    public Bubble(int screenWidth, int screenHeight) {
        Random rand = new Random();
        this.x = rand.nextInt(screenWidth);
        this.y = screenHeight + rand.nextInt(100); // Start below the screen
        this.radius = rand.nextInt(10) + 5; // Radius between 5 and 15
        this.speedY = rand.nextInt(3) + 1; // Speed between 1 and 3
        this.color = new Color(255, 255, 255, 150); // Semi-transparent white
        this.screenHeight = screenHeight;
    }

    /**
     * Updates the position of the bubble by moving it upwards.
     * If the bubble moves off the top of the screen, it is reset to the bottom.
     */
    public void update() {
        y -= speedY;
        if (y + radius * 2 < 0) {
            reset();
        }
    }

    /**
     * Draws the bubble on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(x, y, radius * 2, radius * 2);
    }

    private void reset() {
        Random rand = new Random();
        this.x = rand.nextInt(screenHeight);
        this.y = screenHeight + rand.nextInt(100);
        this.radius = rand.nextInt(10) + 5;
        this.speedY = rand.nextInt(3) + 1;
    }
}
