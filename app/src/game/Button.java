package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button {
    private int x, y, width, height;
    private String text;
    private Rectangle bounds;

    public Button(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        bounds = new Rectangle(x, y, width, height);
    }

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
