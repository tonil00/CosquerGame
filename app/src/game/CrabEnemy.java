package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * CrabEnemy represents a crab enemy that moves horizontally back and forth.
 */
public class CrabEnemy extends Enemy {

    private int direction = 1;
    private int movedDistance = 0;
    private final int width;
    private final int height;

    public CrabEnemy(int startX, int startY) {
        super(startX, startY, 40, 20); // Ensuring constructor matches superclass constructor
        this.width = 40;
        this.height = 20;
    }

    @Override
    public void update(Player player) {
        x += direction * 2;
        movedDistance += 2;

        if (movedDistance >= 100) {
            direction *= -1;
            movedDistance = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
