package game;

import java.awt.Color;
import java.awt.Graphics;

public class DarknessEffect {
    private final int screenWidth, screenHeight;

    public DarknessEffect(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void update(Player player) {
        // Logic to update darkness based on player position can be implemented here
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150)); // Transparent black
        g.fillRect(0, 0, screenWidth, screenHeight);
    }
}
