package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Obstacle {
    // Declare the position, size, and speed of the obstacle
    private int x;
    private int y;
    private final int width;
    private final int height;
    private final int speedX;
    private final int speedY;
    private final int movementType;
    private final Random rand = new Random();  // Random number generator

    public static final int LINEAR = 0;
    public static final int ZIGZAG = 1;
    public static final int RANDOM = 2;

    // Constructor to initialize the obstacle
    public Obstacle(int startX, int startY, int width, int height, int speedX, int speedY, int movementType) {
        this.x = startX;  // Initialize the x-coordinate
        this.y = startY;  // Initialize the y-coordinate
        this.width = width;
        this.height = height;
        this.speedX = speedX;
        this.speedY = speedY;
        this.movementType = movementType;
    }

    // Method to update the obstacle's position
    public void update() {
        switch (movementType) {
            case LINEAR -> {
                x += speedX;
                y += speedY;
            }
            case ZIGZAG -> {
                x += speedX;
                y += Math.sin(x * 0.05) * 10;
            }
            case RANDOM -> {
                x += rand.nextInt(5) - 2;
                y += rand.nextInt(5) - 2;
            }
        }               
    }
    

    // Method to draw the obstacle on the screen
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);  // Draw the obstacle as a red rectangle
    }

    // Getters for position and size
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
