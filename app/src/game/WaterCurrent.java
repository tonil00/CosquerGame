package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * WaterCurrent class represents an area where the player is pushed in a specific direction.
 */
public class WaterCurrent {

    private int x;
    private int y;
    private int width;
    private int height;
    private double pushX;
    private double pushY;

    /***
     * Constructor to initialize the current's position, size, and direction.
     */
    public WaterCurrent(int x, int y, int width, int height, double pushX, double pushY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.pushX = pushX;
        this.pushY = pushY;
    }

    /***
     * Draw the water current on the screen.
     * 
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g) {
        g.setColor(new Color(0, 150, 255, 100)); // Semi-transparent blue to represent the current
        g.fillRect(x, y, width, height);
    }

    /***
     * Get the hitbox of the water current.
     * 
     * @return A Rectangle representing the water current's area.
     */
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }

    /***
     * Get the x-direction push of the current.
     * 
     * @return The x-direction push of the current.
     */
    public double getPushX() {
        return pushX;
    }

    /***
     * Get the y-direction push of the current.
     * 
     * @return The y-direction push of the current.
     */
    public double getPushY() {
        return pushY;
    }
}
