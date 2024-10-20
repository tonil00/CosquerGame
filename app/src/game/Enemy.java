package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * Enemy class represents a generic enemy in the game.
 */
public abstract class Enemy {

    protected int x;
    protected int y;
    protected final int width;
    protected final int height;

    /***
     * Constructor to initialize the enemy's position and size.
     */
    public Enemy(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /***
     * Draw the enemy on the screen.
     * 
     * @param g The Graphics object used for drawing.
     */
    public abstract void draw(Graphics g);

    /***
     * Update the enemy's behavior.
     */
    public abstract void update(Player player);

    /***
     * Get the hitbox of the enemy for collision detection.
     * 
     * @return A Rectangle representing the enemy's hitbox.
     */
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }

    /***
     * Getter for the enemy's X coordinate.
     * 
     * @return The enemy's X coordinate.
     */
    public int getX() {
        return x;
    }

    /***
     * Getter for the enemy's Y coordinate.
     * 
     * @return The enemy's Y coordinate.
     */
    public int getY() {
        return y;
    }

    /***
     * Getter for the enemy's width.
     * 
     * @return The enemy's width.
     */
    public int getWidth() {
        return width;
    }

    /***
     * Getter for the enemy's height.
     * 
     * @return The enemy's height.
     */
    public int getHeight() {
        return height;
    }
}
