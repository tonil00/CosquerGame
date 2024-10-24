package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class LargeFishEnemy extends Enemy {

    private final Random random = new Random();
    private double velocityX;
    private double velocityY;
    private final double maxSpeed = 4;
    private final double bounceFactor = 1.1;

    public LargeFishEnemy(int startX, int startY) {
        super(startX, startY, 60, 30);  // Using the constructor from Enemy class

        velocityX = (random.nextBoolean() ? 1 : -1) * (random.nextDouble() * maxSpeed);
        velocityY = (random.nextBoolean() ? 1 : -1) * (random.nextDouble() * maxSpeed);
    }

    @Override
    public void move() {
        x += velocityX;
        y += velocityY;

        // Bounce off the map boundaries
        if (x < 50 || x > 4500) {
            velocityX = -velocityX * bounceFactor;
        }
        if (y < 50 || y > 2950) {
            velocityY = -velocityY * bounceFactor;
        }

        velocityX = Math.max(-maxSpeed, Math.min(maxSpeed, velocityX));
        velocityY = Math.max(-maxSpeed, Math.min(maxSpeed, velocityY));
    }

    @Override
    public void draw(Graphics g, int cameraX, int cameraY) {
        g.setColor(Color.CYAN);
        g.fillOval(x - cameraX, y - cameraY, width, height);
    }
}
