package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * LargeFishEnemy class represents a larger fish that moves towards the player.
 */
public class LargeFishEnemy extends Enemy {

    private int moveSpeed = 2;

    public LargeFishEnemy(int x, int y) {
        super(x, y, 60, 30); // Larger fish
    }

    @Override
    public void update(Player player) {
        // Move towards the player
        if (player.getX() > x) {
            x += moveSpeed;
        } else {
            x -= moveSpeed;
        }

        if (player.getY() > y) {
            y += moveSpeed;
        } else {
            y -= moveSpeed;
        }

        // Check if the fish touches the player
        if (getHitbox().intersects(player.getHitbox())) {
            player.takeDamage();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height); // Draw larger fish as an orange rectangle
    }

    /***
     * Get the hitbox of the larger fish for collision detection.
     */
    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
