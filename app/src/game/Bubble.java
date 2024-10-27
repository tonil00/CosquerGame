package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Bubble {
    private int x, y;
    private int radius;
    private int speedY;
    private Color color;
    private int screenHeight;

    public Bubble(int screenWidth, int screenHeight) {
        Random rand = new Random();
        this.x = rand.nextInt(screenWidth);
        this.y = screenHeight + rand.nextInt(100); // Start below the screen
        this.radius = rand.nextInt(10) + 5; // Radius between 5 and 15
        this.speedY = rand.nextInt(3) + 1; // Speed between 1 and 3
        this.color = new Color(255, 255, 255, 150); // Semi-transparent white
        this.screenHeight = screenHeight;
    }

    public void update() {
        y -= speedY;
        if (y + radius * 2 < 0) {
            reset();
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(x, y, radius * 2, radius * 2);
    }

    private void reset() {
        Random rand = new Random();
        this.x = rand.nextInt(screenHeight);
        this.y = screenHeight + rand.nextInt(100);
        this.radius = rand.nextInt(10) + 5;
        this.speedY = rand.nextInt(3) + 1;
    }
}
