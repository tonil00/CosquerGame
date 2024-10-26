package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.checkerframework.checker.units.qual.s;

public class GamePanel extends JPanel implements ActionListener, MouseListener {
    private Timer timer;
    private Player player;
    private CollisionManager collisionManager;
    private final int playerSpeed = 4;
    private BufferedImage mapImage;
    private CollisionMaskGenerator mapCollisionMask;
    private Camera camera;
    private HeartBar heartBar;
    private MuteButton muteButton;
    private AudioPlayer bgMusic;
    private PlayerController playerController;
    private EnemyManager enemyManager;
    private Renderer renderer;
    private AudioPlayer collisionSound;
    private AudioPlayer deathSound;
    private GameState gameState;
    private MenuPanel menuPanel;

    public GamePanel() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);

        // Initialize game state
        gameState = GameState.MENU;

        player = new Player(400, 500);
        timer = new Timer(30, this);

        collisionSound = new AudioPlayer("/sounds/hit.wav"); // Collision sound file path
        deathSound = new AudioPlayer("/sounds/death.wav"); // Death sound file path

        heartBar = new HeartBar(3);
        muteButton = new MuteButton(0, 0); // Initial position will be updated

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

        enemyManager = new EnemyManager();

        // Add enemies to the enemy manager
        enemyManager.addEnemy(new Enemy(200, 400, 2)); // Enemy starting at (200, 400) moving right
        enemyManager.addEnemy(new Enemy(600, 300, -2)); // Enemy starting at (600, 300) moving left

        // Initialize PlayerController
        playerController = new PlayerController(player, mapCollisionMask, playerSpeed);

        long hitCooldown = 1000;
        collisionManager = new CollisionManager(player, enemyManager, heartBar, collisionSound, hitCooldown);

        // Add the PlayerController as KeyListener
        this.addKeyListener(playerController);

        // Initialize Renderer
        renderer = new Renderer(this);

        // Initialize menu panel
        menuPanel = new MenuPanel(this);
        this.addMouseListener(menuPanel); // Add mouse listener for menu interactions
    }

    public void startGame() {
        timer.start();
    }

    public void resetGame() {
        // Reset game components
        player.setPosition(400, 500);
        heartBar.reset();
        enemyManager.resetEnemies();
        collisionManager.reset();
        gameState = GameState.PLAYING;
        // Ensure focus is on GamePanel to receive key events
        this.requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gameState == GameState.MENU) {
            menuPanel.draw((Graphics2D) g);
        } else if (gameState == GameState.PLAYING) {
            renderer.render((Graphics2D) g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState == GameState.PLAYING) {
            playerController.updatePlayerSpeed();
            player.update();

            enemyManager.update();

            collisionManager.checkCollisions();

            // Check if player is dead
            if (heartBar.isEmpty()) {
                deathSound.play();
                gameState = GameState.MENU;
            }

            camera.update(player.getX(), player.getY());
        }

        repaint();
    }

    public void drawUI(Graphics2D g2d) {
        // Draw the heart bar
        heartBar.draw(g2d);

        int buttonX = getWidth() - muteButton.width - 10;
        int buttonY = 10;
        muteButton.x = buttonX;
        muteButton.y = buttonY;

        // Draw the mute button
        muteButton.draw(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameState == GameState.MENU) {
            menuPanel.mouseClicked(e);
        } else if (gameState == GameState.PLAYING) {
            int mouseX = e.getX();
            int mouseY = e.getY();

            if (muteButton.isClicked(mouseX, mouseY)) {
                muteButton.toggleMute();
                if (muteButton.isMuted()) {
                    if (bgMusic != null) {
                        bgMusic.stop();
                    }
                } else {
                    if (bgMusic != null) {
                        bgMusic.play();
                    }
                }
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

    // Getter methods for Renderer
    public Player getPlayer() {
        return player;
    }

    public Camera getCamera() {
        return camera;
    }

    public BufferedImage getMapImage() {
        return mapImage;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public HeartBar getHeartBar() {
        return heartBar;
    }

    public MuteButton getMuteButton() {
        return muteButton;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void stopGame() {
        timer.stop();
    }
}
