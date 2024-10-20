package game;

import java.awt.Color;
import java.awt.Font;
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

    private static final Color DARK_BLUE = new Color(0, 0, 139);  
    private static final Color DARKER_BLUE = new Color(0, 0, 100);
    private static final Color HUD_COLOR = Color.BLACK; // Black color for the HUD bar
    private static final Color PAUSE_OVERLAY_COLOR = new Color(255, 0, 0, 50); // 20% Red overlay when paused
    
    private final Timer timer;
    private final Player player;
    private final MapManager mapManager;
    private final Camera camera;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private boolean gamePaused = false; // Boolean to handle pause state
    private int points = 0; // Points counter
    private final int hudHeight = 50; // Height of the HUD bar
    
    public GamePanel() {
        player = new Player(100, 100); 
        mapManager = new MapManager();
        camera = new Camera(0, 0); 

        initializeMapTiles(); 

        timer = new Timer(30, this); 
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    private void initializeMapTiles() {
        MapTile entranceTile = new MapTile(DARK_BLUE);
        entranceTile.addEnemy(new CrabEnemy(300, 200)); 
        entranceTile.addObstacle(new Obstacle(200, 150, 100, 50)); 
        entranceTile.addCurrent(new WaterCurrent(100, 100, 1, 0)); 

        MapTile deepTile = new MapTile(DARKER_BLUE);
        deepTile.addEnemy(new LargeFishEnemy(400, 300));
        deepTile.addPainting(new Painting(500, 100));

        mapManager.addTile(entranceTile);
        mapManager.addTile(deepTile);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gamePaused) {
            player.update(upPressed, downPressed, leftPressed, rightPressed,
                          mapManager.getCurrentObstacles(), mapManager.getCurrentCurrents(),
                          mapManager.getCurrentEnemies(), mapManager.getCurrentPaintings(),
                          this);
            mapManager.updateTileTransition(player);
            camera.update(player);
        }
        repaint(); 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_DOWN -> downPressed = true;
            case KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_RIGHT -> rightPressed = true;
            case KeyEvent.VK_P -> gamePaused = !gamePaused; // Press 'P' to toggle pause
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
    public void keyTyped(KeyEvent e) {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // The camera translates the map and player, but not the HUD
        g.translate(-camera.getX(), -camera.getY());
        mapManager.drawCurrentTile(g); 
        player.draw(g); 

        // HUD is drawn independently of camera movement
        drawHUD(g);

        // If the game is paused, overlay the screen with a 20% red tint
        if (gamePaused) {
            drawPauseOverlay(g);
        }
    }

    private void drawHUD(Graphics g) {
        // Draw a black bar at the top for the HUD
        g.setColor(HUD_COLOR);
        g.fillRect(0, 0, getWidth(), hudHeight);

        // Draw health (hearts) on the left side
        drawHealth(g);

        // Draw pause button in the middle of the HUD
        drawPauseButton(g);

        // Draw points counter on the right side of the HUD
        drawPointsCounter(g);
    }

    private void drawHealth(Graphics g) {
        g.setColor(Color.RED);
        for (int i = 0; i < player.getHealth(); i++) {
            g.fillRect(20 + (i * 20), 10, 15, 15); // Draw hearts for health
        }
    }

    private void drawPauseButton(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Pause (P)", getWidth() / 2 - 40, 30); // Centered pause button
    }

    private void drawPointsCounter(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Points: " + points, getWidth() - 120, 30); // Points at top right
    }

    private void drawPauseOverlay(Graphics g) {
        // Draw the red overlay when the game is paused
        g.setColor(PAUSE_OVERLAY_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void increasePoints() {
        points += 100; // Increase points when a painting is collected
    }

    public void startGame() {
        timer.start(); 
    }
}
