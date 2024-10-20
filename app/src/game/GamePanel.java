package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

/***
 * GamePanel class manages the game's main rendering and logic, including
 * player movement, enemy interactions, and map transitions.
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private final Player player;
    private final HUD hud;
    private final MapManager mapManager;

    // Track which keys are pressed
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public GamePanel() {
        this.setBackground(Color.BLUE); // Set background to represent the sea
        this.setFocusable(true);
        this.addKeyListener(this); // Listen for key events

        player = new Player(100, 100); // Create a new Player instance (the fish)
        hud = new HUD(player); // Initialize the HUD
        timer = new Timer(30, this); // Timer for the game loop (30ms delay)

        // Initialize the map manager and add multiple map tiles
        mapManager = new MapManager();

        // Tile 1 - Initial tile
        MapTile tile1 = new MapTile(Color.BLUE);
        tile1.addObstacle(new Obstacle(200, 150, 100, 50));
        tile1.addEnemy(new CrabEnemy(300, 200));
        tile1.addPainting(new Painting(400, 250));

        // Tile 2 - Second tile
        MapTile tile2 = new MapTile(Color.DARK_GRAY);
        tile2.addEnemy(new LargeFishEnemy(400, 300));
        tile2.addPainting(new Painting(500, 100));

        // Add tiles to the MapManager
        mapManager.addTile(tile1);
        mapManager.addTile(tile2);

        // Start the game
        startGame();
    }

    // Start the game loop
    public void startGame() {
        timer.start(); // Start the timer, which calls actionPerformed
    }

    // Render the game
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the current tile from the map manager
        mapManager.drawCurrentTile(g);

        // Draw the player and HUD
        player.draw(g);
        hud.draw(g);

        // Apply the darkness effect
        applyDarknessEffect((Graphics2D) g);

        // Check for win condition or game over
        if (areAllPaintingsCollected()) {
            drawWinScreen(g);
        } else if (!player.isAlive()) {
            drawGameOverScreen(g);
        }
    }

    // Apply darkness effect: A circular light source around the player
    private void applyDarknessEffect(Graphics2D g2d) {
        int playerX = player.getX();
        int playerY = player.getY();
        int lightRadius = 200;

        // Create a dark overlay
        g2d.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black for darkness
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Create a light gradient around the player
        RadialGradientPaint gradient = new RadialGradientPaint(
            playerX + player.getWidth() / 2,
            playerY + player.getHeight() / 2,
            lightRadius,
            new float[]{0f, 1f},
            new Color[]{new Color(255, 255, 255, 0), new Color(0, 0, 0, 150)}
        );

        g2d.setPaint(gradient);
        g2d.fill(new Ellipse2D.Double(
            playerX - lightRadius + player.getWidth() / 2,
            playerY - lightRadius + player.getHeight() / 2,
            lightRadius * 2,
            lightRadius * 2
        ));
    }

    // Update the game logic
    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the player's position and interactions with the current map tile
        player.update(upPressed, downPressed, leftPressed, rightPressed, mapManager.getCurrentObstacles(), mapManager.getCurrentCurrents(), mapManager.getCurrentEnemies(), mapManager.getCurrentPaintings());

        // Check for map tile transitions
        mapManager.updateTileTransition(player);

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

    // Check if all paintings are collected
    private boolean areAllPaintingsCollected() {
        for (Painting painting : mapManager.getCurrentPaintings()) {
            if (!painting.isCollected()) {
                return false;
            }
        }
        return true;
    }

    // Draw the win screen when all paintings are collected
    private void drawWinScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("You Win!", 300, 250);
    }

    // Draw the game over screen when the player dies
    private void drawGameOverScreen(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Game Over", 300, 250);
    }
}
