package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    // Timer to control the game loop
    private Timer timer;
    private Player player;
    private final int playerSpeed = 5; // Movement speed
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private BufferedImage mapImage;
    private BufferedImage collisionMap;

    /**
     * Constructor for the game panel.
     */
    public GamePanel() {
        this.setBackground(java.awt.Color.BLUE); // Set background to represent the sea
        this.setFocusable(true);
        this.addKeyListener(this); // Listen for key events

        player = new Player(400, 500); // Create a new Player instance (the fish)
        timer = new Timer(30, this); // Timer for the game loop (30ms delay)

        try {
            // Load the main map image
            mapImage = ImageIO.read(new File("src/assets/images/cave_map.png"));

            // Load the black-and-white collision map
            collisionMap = ImageIO.read(new File("src/assets/images/collision_map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Start the game loop
    public void startGame() {
        timer.start(); // Start the timer, which calls actionPerformed
    }

    // Render the game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the main map
        g.drawImage(collisionMap, 0, 0, this);

        // Draw the player (fish)
        player.draw(g);
    }

    // Update the game (move the player)
    @Override
    public void actionPerformed(ActionEvent e) {
        updatePlayerSpeed(); // Calculate speed based on keys pressed
        player.update(); // Update the player's position
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
            default:
                break;
        }

        updatePlayerSpeed(); // Update player movement when key is pressed
    }

    // Handle key releases to stop the player's movement
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
            default:
                break;
        }

        updatePlayerSpeed(); // Update player movement when key is released
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    // Check if the player can move to the given position by reading the collision
    // map
    public boolean canMove(int nextX, int nextY) {
        // Ensure the position is within bounds
        if (nextX < 0 || nextY < 0 || nextX >= collisionMap.getWidth() || nextY >= collisionMap.getHeight()) {
            return false; // Out of bounds
        }

        // Get the color of the pixel at the next position
        int pixelColor = collisionMap.getRGB(nextX, nextY);
        Color color = new Color(pixelColor, true);

        // Allow movement if the pixel is not black (impassable)
        return !color.equals(Color.BLACK);
    }

    // Calculate the player's speed based on the keys currently pressed
    private void updatePlayerSpeed() {
        int speedX = 0;
        int speedY = 0;

        if (upPressed && canMove(player.getX(), player.getY() - playerSpeed)) {
            speedY = -playerSpeed;
        }
        if (downPressed && canMove(player.getX(), player.getY() + playerSpeed)) {
            speedY = playerSpeed;
        }
        if (leftPressed && canMove(player.getX() - playerSpeed, player.getY())) {
            speedX = -playerSpeed;
        }
        if (rightPressed && canMove(player.getX() + playerSpeed, player.getY())) {
            speedX = playerSpeed;
        }

        // Set the player's speed (handles diagonal movement automatically)
        player.setSpeed(speedX, speedY);
    }
}
