package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * Painting class represents the collectible paintings in the game.
 */
public class Painting {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean collected;

    /***
     * Constructor to initialize the painting's position and size.
     */
    public Painting(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 30; // Arbitrary width for the painting
        this.height = 30; // Arbitrary height for the painting
        this.collected = false;
    }

    /***
     * Draw the painting on the screen.
     * If collected, it is no longer drawn.
     * 
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g) {
        if (!collected) {
            g.setColor(Color.ORANGE); // Paintings are orange squares
            g.fillRect(x, y, width, height);
        }
    }

    /***
     * Get the hitbox of the painting.
     * 
     * @return A Rectangle representing the painting's hitbox.
     */
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }

    /***
     * Mark the painting as collected.
     */
    public void collect() {
        collected = true;
    }

    /***
     * Check if the painting has been collected.
     * 
     * @return True if the painting has been collected, false otherwise.
     */
    public boolean isCollected() {
        return collected;
    }
}
