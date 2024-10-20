package game;

import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * Enemy represents a generic enemy in the game.
 */
public abstract class Enemy {

    protected int x, y, health;

    public Enemy(int x, int y, int health) {
        this.x = x;
        this.y = y;
        this.health = health;
    }

    public abstract void update(Player player);

    public abstract void draw(Graphics g);

    public abstract Rectangle getHitbox();

    public void takeDamage() {
        health--;
        if (health <= 0) {
            // Handle death of the enemy
        }
    }
}
