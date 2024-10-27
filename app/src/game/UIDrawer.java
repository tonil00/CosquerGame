package game;

import java.awt.*;

/**
 * The UIDrawer class is responsible for drawing the user interface elements
 * such as the heart bar, mute button, and score text.
 */
public class UIDrawer {
    private HeartBar heartBar;
    private MuteButton muteButton;
    private PaintingManager paintingManager;
    private Font uiFont = new Font("Arial", Font.BOLD, 20);

    /**
     * Constructs a UIDrawer with the specified HeartBar, MuteButton, and PaintingManager.
     *
     * @param heartBar the HeartBar to be drawn
     * @param muteButton the MuteButton to be drawn
     * @param paintingManager the PaintingManager to get painting information
     */
    public UIDrawer(HeartBar heartBar, MuteButton muteButton, PaintingManager paintingManager) {
        this.heartBar = heartBar;
        this.muteButton = muteButton;
        this.paintingManager = paintingManager;
    }

    /**
     * Draws the user interface elements on the provided Graphics2D object.
     *
     * @param g2d the Graphics2D object to draw on
     * @param panelWidth the width of the panel to position elements correctly
     */
    public void drawUI(Graphics2D g2d, int panelWidth) {
        heartBar.draw(g2d);

        int buttonX = panelWidth - muteButton.width - 10;
        muteButton.x = buttonX;
        muteButton.y = 10;
        muteButton.draw(g2d);

        String scoreText = paintingManager.getCollectedCount()
                + "/" + paintingManager.getTotalPaintings();
        g2d.setColor(Color.YELLOW);
        g2d.setFont(uiFont);
        int textWidth = g2d.getFontMetrics().stringWidth(scoreText);
        g2d.drawString(scoreText, (panelWidth - textWidth) / 2, 30);
    }
}
