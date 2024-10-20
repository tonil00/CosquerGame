package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * CrabEnemy represents a crab enemy that damages the player upon contact.
 */
public class CrabEnemy extends Enemy {

    private final int x, y, width, height; // These fields are final because they are not modified

    public CrabEnemy(int x, int y) {
        super(x, y, 3); // Initialize the crab with a set health of 3
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 20;
    }

    @Override
    public void update(Player player) {
        // Define behavior, e.g., moving towards the player or random movement
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED); // Red color to indicate enemy
        g.fillRect(x, y, width, height); // Draw the crab as a rectangle
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
