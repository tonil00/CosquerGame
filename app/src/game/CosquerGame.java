package game;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Main class to start the game.
 */
public class CosquerGame {

    public static void main(String[] args) {
        // Create the game window (JFrame)
        JFrame window = new JFrame("Cosquer Cave Explorer");

        // Set the window size
        window.setSize(800, 600);

        // Prevent the window from being resized
        window.setResizable(false);

        // Close the application when the window is closed
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            Image iconImage = ImageIO.read(
                    CosquerGame.class.getResourceAsStream("/images/fish.png"));
            window.setIconImage(iconImage); // Set the icon for the JFrame
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading icon image.");
        }

        // Add the game panel (the part that handles game rendering)
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // Make the window visible
        window.setVisible(true);

        // Play background music
        AudioPlayer bgMusic = new AudioPlayer("/sounds/background_music.wav");
        bgMusic.loop(); // Loop the music continuously

        // Play background underwater sounds
        AudioPlayer bgUnderWaterMusic = new AudioPlayer("/sounds/background_underwater.wav");
        bgUnderWaterMusic.loop(); // Loop the music continuously

        gamePanel.setBackgroundMusic(bgMusic);

        // Start the game loop (this can be inside the game panel or another class)
        gamePanel.startGame();
    }
}
