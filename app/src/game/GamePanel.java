package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {
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
    private List<Enemy> enemies;
    private HeartBar heartBar;
    private MuteButton muteButton;
    private AudioPlayer bgMusic;

    public GamePanel() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);

        player = new Player(400, 500);
        timer = new Timer(30, this);

        heartBar = new HeartBar(3);
        int buttonX = getWidth() - 42; // Adjust to position at top-right corner
        int buttonY = 10;
        muteButton = new MuteButton(buttonX, buttonY);

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

        enemies = new ArrayList<>();

        // Add enemies to the game
        enemies.add(new Enemy(200, 400, 2)); // Enemy starting at (200, 400) moving right
        enemies.add(new Enemy(600, 300, -2)); // Enemy starting at (600, 300) moving left
    }

    public void startGame() {
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Save the original transform
        AffineTransform originalTransform = g2d.getTransform();

        // Set rendering hints for pixelated scaling
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        double zoom = camera.getZoom();

        // Apply scaling and translation for the game world
        g2d.scale(zoom, zoom);
        g2d.translate(-camera.getX(), -camera.getY());

        // Draw the map
        g2d.drawImage(mapImage, 0, 0, null);

        // Draw the player
        player.draw(g2d);

        // Draw the enemies
        for (Enemy enemy : enemies) {
            enemy.draw(g2d);
        }

        // Restore the original transform to work in screen coordinates
        g2d.setTransform(originalTransform);

        // Calculate player's position on screen
        int playerScreenX = (int) ((player.getX() - camera.getX()) * zoom);
        int playerScreenY = (int) ((player.getY() - camera.getY()) * zoom);

        // Adjust for player's size after scaling
        int playerWidth = (int) (player.getWidth() * zoom);
        int playerHeight = (int) (player.getHeight() * zoom);

        // Calculate center position of the player
        int playerCenterX = playerScreenX + playerWidth / 2;
        int playerCenterY = playerScreenY + playerHeight / 2;

        // Create the darkness overlay with a radial gradient
        int lightRadius = 150;

        // Define the gradient colors and fractions
        float[] fractions = { 0f, 0.2f, 0.4f, 0.6f, 0.8f, 1f };
        Color[] colors = {
                new Color(0, 0, 0, 0), // Center: fully transparent
                new Color(0, 0, 0, 30), // Light darkness
                new Color(0, 0, 0, 80), // Medium darkness
                new Color(0, 0, 0, 130), // Darker
                new Color(0, 0, 0, 180), // Even darker
                new Color(0, 0, 0, 230) // Edge: darkest
        };

        RadialGradientPaint radialPaint = new RadialGradientPaint(
                new Point(playerCenterX, playerCenterY), // Center of the gradient
                lightRadius, // Radius of the gradient
                fractions,
                colors,
                MultipleGradientPaint.CycleMethod.NO_CYCLE);

        // Set the paint to the radial gradient
        g2d.setPaint(radialPaint);

        // Draw the gradient over the entire screen
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Reset the paint to default
        g2d.setPaint(null);

        // Draw UI elements (not scaled)
        drawUI(g2d);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update player and other game elements
        updatePlayerSpeed();
        player.update();

        // Update enemies
        for (Enemy enemy : enemies) {
            enemy.update();
        }

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

    private void drawUI(Graphics2D g2d) {
        // Draw the heart bar
        heartBar.draw(g2d);

        int buttonX = getWidth() - muteButton.width - 10;
        int buttonY = 10;
        muteButton.x = buttonX;
        muteButton.y = buttonY;

        // Draw the mute button
        muteButton.draw(g2d);
    }

    // Implement MouseListener methods
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (muteButton.isClicked(mouseX, mouseY)) {
            muteButton.toggleMute();
            if (muteButton.isMuted()) {
                bgMusic.stop();
            } else {
                bgMusic.play();
            }
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

    public void setBackgroundMusic(AudioPlayer bgMusic) {
        this.bgMusic = bgMusic;
    }
}
