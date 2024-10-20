package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/***
 * LargeFishEnemy represents a large fish that moves in random directions and bounces off walls.
 */
public class LargeFishEnemy extends Enemy {

    private final int width, height;
    private final int moveSpeed = 2; // Medium speed for the large fish
    private double angle; // Angle of movement in radians
    private final Random random;

    public LargeFishEnemy(int x, int y) {
        super(x, y, 5); // Large fish does 5 damage
        this.width = 60;
        this.height = 30;
        this.random = new Random();
        this.angle = random.nextDouble() * 2 * Math.PI; // Set initial random movement angle
    }

    @Override
    public void update(Player player) {
        // Move the fish in the current angle direction
        x += (int) (moveSpeed * Math.cos(angle));
        y += (int) (moveSpeed * Math.sin(angle));

        // Bounce off walls by changing the movement angle randomly
        if (x <= 0 || x >= 800 - width) { // Hitting left or right boundary
            angle = Math.PI - angle; // Reflect horizontally
            randomizeAngle(); // Add a slight randomization
        }

        if (y <= 0 || y >= 600 - height) { // Hitting top or bottom boundary
            angle = -angle; // Reflect vertically
            randomizeAngle(); // Add a slight randomization
        }
    }

    private void randomizeAngle() {
        // Add a random element to the bounce angle for unpredictability
        angle += (random.nextDouble() - 0.5) * 0.2; // Adjust angle slightly to avoid repetitive movements
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height); // Draw large fish as a blue rectangle
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
