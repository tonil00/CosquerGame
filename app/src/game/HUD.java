package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

    private final Player player;  // Marked as final since it’s not reassigned

    public HUD(Player player) {
        this.player = player;
    }

    // Render the HUD (health, score) on the screen
    public void render(Graphics g) {
        // Draw Health
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Health: " + player.getHealth(), 20, 50);  // Health at the top-left corner

        // Draw Score
        g.drawString("Score: " + player.getScore(), 20, 80);  // Score displayed below health
    }
}
