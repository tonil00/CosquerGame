package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/***
 * MapTile class represents one section of the cave map.
 */
public class MapTile {
    
    private final List<Obstacle> obstacles; // Marked as final
    private final List<Painting> paintings; // Marked as final
    private final List<Enemy> enemies; // Marked as final
    private final List<WaterCurrent> currents; // Marked as final
    private final Color backgroundColor; // Marked as final

    public MapTile(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.obstacles = new ArrayList<>();
        this.paintings = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.currents = new ArrayList<>();
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public void addPainting(Painting painting) {
        paintings.add(painting);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addCurrent(WaterCurrent current) {
        currents.add(current);
    }

    public void draw(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, 800, 600); // Draw background for the tile

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }

        for (Painting painting : paintings) {
            painting.draw(g);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        for (WaterCurrent current : currents) {
            current.draw(g);
        }
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<WaterCurrent> getCurrents() {
        return currents;
    }
}
