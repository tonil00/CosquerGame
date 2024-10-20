package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * WaterCurrent represents the currents that push the player in the game.
 */
public class WaterCurrent {

    private final int x, y, width, height;
    private final int pushX, pushY;

    public WaterCurrent(int x, int y, int pushX, int pushY) {
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 20;
        this.pushX = pushX;
        this.pushY = pushY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.CYAN); // Draw the current in cyan
        g.fillRect(x, y, width, height); // Draw the water current
    }

    public int getPushX() {
        return pushX;
    }

    public int getPushY() {
        return pushY;
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }
}
