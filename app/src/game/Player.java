package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

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
    private Image playerImage;
    private CollisionMaskGenerator collisionMaskGenerator;

    /**
     * Constructor to initialize the player's position.
     */
    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.width = 36;
        this.height = 36;
        this.speedX = 0;
        this.speedY = 0;

        // Load the image and create the collision mask
        try {
            String imagePath = "/images/fish.png";
            var resource = getClass().getResourceAsStream(imagePath);
            if (resource != null) {
                playerImage = ImageIO.read(resource);
                collisionMaskGenerator = new CollisionMaskGenerator(imagePath);
            } else {
                System.out.println("Player sprite image not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading player sprite image.");
        }
    }

    /**
     * Draw the player on the screen.
     * 
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics2D g) {
        if (playerImage != null) {
            g.drawImage(playerImage, x, y, width, height, null);
        }
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
     * 
     * @param speedX The speed in the x direction.
     * @param speedY The speed in the y direction.
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

    public int getWidth() {
        return collisionMaskGenerator.getWidth();
    }

    public int getHeight() {
        return collisionMaskGenerator.getHeight();
    }

    /**
     * Checks if the player can move to the next position by checking for
     * collisions.
     *
     * @param nextX            The next x-coordinate.
     * @param nextY            The next y-coordinate.
     * @param mapCollisionMask The collision mask of the map.
     * @return True if the player can move; false otherwise.
     */
    public boolean canMove(int nextX, int nextY, CollisionMaskGenerator mapCollisionMask) {
        boolean[][] playerMask = collisionMaskGenerator.getCollisionMask();
        boolean[][] mapMask = mapCollisionMask.getCollisionMask();

        int playerWidth = getWidth();
        int playerHeight = getHeight();

        for (int px = 0; px < playerWidth; px++) {
            for (int py = 0; py < playerHeight; py++) {
                if (playerMask[px][py]) {
                    int mapX = nextX + px;
                    int mapY = nextY + py;

                    // Check bounds
                    if (mapX < 0 || mapY < 0 || mapX >= mapCollisionMask.getWidth()
                            || mapY >= mapCollisionMask.getHeight()) {
                        return false; // Collision with boundary
                    }

                    if (mapMask[mapX][mapY]) {
                        return false; // Collision detected
                    }
                }
            }
        }
        return true; // No collision
    }
}
