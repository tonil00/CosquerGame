package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

/***
 * Player class represents the player (fish) in the game.
 */
public class Player {

    private int x;
    private int y;
    private int width;
    private int height;
    private double speedX;
    private double speedY;
    private double acceleration = 0.2;
    private double deceleration = 0.1;
    private double maxSpeed = 3.0;

    /***
     * Constructor to initialize the player's position.
     */
    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.width = 50;
        this.height = 30;
        this.speedX = 0;
        this.speedY = 0;
    }

    /***
     * Draw the player on the screen.
     * 
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height); // Draw fish as a yellow oval
    }

    /***
     * Update the player's position, apply acceleration or deceleration, and check for collisions.
     */
    public void update(boolean movingUp, boolean movingDown, boolean movingLeft, boolean movingRight, List<Obstacle> obstacles) {
        // Adjust speed
        if (movingLeft) {
            speedX = Math.max(speedX - acceleration, -maxSpeed);
        } else if (movingRight) {
            speedX = Math.min(speedX + acceleration, maxSpeed);
        } else {
            speedX = (speedX > 0) ? Math.max(speedX - deceleration, 0) : Math.min(speedX + deceleration, 0);
        }

        if (movingUp) {
            speedY = Math.max(speedY - acceleration, -maxSpeed);
        } else if (movingDown) {
            speedY = Math.min(speedY + acceleration, maxSpeed);
        } else {
            speedY = (speedY > 0) ? Math.max(speedY - deceleration, 0) : Math.min(speedY + deceleration, 0);
        }

        // Calculate potential new position
        int nextX = x + (int) speedX;
        int nextY = y + (int) speedY;

        // Check for collisions
        if (!isColliding(nextX, nextY, obstacles)) {
            // Move the player if no collision detected
            x = nextX;
            y = nextY;
        } else {
            // Stop movement if a collision occurs
            speedX = 0;
            speedY = 0;
        }
    }

    /***
     * Check if the player would collide with any obstacles at the given position.
     * 
     * @param nextX The player's next x position.
     * @param nextY The player's next y position.
     * @param obstacles List of obstacles to check collision against.
     * @return True if a collision would occur, false otherwise.
     */
    private boolean isColliding(int nextX, int nextY, List<Obstacle> obstacles) {
        Rectangle playerHitbox = new Rectangle(nextX, nextY, width, height);
        for (Obstacle obstacle : obstacles) {
            if (playerHitbox.intersects(obstacle.getHitbox())) {
                return true;
            }
        }
        return false;
    }

    /***
     * Stop the player from moving immediately.
     */
    public void stopMoving() {
        this.speedX = 0;
        this.speedY = 0;
    }

    /***
     * Get the x-coordinate of the player.
     * 
     * @return The x-coordinate of the player.
     */
    public int getX() {
        return x;
    }

    /***
     * Get the y-coordinate of the player.
     * 
     * @return The y-coordinate of the player.
     */
    public int getY() {
        return y;
    }
}
