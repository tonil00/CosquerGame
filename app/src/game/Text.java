package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Text class is responsible for loading and rendering custom text using a specified font.
 */
public class Text {

    private Font customFont;

    public Text() {
        loadCustomFont("/fonts/yoster.ttf"); // Update the path as needed
    }

    private void loadCustomFont(String fontPath) {
        try (InputStream fontStream = getClass().getResourceAsStream(fontPath)) {
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream)
                             .deriveFont(34f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.out.println("Error loading custom font.");
            customFont = new Font("Arial", Font.PLAIN, 34);
        }
    }

    /**
     * Draws the custom text on the provided Graphics2D context.
     *
     * @param g2d the Graphics2D context to draw the text on
     * @param text the text to draw
     * @param x the x-coordinate of the text
     * @param y the y-coordinate of the text
     */
    public void drawText(Graphics2D g2d, String text, int x, int y) {
        if (customFont != null) {
            g2d.setFont(customFont);
        }

        // Set custom brown color
        g2d.setColor(new Color(217, 160, 102)); // RGB for brown

        // Draw the text
        g2d.drawString(text, x, y);
    }
}
