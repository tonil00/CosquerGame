package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * Painting represents a collectible prehistoric painting in the game.
 */
public class Painting {

    private int x, y;
    private int width = 30;
    private int height = 30;
    private boolean collected;

    public Painting(int x, int y) {
        this.x = x;
        this.y = y;
        this.collected = false;
    }

    public void draw(Graphics g) {
        if (!collected) {
            g.setColor(Color.YELLOW); // Bright yellow to stand out
            g.fillRect(x, y, width, height); // Draw painting as a square for now
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
