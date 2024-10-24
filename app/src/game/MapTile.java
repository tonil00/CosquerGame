package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MapTile {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public MapTile(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g, int cameraX, int cameraY) {
        g.setColor(Color.GRAY);
        g.fillRect(x - cameraX, y - cameraY, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
