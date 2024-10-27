package game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * GamePanel is the main panel for the game, handling rendering, updates, and
 * user input.
 * It initializes game components, manages game state, and processes user
 * interactions.
 */
public class GamePanel extends JPanel implements ActionListener {
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
    private AudioPlayer collisionSound;
    private AudioPlayer deathSound;
    private PlayerController playerController;
    private EnemyManager enemyManager;
    private PaintingManager paintingManager;
    private Renderer renderer;
    private GameState gameState;
    private MenuPanel menuPanel;
    private Button closeButton;
    private List<Bubble> bubbles;
    private Font winningFont;

    /**
     * Constructs a new GamePanel, initializes game state, components, resources,
     * and listeners.
     */
    public GamePanel() {
        setFocusable(true);
        requestFocusInWindow();

        initializeGameState();
        initializeComponents();
        initializeResources();
        initializeListeners();
    }

    private void initializeGameState() {
        gameState = GameState.MENU;
    }

    private void initializeComponents() {
        player = new Player(400, 500);
        timer = new Timer(30, this);
        heartBar = new HeartBar(3);
        muteButton = new MuteButton(0, 0);
        enemyManager = new EnemyManager();
        paintingManager = new PaintingManager();
        bubbles = new ArrayList<>();
        winningFont = new Font("Arial", Font.BOLD, 36);
        closeButton = new Button(0, 0, 200, 80, "/images/button_play.png");

        long hitCooldown = 1000; // Example cooldown in milliseconds
        collisionManager = new CollisionManager(
                player, enemyManager, heartBar, collisionSound, hitCooldown);
    }

    private void initializeResources() {
        collisionSound = new AudioPlayer("/sounds/hit.wav");
        deathSound = new AudioPlayer("/sounds/death.wav");
        try {
            mapImage = ImageIO.read(getClass().getResourceAsStream("/images/cave_map.png"));
            mapCollisionMask = new CollisionMaskGenerator("/images/collision_map.png");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading map images.");
        }
        int worldWidth = mapImage.getWidth();
        int worldHeight = mapImage.getHeight();
        int screenWidth = 800;
        int screenHeight = 600;
        camera = new Camera(worldWidth, worldHeight, screenWidth, screenHeight);
        renderer = new Renderer(this);
    }

    private void initializeListeners() {
        playerController = new PlayerController(player, mapCollisionMask, playerSpeed);
        addKeyListener(playerController);
        menuPanel = new MenuPanel(this);
        addMouseListener(menuPanel);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClicked(e);
            }
        });
    }

    public void startGame() {
        timer.start();
    }

    /**
     * Resets the game state to its initial conditions.
     * This includes resetting the player's position, heart bar, enemies, collision
     * manager,
     * painting manager, and clearing bubbles. The game state is set to PLAYING and
     * the timer is started.
     */
    public void resetGame() {
        player.setPosition(400, 500);
        heartBar.reset();
        enemyManager.resetEnemies();
        collisionManager.reset();
        paintingManager.reset();
        bubbles.clear();
        gameState = GameState.PLAYING;
        timer.start();
        if (bgMusic != null) {
            bgMusic.play();
        }
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        switch (gameState) {
            case MENU:
                menuPanel.draw(g2d);
                break;
            case PLAYING:
                renderer.render(g2d);
                break;
            case WINNING:
                renderer.render(g2d);
                drawWinningOverlay(g2d);
                break;
            default:
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (gameState) {
            case PLAYING:
                updateGame();
                break;
            case WINNING:
                updateBubbles();
                break;
            default:
                break;
        }
        repaint();
    }

    private void updateGame() {
        playerController.updatePlayerSpeed();
        player.update();
        enemyManager.update();
        collisionManager.checkCollisions();
        paintingManager.update(player);

        if (heartBar.isEmpty()) {
            deathSound.play();
            gameState = GameState.MENU;
        }

        if (paintingManager.allPaintingsCollected()) {
            if (bgMusic != null) {
                bgMusic.stop();
            }
            gameState = GameState.WINNING;
        }

        camera.update(player.getX(), player.getY());
    }

    private void drawWinningOverlay(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        drawBubbles(g2d);

        String message = "Congratulations! You collected all the paintings!";
        g2d.setFont(winningFont);
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(message);
        int textX = (getWidth() - textWidth) / 2;
        int textY = getHeight() / 2;
        g2d.setColor(Color.WHITE);
        g2d.drawString(message, textX, textY);

        int buttonX = (getWidth() - closeButton.getWidth()) / 2;
        int buttonY = textY + 50;
        closeButton.setPosition(buttonX, buttonY);
        closeButton.draw(g2d);
    }

    private void updateBubbles() {
        if (bubbles.size() < 20) {
            bubbles.add(new Bubble(getWidth(), getHeight()));
        }
        for (Bubble bubble : bubbles) {
            bubble.update();
        }
    }

    private void drawBubbles(Graphics2D g2d) {
        for (Bubble bubble : bubbles) {
            bubble.draw(g2d);
        }
    }

    private void handleMouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        switch (gameState) {
            case MENU:
                menuPanel.mouseClicked(e);
                break;
            case PLAYING:
                if (muteButton.isClicked(mouseX, mouseY)) {
                    muteButton.toggleMute();
                    if (muteButton.isMuted() && bgMusic != null) {
                        bgMusic.stop();
                    } else if (bgMusic != null) {
                        bgMusic.play();
                    }
                }
                break;
            case WINNING:
                if (closeButton.isClicked(mouseX, mouseY)) {
                    System.exit(0);
                }
                break;
            default:
                break;
        }
    }

    public void setBackgroundMusic(AudioPlayer bgMusic) {
        this.bgMusic = bgMusic;
    }

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

    public PaintingManager getPaintingManager() {
        return paintingManager;
    }

    public void drawUI(Graphics2D g2d) {
        new UIDrawer(heartBar, muteButton, paintingManager).drawUI(g2d, getWidth());
    }
}
