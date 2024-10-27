package game;

import java.awt.Rectangle;
import java.util.List;

/**
 * Manages collision detection between the player and enemies.
 */
public class CollisionManager {
    private Player player;
    private EnemyManager enemyManager;
    private HeartBar heartBar;
    private AudioPlayer collisionSound;
    private long lastHitTime;
    private final long hitCooldown;

    /**
     * Constructs a CollisionManager.
     *
     * @param player the player object
     * @param enemyManager the manager for enemies
     * @param heartBar the heart bar for player health
     * @param collisionSound the audio player for collision sounds
     * @param hitCooldown the cooldown time between hits
     */
    public CollisionManager(Player player, EnemyManager enemyManager,
            HeartBar heartBar, AudioPlayer collisionSound,
            long hitCooldown) {
        this.player = player;
        this.enemyManager = enemyManager;
        this.heartBar = heartBar;
        this.collisionSound = new AudioPlayer("/sounds/hit.wav");
        this.hitCooldown = hitCooldown;
        this.lastHitTime = 0;
    }

    /**
     * Checks for collisions between the player and enemies.
     * If a collision is detected and the player can take damage,
     * it handles the collision.
     */
    public void checkCollisions() {
        if (!canTakeDamage()) {
            return;
        }

        Rectangle playerBounds = player.getBounds();
        List<Enemy> enemies = enemyManager.getEnemies();

        for (Enemy enemy : enemies) {
            if (playerBounds.intersects(enemy.getBounds())) {
                handlePlayerEnemyCollision();
                break;
            }
        }
    }

    private boolean canTakeDamage() {
        long currentTime = System.currentTimeMillis();
        return currentTime - lastHitTime >= hitCooldown;
    }

    private void handlePlayerEnemyCollision() {
        // Update the last hit time for cooldown
        lastHitTime = System.currentTimeMillis();

        // Play collision sound
        collisionSound.play();

        // Reduce player health
        heartBar.loseHeart();
        System.out.println("Collision detected with enemy!");
    }

    public void reset() {
        lastHitTime = 0;
    }
}
