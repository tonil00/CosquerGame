package game;

import javax.swing.JFrame;

/***
 * CosquerGame is the main class that starts the game.
 */
public class CosquerGame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cosquer Cave Explorer");
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);

        gamePanel.startGame(); // Start the game loop
    }
}
