package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuPanel implements MouseListener {
    private GamePanel gamePanel;
    private Image backgroundImage;
    private Button playButton;
    private boolean buttonPositionSet = false;

    public MenuPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.backgroundImage = loadImage("/images/menu_background.png");
        this.playButton = new Button(0, 0, 200, 80, "Play");
    }

    private Image loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            System.err.println("Error loading image: " + path);
            return null;
        }
    }

    public void draw(Graphics2D g2d) {
        // Draw background
        g2d.drawImage(backgroundImage, 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), null);

        // Set the button position if it hasn't been set yet
        if (!buttonPositionSet) {
            int buttonX = (gamePanel.getWidth() - playButton.getWidth()) / 2;
            int buttonY = (gamePanel.getHeight() - playButton.getHeight()) / 2;
            playButton.setPosition(buttonX, buttonY);
            buttonPositionSet = true; // Mark the position as set
        }

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
