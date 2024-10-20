package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * Obstacle class represents the obstacles in the game.
 */
public class Obstacle {

    private int x;
    private int y;
    private int width;
    private int height;

    /***
     * Constructor to initialize the obstacle's position and size.
     */
    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /***
     * Draw the obstacle on the screen.
     * 
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height); // Draw the obstacle as a gray rectangle
    }

    /***
     * Get the hitbox of the obstacle.
     * 
     * @return A Rectangle representing the obstacle's hitbox.
     */
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
