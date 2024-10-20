package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/***
 * MapTile represents a section of the map, containing enemies, obstacles, currents, and paintings.
 */
public class MapTile {

    private final Color backgroundColor;
    private final List<Obstacle> obstacles;
    private final List<WaterCurrent> currents;
    private final List<Enemy> enemies;
    private final List<Painting> paintings;

    public MapTile(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.obstacles = new ArrayList<>();
        this.currents = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.paintings = new ArrayList<>();
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public void addCurrent(WaterCurrent current) {
        currents.add(current);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addPainting(Painting painting) {
        paintings.add(painting);
    }

    public void draw(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, 5000, 3000);

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
        for (WaterCurrent current : currents) {
            current.draw(g);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
        for (Painting painting : paintings) {
            painting.draw(g);
        }
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<WaterCurrent> getCurrents() {
        return currents;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }
}
