package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    private int x, y, width, height;
    private double speedX, speedY;
    private double maxSpeed = 5;
    private double acceleration = 0.5;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speedX = 0;
        this.speedY = 0;
    }

    // Update player with smooth acceleration and deceleration
    public void update() {
        if (speedY < maxSpeed) {
            speedY += acceleration; // Accelerate upwards
        } else {
            speedY = maxSpeed; // Cap speed
        }

        if (speedX < maxSpeed) {
            speedX += acceleration; // Accelerate right
        } else {
            speedX = maxSpeed; // Cap speed
        }

        // Update position
        x += speedX;
        y += speedY;

        // Apply boundaries
        if (x < 0) x = 0; // Left boundary
        if (x + width > 1200) x = 1200 - width; // Right boundary
        if (y < 0) y = 0; // Top boundary
        if (y + height > 1200) y = 1200 - height; // Bottom boundary
    }

    // Draw the player
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW); // Fish is yellow
        g.fillOval(x, y, width, height);
    }

    // Setter methods for speed
    public void setSpeedX(double speed) {
        this.speedX = speed;
    }

    public void setSpeedY(double speed) {
        this.speedY = speed;
    }

    // Getter methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }
}
