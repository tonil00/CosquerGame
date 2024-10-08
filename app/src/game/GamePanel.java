package game;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    // Timer to control the game loop
    private Timer timer;
    private Player player;
    private final int playerSpeed = 5; // Movement speed

    // Track which keys are pressed
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public GamePanel() {
        this.setBackground(java.awt.Color.BLUE); // Set background to represent the sea
        this.setFocusable(true);
        this.addKeyListener(this); // Listen for key events

        player = new Player(100, 100); // Create a new Player instance (the fish)
        timer = new Timer(30, this); // Timer for the game loop (30ms delay)
    }

    // Start the game loop
    public void startGame() {
        timer.start(); // Start the timer, which calls actionPerformed
    }

    // Render the game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g); // Draw the fish
    }

    // Update the game (move the fish)
    @Override
    public void actionPerformed(ActionEvent e) {
        updatePlayerSpeed(); // Calculate speed based on keys pressed
        player.update(); // Update the fish's position
        repaint(); // Repaint the screen after every game update
    }

    // Handle key press events
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Set flags when keys are pressed
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;
        }

        updatePlayerSpeed(); // Update fish movement when key is pressed
    }

    // Handle key releases to stop the fish's movement
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Reset flags when keys are released
        switch (keyCode) {
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_DOWN:
                downPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
        }

        updatePlayerSpeed(); // Update fish movement when key is released
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    // Calculate the fish's speed based on the keys currently pressed
    private void updatePlayerSpeed() {
        int speedX = 0;
        int speedY = 0;

        if (upPressed)
            speedY = -playerSpeed;
        if (downPressed)
            speedY = playerSpeed;
        if (leftPressed)
            speedX = -playerSpeed;
        if (rightPressed)
            speedX = playerSpeed;

        // Set the fish's speed (handles diagonal movement automatically)
        player.setSpeed(speedX, speedY);
    }
}
