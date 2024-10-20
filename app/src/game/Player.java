package game;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

    private int x;
    private int y;
    private final int health = 100;  // Final since it’s not reassigned
    private final int score = 0;  // Final since it’s not reassigned
    private final int width;
    private final int height;
    private int speedX;
    private int speedY;

    // Constructor
    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.width = 50;
        this.height = 30;
        this.speedX = 0;
        this.speedY = 0;
    }

    // Getter for health
    public int getHealth() {
        return health;
    }

    // Getter for score
    public int getScore() {
        return score;
    }

    // Method to update player's position
    public void update() {
        x += speedX;
        y += speedY;
    }

    // Method to set player's speed
    public void setSpeed(int speedX, int speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    // Method to stop player's movement
    public void stopMoving() {
        this.speedX = 0;
        this.speedY = 0;
    }

    // Draw the player on the screen
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height); // Draw player as a yellow oval
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
