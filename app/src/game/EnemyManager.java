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
        Enemy enemy1 = new Enemy(200, 100, 2);
        Enemy enemy2 = new Enemy(600, 210, -2);
        Enemy enemy3 = new Enemy(100, 340, 3);
        Enemy enemy4 = new Enemy(800, 540, -3);
        Enemy enemy5 = new Enemy(300, 620, -1);
        Enemy enemy6 = new Enemy(1000, 800, 1);

        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
        enemies.add(enemy4);
        enemies.add(enemy5);
        enemies.add(enemy6);

        initialEnemies.add(enemy1);
        initialEnemies.add(enemy2);
        initialEnemies.add(enemy3);
        initialEnemies.add(enemy4);
        initialEnemies.add(enemy5);
        initialEnemies.add(enemy6);
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
