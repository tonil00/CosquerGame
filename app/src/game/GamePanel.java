package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/***
 * GamePanel manages the game loop, drawing the player, and handling key inputs.
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private final Timer timer;  // Marking timer as final
    private final Player player;
    private final MapManager mapManager;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public GamePanel() {
        this.setBackground(Color.BLUE); // Set background color to represent water
        this.setFocusable(true);  // Setup focus first to avoid "leaking this"
        this.addKeyListener(this); // Add key listener

        player = new Player(100, 100); // Create a new player instance at (100, 100)
        mapManager = new MapManager();

        initializeMapTiles(); // Set up the map tiles and game world

        timer = new Timer(30, this); // Create a timer with 30ms delay for the game loop
        timer.start(); // Start the game loop
    }

    // Initialize the map tiles with obstacles, enemies, and paintings
    private void initializeMapTiles() {
        MapTile tile1 = new MapTile(Color.DARK_GRAY);
        tile1.addObstacle(new Obstacle(200, 150, 100, 50));
        tile1.addEnemy(new CrabEnemy(300, 200));
        tile1.addPainting(new Painting(400, 250));

        MapTile tile2 = new MapTile(Color.GRAY);
        tile2.addEnemy(new LargeFishEnemy(400, 300));
        tile2.addPainting(new Painting(500, 100));

        mapManager.addTile(tile1);
        mapManager.addTile(tile2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the player's position and the map
        player.update(upPressed, downPressed, leftPressed, rightPressed,
                      mapManager.getCurrentObstacles(), mapManager.getCurrentCurrents(),
                      mapManager.getCurrentEnemies(), mapManager.getCurrentPaintings());
        mapManager.updateTileTransition(player);
        repaint(); // Redraw the screen
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_DOWN -> downPressed = true;
            case KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_RIGHT -> rightPressed = true;
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
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but required by KeyListener
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        mapManager.drawCurrentTile(g); // Draw the current map tile
        player.draw(g); // Draw the player
    }

    public void startGame() {
        timer.start(); // Start the game loop timer
    }
}
