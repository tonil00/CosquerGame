package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * LargeFishEnemy represents a larger enemy that swims at random angles and bounces off walls.
 */
public class LargeFishEnemy extends Enemy {

    private double speedX;
    private double speedY;
    private final int width;
    private final int height;

    public LargeFishEnemy(int startX, int startY) {
        super(startX, startY, 70, 40); // Ensuring constructor matches superclass constructor
        this.width = 70;
        this.height = 40;
        this.speedX = 2 * (Math.random() > 0.5 ? 1 : -1);
        this.speedY = 2 * (Math.random() > 0.5 ? 1 : -1);
    }

    @Override
    public void update(Player player) {
        x += speedX;
        y += speedY;

        if (x <= 0 || x + width >= 5000) {
            speedX *= -1;
        }
        if (y <= 50 || y + height >= 3000) {
            speedY *= -1;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
