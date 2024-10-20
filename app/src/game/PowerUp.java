package game;

import java.awt.Color;
import java.awt.Graphics;

public class PowerUp {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final int type;
    private boolean isActive;

    public PowerUp(int startX, int startY, int width, int height, int type) {
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
        this.type = type;
        this.isActive = true;
    }

    public void draw(Graphics g) {
        if (isActive) {
            switch (type) {
                case 0 -> g.setColor(Color.BLUE);  // Speed boost
                case 1 -> g.setColor(Color.GREEN);  // Shield
                case 2 -> g.setColor(Color.PINK);   // Health
            }            
            g.fillRect(x, y, width, height);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void collect() {
        this.isActive = false;  // Deactivate power-up when collected
    }

    public int getType() {
        return type;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
