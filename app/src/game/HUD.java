package game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
    private final int health = 3;

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        for (int i = 0; i < health; i++) {
            g.fillRect(20 + (i * 30), 10, 20, 20);
        }
        g.setColor(Color.WHITE);
        g.drawString("Pause (P)", 400, 20);
        g.drawString("Points: 0", 700, 20);
    }
}
