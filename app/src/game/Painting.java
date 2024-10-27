package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Painting {
    private int x, y;
    private Image paintingImage;
    private boolean collected;

    public Painting(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.collected = false;
        loadImage(imagePath);
    }

    private void loadImage(String imagePath) {
        try {
            paintingImage = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading painting image: " + imagePath);
        }
    }

    public void draw(Graphics2D g) {
        if (!collected && paintingImage != null) {
            g.drawImage(paintingImage, x, y, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, paintingImage.getWidth(null), paintingImage.getHeight(null));
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;
    }

    public void reset() {
        collected = false;
    }
}
