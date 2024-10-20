package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/***
 * CrabEnemy represents a crab enemy that moves left and right like a crab.
 */
public class CrabEnemy extends Enemy {

    private final int width, height;
    private int moveDirection = 1; // 1 means moving right, -1 means moving left
    private final int moveSpeed = 2; // Speed of movement
    private final int leftLimit, rightLimit; // Defines the crab's range of movement
    private final Random random;

    public CrabEnemy(int x, int y) {
        super(x, y, 3); // Crab does 3 damage
        this.width = 40;
        this.height = 20;
        this.random = new Random();

        // Ensure the crab stays within a safe range of the screen width
        this.leftLimit = x - 100;  // Crab can move 100 pixels left from its starting point
        this.rightLimit = x + 100; // Crab can move 100 pixels right from its starting point
    }

    @Override
    public void update(Player player) {
        // Move the crab left and right, bouncing at the limits
        if (x >= rightLimit) {
            moveDirection = -1; // Move left if hitting the right limit
        } else if (x <= leftLimit) {
            moveDirection = 1; // Move right if hitting the left limit
        }

        x += moveSpeed * moveDirection; // Move crab

        // Randomly change direction for a more "dancing" movement style
        if (random.nextInt(100) < 2) { // 2% chance to change direction each frame
            moveDirection *= -1;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height); // Draw crab as a red rectangle
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
