package game;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CosquerGame {
    private JFrame window;
    private GamePanel gamePanel;

    public CosquerGame() {
        window = new JFrame("Cosquer Cave Explorer");

        // Set the window icon
        URL iconURL = getClass().getResource("/../img/logo.jpg");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            window.setIconImage(icon.getImage());
        }

        gamePanel = new GamePanel(1200, 1200); // Set large map size

        window.add(gamePanel);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);

        gamePanel.startGameThread(); // Starting the game loop
    }

    public static void main(String[] args) {
        new CosquerGame();
    }
}
