package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * CrabEnemy class represents a crab that moves horizontally and damages the player on contact.
 */
public class CrabEnemy extends Enemy {

    private int moveDirection = 1; // 1 for right, -1 for left
    private int moveSpeed = 2;

    public CrabEnemy(int x, int y) {
        super(x, y, 40, 20); // Crabs are wider and shorter
    }

    @Override
    public void update(Player player) {
        // Move the crab horizontally
        x += moveDirection * moveSpeed;

        // Reverse direction if it hits the boundary
        if (x <= 0 || x >= 760) {
            moveDirection *= -1;
        }

        // Check if the crab touches the player
        if (getHitbox().intersects(player.getHitbox())) {
            player.takeDamage();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height); // Draw crab as a red rectangle
    }

    /***
     * Get the hitbox of the crab for collision detection.
     */
    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
