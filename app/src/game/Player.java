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
    private final int width;
    private final int height;
    private double speedX;
    private double speedY;
    private final double acceleration = 0.2;
    private final double deceleration = 0.1;
    private final double maxSpeed = 3.0;
    private int health;

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
        this.health = 3;
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
     * Update the player's position, apply acceleration or deceleration, and handle water currents.
     */
    public void update(boolean movingUp, boolean movingDown, boolean movingLeft, boolean movingRight, List<Obstacle> obstacles, List<WaterCurrent> currents, List<Enemy> enemies, List<Painting> paintings) {
        if (!isAlive()) return; // Skip updating if the player is dead

        // Apply normal movement
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

        // Check for collisions with obstacles
        if (!isColliding(nextX, nextY, obstacles)) {
            // Move the player if no collision detected
            x = nextX;
            y = nextY;
        }

        // Apply water current effect
        applyCurrentEffect(currents);

        // Handle enemies and paintings
        handleEnemiesAndPaintings(enemies, paintings);
    }

    /***
     * Check for collisions with obstacles.
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
     * Apply the effect of water currents on the player.
     */
    private void applyCurrentEffect(List<WaterCurrent> currents) {
        Rectangle playerHitbox = new Rectangle(x, y, width, height);
        for (WaterCurrent current : currents) {
            if (playerHitbox.intersects(current.getHitbox())) {
                speedX += current.getPushX();
                speedY += current.getPushY();
                return; // Exit once a current is applied
            }
        }
    }

    /***
     * Handle enemies and paintings collisions.
     */
    private void handleEnemiesAndPaintings(List<Enemy> enemies, List<Painting> paintings) {
        Rectangle playerHitbox = new Rectangle(x, y, width, height);
        for (Enemy enemy : enemies) {
            if (playerHitbox.intersects(enemy.getHitbox())) {
                takeDamage();
            }
        }

        for (Painting painting : paintings) {
            if (playerHitbox.intersects(painting.getHitbox()) && !painting.isCollected()) {
                painting.collect();
            }
        }
    }

    /***
     * Handle player taking damage from an enemy.
     */
    public void takeDamage() {
        health--;
        if (health <= 0) {
            // Player dies
            health = 0;
        }
    }

    /***
     * Get the hitbox of the player for collision detection.
     */
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }

    /***
     * Getter for the player's X coordinate.
     * 
     * @return The player's X coordinate.
     */
    public int getX() {
        return x;
    }

    /***
     * Getter for the player's Y coordinate.
     * 
     * @return The player's Y coordinate.
     */
    public int getY() {
        return y;
    }

    /***
     * Setter for the player's position.
     */
    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    /***
     * Getter for the player's health.
     * 
     * @return The player's health.
     */
    public int getHealth() {
        return health;
    }

    /***
     * Getter for the player's width.
     * 
     * @return The player's width.
     */
    public int getWidth() {
        return width;
    }

    /***
     * Getter for the player's height.
     * 
     * @return The player's height.
     */
    public int getHeight() {
        return height;
    }

    /***
     * Determine if the player is alive.
     */
    public boolean isAlive() {
        return health > 0;
    }
}
