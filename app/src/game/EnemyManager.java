package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the enemies in the game, including their creation, updating, drawing, and resetting.
 */

public class EnemyManager {
    private List<Enemy> enemies;
    private List<Enemy> initialEnemies;

    /**
     * Constructs an EnemyManager and initializes the enemy lists with initial enemies.
     */
    public EnemyManager() {
        enemies = new ArrayList<>();
        initialEnemies = new ArrayList<>();

        // Add initial enemies to both lists
        Enemy enemy1 = new Enemy(200, 400, 2);
        Enemy enemy2 = new Enemy(600, 300, -2);

        enemies.add(enemy1);
        enemies.add(enemy2);

        initialEnemies.add(enemy1);
        initialEnemies.add(enemy2);
    }

    /**
     * Adds an enemy to the list of enemies and the initial enemies list.
     *
     * @param enemy the enemy to be added
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        initialEnemies.add(new Enemy(enemy));
    }

    /**
     * Updates the state of all enemies.
     */
    public void update() {
        for (Enemy enemy : enemies) {
            enemy.update();
        }
    }

    /**
     * Draws all enemies on the screen.
     *
     * @param g2d the graphics context used for drawing
     */
    public void draw(Graphics2D g2d) {
        for (Enemy enemy : enemies) {
            enemy.draw(g2d);
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Resets the enemies to their initial state.
     */
    public void resetEnemies() {
        enemies.clear();
        for (Enemy enemy : initialEnemies) {
            enemies.add(new Enemy(enemy));
        }
    }
}
