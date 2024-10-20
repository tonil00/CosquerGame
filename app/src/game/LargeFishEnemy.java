package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * LargeFishEnemy represents a large fish enemy in the game.
 */
public class LargeFishEnemy extends Enemy {

    private int x, y, width, height; // x and y are no longer final, so they can be modified
    private final int moveSpeed = 2; // moveSpeed remains final because it won't change

    public LargeFishEnemy(int x, int y) {
        super(x, y, 5); // Initialize with health = 5
        this.x = x;
        this.y = y;
        this.width = 60;
        this.height = 30;
    }

    @Override
    public void update(Player player) {
        // Example of utilizing moveSpeed for movement logic
        if (player.getX() > x) {
            x += moveSpeed;
        } else if (player.getX() < x) {
            x -= moveSpeed;
        }
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
