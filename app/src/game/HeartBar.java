package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HeartBar {
    private int maxHearts;
    private int currentHearts;
    private Image fullHeartImage;
    private Image emptyHeartImage;
    private int heartWidth;
    private int heartHeight;

    /**
     * Constructor to initialize the heart bar.
     *
     * @param maxHearts The maximum number of hearts (lives).
     */
    public HeartBar(int maxHearts) {
        this.maxHearts = maxHearts;
        this.currentHearts = maxHearts;
        this.heartWidth = 32;  // Adjust based on your image size
        this.heartHeight = 32; // Adjust based on your image size

        // Load heart images
        try {
            fullHeartImage = ImageIO.read(getClass().getResourceAsStream("/images/full_heart.png"));
            emptyHeartImage = ImageIO.read(getClass().getResourceAsStream("/images/empty_heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading heart images.");
        }
    }

    /**
     * Decrease the current hearts by one.
     */
    public void loseHeart() {
        if (currentHearts > 0) {
            currentHearts--;
        }
    }

    /**
     * Increase the current hearts by one.
     */
    public void gainHeart() {
        if (currentHearts < maxHearts) {
            currentHearts++;
        }
    }

    /**
     * Draw the heart bar on the screen.
     *
     * @param g The Graphics2D object used for drawing.
     */
    public void draw(Graphics2D g) {
        int x = 10; // Starting x-coordinate
        int y = 10; // Starting y-coordinate

        for (int i = 0; i < maxHearts; i++) {
            Image heartImage = (i < currentHearts) ? fullHeartImage : emptyHeartImage;
            g.drawImage(heartImage, x + i * (heartWidth + 5), y, heartWidth, heartHeight, null);
        }
    }

    /**
     * Get the current number of hearts.
     *
     * @return The current hearts.
     */
    public int getCurrentHearts() {
        return currentHearts;
    }
}
