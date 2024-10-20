package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

    private int x;
    private int y;
    private final int width = 50;  // Player width
    private final int height = 30;  // Player height
    private int speedX;
    private int speedY;

    private final int gameWidth;
    private final int gameHeight;

    // Constructor to spawn player with default position
    public Player(int startX, int startY, int gameWidth, int gameHeight) {
        this.x = startX;
        this.y = startY;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.speedX = 0;
        this.speedY = 0;
    }

    // Method to manually set player's position
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Method to get player height
    public int getHeight() {
        return height;
    }

    // Update the player's position and enforce screen boundary constraints
    public void update() {
        x += speedX;
        y += speedY;

        // Prevent player from going beyond the left edge
        if (x < 0) {
            x = 0;
        }
        // Prevent player from going beyond the right edge (subtracting player width)
        if (x > gameWidth - (3*width)/2) {
            x = gameWidth - (3*width)/2;
        }
        // Prevent player from going beyond the top edge
        if (y < 0) {
            y = 0;
        }
        // Prevent player from going beyond the bottom edge (subtracting player height)
        if (y > gameHeight - 4*height) {
            y = gameHeight - 4*height;
        }
    }

    // Set player's speed based on user input
    public void setSpeed(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    // Draw the player on the screen
    public void draw(Graphics g) {
        g.setColor(Color.RED);  // Make the player red for visibility
        g.fillOval(x, y, width, height);  // Draw the player as a red oval
    }

    // Optional methods for health and score
    public int getHealth() {
        return 100;  // Default health
    }

    public int getScore() {
        return 0;  // Default score
    }

    // Get the x-coordinate of the player
    public int getX() {
        return x;
    }

    // Get the y-coordinate of the player
    public int getY() {
        return y;
    }
}
