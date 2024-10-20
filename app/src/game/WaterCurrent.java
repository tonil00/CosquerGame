package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/***
 * WaterCurrent represents a water current that pushes the player in a certain direction.
 */
public class WaterCurrent {

    private final int x, y, width, height;
    private final int pushX, pushY;

    public WaterCurrent(int x, int y, int pushX, int pushY) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 50;
        this.pushX = pushX;
        this.pushY = pushY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height); // Draw water current as a cyan rectangle
    }

    public Rectangle getHitbox() {
        return new Rectangle(x, y, width, height);
    }

    public int getPushX() {
        return pushX;
    }

    public int getPushY() {
        return pushY;
    }
}
