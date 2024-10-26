package game;

import java.awt.Rectangle;
import java.util.List;

public class CollisionManager {
    private Player player;
    private EnemyManager enemyManager;
    private HeartBar heartBar;
    private AudioPlayer collisionSound;
    private long lastHitTime;
    private final long hitCooldown;

    public CollisionManager(Player player, EnemyManager enemyManager,
            HeartBar heartBar, AudioPlayer collisionSound,
            long hitCooldown) {
        this.player = player;
        this.enemyManager = enemyManager;
        this.heartBar = heartBar;
        this.collisionSound = collisionSound;
        this.hitCooldown = hitCooldown;
        this.lastHitTime = 0;
    }

    public void checkCollisions() {
        if (!canTakeDamage())
            return;

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
