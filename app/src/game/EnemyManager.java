package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private List<Enemy> enemies;
    private List<Enemy> initialEnemies;

    public EnemyManager() {
        enemies = new ArrayList<>();
        initialEnemies = new ArrayList<>();
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        initialEnemies.add(new Enemy(enemy));
    }

    public void update() {
        for (Enemy enemy : enemies) {
            enemy.update();
        }
    }

    public void draw(Graphics2D g2d) {
        for (Enemy enemy : enemies) {
            enemy.draw(g2d);
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void resetEnemies() {
        enemies.clear();
        for (Enemy enemy : initialEnemies) {
            enemies.add(new Enemy(enemy)); // Add fresh copies of initial enemies
        }
    }
}
