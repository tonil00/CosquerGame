package game;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Player player;
    private final int playerSpeed = 5;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private BufferedImage mapImage;
    private CollisionMaskGenerator mapCollisionMask;
    private Camera camera;

    public GamePanel() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);

        player = new Player(400, 500);
        timer = new Timer(30, this);

        try {
            // Load the main map image
            mapImage = ImageIO.read(getClass().getResourceAsStream("/images/cave_map.png"));

            // Generate collision mask from collision map image
            mapCollisionMask = new CollisionMaskGenerator("/images/collision_map.png");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading map images.");
        }

        // Set the world dimensions based on your map size
        int worldWidth = mapImage.getWidth();
        int worldHeight = mapImage.getHeight();

        // Set the screen dimensions (e.g., window size)
        int screenWidth = 800; // Replace with your actual screen width
        int screenHeight = 600; // Replace with your actual screen height

        camera = new Camera(worldWidth, worldHeight, screenWidth, screenHeight);
    }

    public void startGame() {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the visible portion of the map
        g.drawImage(
                mapImage,
                0, 0, camera.getScreenWidth(), camera.getScreenHeight(), // Destination (on screen)
                camera.getX(), camera.getY(), camera.getX() + camera.getScreenWidth(),
                camera.getY() + camera.getScreenHeight(), // Source (from map)
                null);

        // Draw the player adjusted for camera position
        player.draw(g, camera);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update player and other game elements
        updatePlayerSpeed();
        player.update();

        // Update camera to follow the player
        camera.update(player.getX(), player.getY());

        // Repaint the screen
        repaint();
    }

    private void updatePlayerSpeed() {
        int speedX = 0;
        int speedY = 0;

        int nextX = player.getX();
        int nextY = player.getY();

        if (upPressed) {
            speedY = -playerSpeed;
        }
        if (downPressed) {
            speedY = playerSpeed;
        }
        if (leftPressed) {
            speedX = -playerSpeed;
        }
        if (rightPressed) {
            speedX = playerSpeed;
        }

        nextX += speedX;
        nextY += speedY;

        if (player.canMove(nextX, nextY, mapCollisionMask)) {
            player.setSpeed(speedX, speedY);
        } else {
            player.setSpeed(0, 0);
        }
    }

    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_DOWN -> downPressed = true;
            case KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_RIGHT -> rightPressed = true;
            default -> {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_DOWN -> downPressed = false;
            case KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_RIGHT -> rightPressed = false;
            default -> {
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}
