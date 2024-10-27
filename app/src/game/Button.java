package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * The Button class represents a clickable button in the game.
 * It can be drawn on the screen and can detect mouse clicks.
 */
public class Button {
    private int x;
    private int y;
    private int width;
    private int height;
    private String text;
    private Rectangle bounds;

    /**
     * Constructs a new Button with the specified position, size, and text.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param width the width of the button
     * @param height the height of the button
     * @param text the text to display on the button
     */
    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Draws the button on the screen.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    public void draw(Graphics2D g2d) {
        // Draw button rectangle
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, width, height);

        // Draw button border
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, width, height);

        // Draw text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 36));
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height + g2d.getFontMetrics().getAscent()) / 2 - 10;
        g2d.drawString(text, textX, textY);
    }

    public boolean isClicked(int mouseX, int mouseY) {
        return bounds.contains(mouseX, mouseY);
    }

    /**
     * Sets the position of the button.
     *
     * @param x the new x-coordinate of the button
     * @param y the new y-coordinate of the button
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.setLocation(x, y); // Update bounds with new position
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
