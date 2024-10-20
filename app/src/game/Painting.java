package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * Painting represents the collectible prehistoric paintings in the game.
 */
public class Painting {

    private final int x, y, width, height;
    private boolean collected;

    public Painting(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 30;
        this.collected = false;
    }

    public void draw(Graphics g) {
        if (!collected) {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, width, height); // Draw collectible painting as a yellow square
        }
    }

    public void collect() {
        collected = true;
    }

    public boolean isCollected() {
        return collected;
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
