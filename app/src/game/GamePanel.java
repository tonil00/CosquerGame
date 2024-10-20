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
 * GamePanel manages the main game loop, drawing the player, and handling key inputs.
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private static final Color DARK_BLUE = new Color(0, 0, 139);  // Define DARK_BLUE color
    private static final Color DARKER_BLUE = new Color(0, 0, 100); // Define DARKER_BLUE color

    private final Timer timer;
    private final Player player;
    private final MapManager mapManager;
    private final Camera camera;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public GamePanel() {
        this.setBackground(Color.BLUE); // Set background color to represent water
        this.setFocusable(true);
        this.addKeyListener(this); // Add key listener

        player = new Player(100, 100); // Create a new player instance at (100, 100)
        mapManager = new MapManager();
        camera = new Camera(0, 0); // Initialize camera

        initializeMapTiles(); // Set up the map tiles and game world

        timer = new Timer(30, this); // Create a timer with 30ms delay for the game loop
        startGame(); // Start the game
    }

    // Initialize the map tiles with obstacles, enemies, and paintings
    private void initializeMapTiles() {
        // Tile 1 - Entrance section
        MapTile entranceTile = new MapTile(DARK_BLUE);
        entranceTile.addEnemy(new CrabEnemy(300, 200)); // Add enemies
        entranceTile.addObstacle(new Obstacle(200, 150, 100, 50)); // Add obstacles
        entranceTile.addCurrent(new WaterCurrent(100, 100, 1, 0)); // Water current example

        // Tile 2 - Deeper cave section
        MapTile deepTile = new MapTile(DARKER_BLUE);
        deepTile.addEnemy(new LargeFishEnemy(400, 300));
        deepTile.addPainting(new Painting(500, 100));

        // Add more tiles based on the segmentation of the cave
        mapManager.addTile(entranceTile);
        mapManager.addTile(deepTile);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the player's position and the map
        player.update(upPressed, downPressed, leftPressed, rightPressed,
                      mapManager.getCurrentObstacles(), mapManager.getCurrentCurrents(),
                      mapManager.getCurrentEnemies(), mapManager.getCurrentPaintings());
        mapManager.updateTileTransition(player);
        camera.update(player); // Update camera to follow the player
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
        g.translate(-camera.getX(), -camera.getY()); // Offset drawing based on camera position
        mapManager.drawCurrentTile(g); // Draw the current map tile
        player.draw(g); // Draw the player
    }

    public void startGame() {
        timer.start(); // Start the game loop timer
    }
}
