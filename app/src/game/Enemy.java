package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemy {

    protected int x, y;
    protected int width, height;

    public Enemy(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Abstract method for movement
    public abstract void move();

    // Abstract method for drawing
    public abstract void draw(Graphics g, int cameraX, int cameraY);

    // Get the bounding box for collision detection
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
