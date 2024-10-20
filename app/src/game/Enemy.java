package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * Enemy is the base class for all enemy types in the game.
 */
public abstract class Enemy {

    protected int x, y;
    protected final int damage;

    public Enemy(int x, int y, int damage) {
        this.x = x;
        this.y = y;
        this.damage = damage;
    }

    public abstract void update(Player player);

    public abstract void draw(Graphics g);

    public abstract Rectangle getHitbox();

    public int getDamage() {
        return damage;
    }
}
