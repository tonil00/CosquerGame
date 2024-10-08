package game;

import java.awt.Color;
import java.awt.Graphics;

/***
 * Player class represents the player (fish) in the game.
 */
public class Player {

    private int x;
    private int y;
    private int width;
    private int height;
    private int speedX;
    private int speedY;

    /**
     * Constructor to initialize the player's position.
     */
    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.width = 50; // Width of the fish
        this.height = 30; // Height of the fish
        this.speedX = 0;
        this.speedY = 0;
    }

    /**
     * Draw the player on the screen.
     * 
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height); // Draw fish as a yellow oval
    }

    /**
     * Update the player's position based on speed.
     */
    public void update() {
        x += speedX;
        y += speedY;
    }

    /**
     * Set the speed of the player.
     */
    public void setSpeed(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    /**
     * Stop the player from moving.
     */
    public void stopMoving() {
        this.speedX = 0;
        this.speedY = 0;
    }

    /**
     * Get the x-coordinate of the player.
     * 
     * @return The x-coordinate of the player.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-coordinate of the player.
     * 
     * @return The y-coordinate of the player.
     */
    public int getY() {
        return y;
    }
}
