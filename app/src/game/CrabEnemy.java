package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * CrabEnemy represents an enemy crab in the game.
 */
public class CrabEnemy extends Enemy {

    private int x, y, width, height;

    public CrabEnemy(int x, int y) {
        // Assuming Enemy constructor requires (int x, int y, int health)
        super(x, y, 3);  // Example: Initialize CrabEnemy with health = 3
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 20;
    }

    @Override
    public void update(Player player) {
        // Define crab behavior, e.g., moving towards the player or wandering
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED); // Color for crab
        g.fillRect(x, y, width, height); // Draw crab as a rectangle
    }

    @Override
    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
