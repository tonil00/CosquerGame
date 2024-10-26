package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CollisionMaskGenerator {
    private boolean[][] collisionMask;
    private int width;
    private int height;

    public CollisionMaskGenerator(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            this.width = image.getWidth();
            this.height = image.getHeight();
            collisionMask = new boolean[width][height];

            // Generate the collision mask
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int pixel = image.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;

                    // If pixel is not transparent, mark as true (collidable)
                    collisionMask[x][y] = alpha != 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading image for collision mask.");
        }
    }

    public boolean[][] getCollisionMask() {
        return collisionMask;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
