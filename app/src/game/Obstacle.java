package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * Obstacle represents static objects in the game that the player must avoid.
 */
public class Obstacle {

    private final int x, y, width, height;

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY); // Draw the obstacle as dark gray
        g.fillRect(x, y, width, height); // Draw the obstacle
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
