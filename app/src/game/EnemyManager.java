package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private List<Enemy> enemies;

    public EnemyManager() {
        enemies = new ArrayList<>();
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
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

}
