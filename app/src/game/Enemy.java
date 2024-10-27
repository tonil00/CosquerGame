package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The Enemy class represents an enemy character in the game.
 * It handles the enemy's position, movement, and rendering.
 */
public class Enemy {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speedX;
    private Image enemyImage;

    /**
     * Constructor to initialize the enemy's position and movement.
     *
     * @param startX The starting x-coordinate.
     * @param startY The starting y-coordinate.
     * @param speedX The horizontal speed of the enemy.
     */
    public Enemy(int startX, int startY, int speedX) {
        this.x = startX;
        this.y = startY;
        this.speedX = speedX;
        this.width = 20; // Adjust as needed
        this.height = 20; // Adjust as needed

        // Load the enemy image
        try {
            var resource = getClass().getResourceAsStream("/images/enemy.png");
            if (resource != null) {
                enemyImage = ImageIO.read(resource);
            } else {
                System.out.println("Enemy sprite image not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading enemy sprite image.");
        }
    }

    /**
     * Copy constructor to create a new enemy based on an existing one.
     *
     * @param enemy The enemy to copy.
     */
    public Enemy(Enemy enemy) {
        this.x = enemy.x;
        this.y = enemy.y;
        this.width = enemy.width;
        this.height = enemy.height;
        this.speedX = enemy.speedX;
        this.enemyImage = enemy.enemyImage;
    }

    /**
     * Update the enemy's position.
     */
    public void update() {
        x += speedX;

        // Example movement: Reverse direction at specific x-coordinates
        if (x <= 100 || x + width >= 700) {
            speedX = -speedX; // Reverse direction
        }
    }

    /**
     * Draw the enemy on the screen.
     *
     * @param g The Graphics2D object used for drawing.
     */
    public void draw(Graphics2D g) {
        if (enemyImage != null) {
            g.drawImage(enemyImage, x, y, width, height, null);
        }
    }

    /**
     * Get the bounding box of the enemy for collision detection.
     *
     * @return The bounding rectangle of the enemy.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    // Getters for position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
