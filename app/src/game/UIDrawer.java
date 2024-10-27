package game;

import java.awt.*;

public class UIDrawer {
    private HeartBar heartBar;
    private MuteButton muteButton;
    private PaintingManager paintingManager;
    private Font uiFont = new Font("Arial", Font.BOLD, 20);

    public UIDrawer(HeartBar heartBar, MuteButton muteButton, PaintingManager paintingManager) {
        this.heartBar = heartBar;
        this.muteButton = muteButton;
        this.paintingManager = paintingManager;
    }

    public void drawUI(Graphics2D g2d, int panelWidth) {
        heartBar.draw(g2d);

        int buttonX = panelWidth - muteButton.width - 10;
        muteButton.x = buttonX;
        muteButton.y = 10;
        muteButton.draw(g2d);

        String scoreText = paintingManager.getCollectedCount() + "/" + paintingManager.getTotalPaintings();
        g2d.setColor(Color.YELLOW);
        g2d.setFont(uiFont);
        int textWidth = g2d.getFontMetrics().stringWidth(scoreText);
        g2d.drawString(scoreText, (panelWidth - textWidth) / 2, 30);
    }
}
