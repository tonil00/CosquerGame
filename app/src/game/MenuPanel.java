package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The MenuPanel class represents the menu screen of the game.
 * It handles the display of the menu and user interactions with it.
 */
public class MenuPanel implements MouseListener {
    private GamePanel gamePanel;
    private Image backgroundImage;
    private Button playButton;
    private Text titleText;

    /**
     * Constructs a MenuPanel with the specified GamePanel.
     *
     * @param gamePanel the GamePanel associated with this MenuPanel
     */
    public MenuPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.backgroundImage = loadImage("/images/menu_background.png");
        this.playButton = new Button(320, 380, 200, 80, "/images/button_play.png");
        this.titleText = new Text();
    }

    private Image loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            System.err.println("Error loading image: " + path);
            return null;
        }
    }

    /**
     * Draws the menu panel, including the background and the play button.
     *
     * @param g2d the Graphics2D object used for drawing
     */
    public void draw(Graphics2D g2d) {
        // Draw background
        g2d.drawImage(backgroundImage, 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), null);

        // Draw title text
        titleText.drawText(g2d, "Collect all the paintings,", 70, 200);
        titleText.drawText(g2d, "and awoid the jellyfish.", 70, 250);
        titleText.drawText(g2d, "Use the arrow keys to move the fish.", 70, 300);

        // Draw the play button
        playButton.draw(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (playButton.isClicked(mouseX, mouseY)) {
            gamePanel.resetGame();
            gamePanel.setGameState(GameState.PLAYING);
        }
    }

    // Other MouseListener methods (empty implementations)
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
