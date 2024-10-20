package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
import javax.swing.Timer;

/***
 * GamePanel manages the main game loop, drawing the player, and handling key inputs.
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private static final Color HUD_COLOR = Color.BLACK;
    private static final Color PAUSE_OVERLAY_COLOR = new Color(255, 0, 0, 50);
    private static final Color WALL_COLOR = new Color(0, 0, 0, 150); // Slightly transparent black for walls

    private final Timer timer;
    private final Player player;
    private final MapManager mapManager;
    private final Camera camera;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private boolean gamePaused = false;
    private int points = 0;
    public static final int HUD_HEIGHT = 50; // Adjusted to follow naming convention
    public static final int MAP_WIDTH = 5000; // Adjusted to follow naming convention
    public static final int MAP_HEIGHT = 3000; // Adjusted to follow naming convention

    public GamePanel() {
        // Player starts at the exact bottom-left corner
        player = new Player(100, MAP_HEIGHT - 150); // Starts at the bottom-left corner, a little off the wall for visibility
        mapManager = new MapManager();
        camera = new Camera(0, 0);

        initializeMapTiles();

        timer = new Timer(30, this);
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    private void initializeMapTiles() {
        MapTile entranceTile = new MapTile(new Color(0, 100, 255));
        entranceTile.addEnemy(new CrabEnemy(300, 200));
        entranceTile.addObstacle(new Obstacle(200, 150, 100, 50));
        entranceTile.addCurrent(new WaterCurrent(100, 100, 1, 0));

        MapTile deepTile = new MapTile(new Color(0, 0, 139));
        deepTile.addEnemy(new LargeFishEnemy(400, 300));
        deepTile.addPainting(new Painting(500, 100));

        mapManager.addTile(entranceTile);
        mapManager.addTile(deepTile);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gamePaused) {
            player.update(upPressed, downPressed, leftPressed, rightPressed, this);
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

        // Set a dynamic blue gradient background that moves with the camera
        drawDynamicBlueBackground(g);

        // The camera translates the map and player, but not the HUD
        g.translate(-camera.getX(), -camera.getY());
        mapManager.drawCurrentTile(g);
        drawWalls(g); // Draw walls on all four sides of the map
        player.draw(g);

        // Translate back to prevent the HUD from moving with the camera
        g.translate(camera.getX(), camera.getY());

        // Draw the static HUD that stays at the top of the screen
        drawHUD(g);

        // If the game is paused, overlay the screen with a 20% red tint
        if (gamePaused) {
            drawPauseOverlay(g);
        }
    }

    private void drawHUD(Graphics g) {
        // Draw a black bar at the top for the HUD
        g.setColor(HUD_COLOR);
        g.fillRect(0, 0, getWidth(), HUD_HEIGHT);

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
            g.fillRect(20 + (i * 20), 10, 15, 15);
        }
    }

    private void drawPauseButton(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Pause (P)", getWidth() / 2 - 40, 30);
    }

    private void drawPointsCounter(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Points: " + points, getWidth() - 120, 30);
    }

    private void drawPauseOverlay(Graphics g) {
        g.setColor(PAUSE_OVERLAY_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawDynamicBlueBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Set up the gradient to dynamically change based on camera position
        int centerX = getWidth() / 2 + camera.getX();
        int centerY = getHeight() / 2 + camera.getY();
        float radius = (float) Math.max(getWidth(), getHeight()) / 2.0f;
        Point2D center = new Point2D.Float(centerX, centerY);

        // Create a radial gradient from lighter mid blue to very dark blue
        Color[] colors = { new Color(173, 216, 230, 0), new Color(0, 0, 139, 200), new Color(0, 0, 50, 255) };
        float[] fractions = { 0.5f, 0.8f, 1.0f };

        RadialGradientPaint gradient = new RadialGradientPaint(center, radius, fractions, colors);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, MAP_WIDTH, MAP_HEIGHT);
    }

    private void drawWalls(Graphics g) {
        g.setColor(WALL_COLOR);

        // Draw walls on all four sides of the map
        g.fillRect(0, 0, 50, MAP_HEIGHT); // Left wall
        g.fillRect(0, HUD_HEIGHT, MAP_WIDTH, 50); // Top wall
        g.fillRect(MAP_WIDTH - 50, 0, 50, MAP_HEIGHT); // Right wall
        g.fillRect(0, MAP_HEIGHT - 50, MAP_WIDTH, 50); // Bottom wall
    }

    public void increasePoints() {
        points += 100;
    }

    public void startGame() {
        timer.start();
    }
}
