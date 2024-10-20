package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * Abstract Enemy class representing a generic enemy in the game.
 */
public abstract class Enemy {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Enemy(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void update(Player player);

    public abstract void draw(Graphics g);

    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
