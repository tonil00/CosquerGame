package game;

import java.awt.Color;
import java.awt.Graphics;

/***
 * HUD class manages the player's health and displays it on the screen.
 */
public class HUD {

    private final Player player;

    public HUD(Player player) {
        this.player = player;
    }

    /***
     * Draw the player's health bar (or hearts) on the screen.
     * 
     * @param g The Graphics object used for drawing.
     */
    public void draw(Graphics g) {
        g.setColor(Color.RED);

        // Draw hearts based on player's health
        for (int i = 0; i < player.getHealth(); i++) {
            g.fillRect(20 + i * 40, 20, 30, 30); // Draw a simple rectangle for each heart
        }
    }
}
