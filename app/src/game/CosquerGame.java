package game;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/***
 * CosquerGame is the main class that starts the game.
 */
public class CosquerGame {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Cosquer Cave Explorer");
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        ImageIcon icon = new ImageIcon("../img/logo.jpg");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);

        // Start the game loop
        gamePanel.startGame();
    }
}
