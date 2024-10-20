package game;

import javax.swing.JFrame;

/***
 * CosquerGame is the main class that starts the game.
 */
public class CosquerGame {

    public static void main(String[] args) {
        JFrame window = new JFrame("Cosquer Cave Explorer");
        GamePanel gamePanel = new GamePanel(); // Create the GamePanel

        window.add(gamePanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setSize(800, 600);
        window.setVisible(true);

        gamePanel.startGame(); // Start the game
    }
}
