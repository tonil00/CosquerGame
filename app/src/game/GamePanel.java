package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    // Timer to control the game loop
    private Timer timer;
    private Player player;
    private List<Obstacle> obstacles;

    // Track which keys are pressed
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public GamePanel() {
        this.setBackground(java.awt.Color.BLUE); // Set background to represent the sea
        this.setFocusable(true);
        this.addKeyListener(this); // Listen for key events

        player = new Player(100, 100); // Create a new Player instance (the fish)
        timer = new Timer(30, this); // Timer for the game loop (30ms delay)

        // Initialize obstacles (Example obstacles, you can customize them)
        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(200, 150, 100, 50));
        obstacles.add(new Obstacle(300, 300, 50, 100));
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
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g); // Draw obstacles
        }
    }

    // Update the game (move the fish)
    @Override
    public void actionPerformed(ActionEvent e) {
        // Pass the key press status and obstacles to the player's update method
        player.update(upPressed, downPressed, leftPressed, rightPressed, obstacles);
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
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}
