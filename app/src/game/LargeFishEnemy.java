package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * LargeFishEnemy represents a larger enemy fish.
 */
public class LargeFishEnemy extends Enemy {

    private final int moveSpeed = 2; // Marking moveSpeed as final
    private int x, y, width, height;

    public LargeFishEnemy(int x, int y) {
        super(x, y, 5); // Initialize with health = 5
        this.x = x;
        this.y = y;
        this.width = 60;
        this.height = 30;
    }

    @Override
    public void update(Player player) {
        // Define large fish behavior, e.g., follow the player
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE); // Color for large fish
        g.fillRect(x, y, width, height); // Draw large fish as a rectangle
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
