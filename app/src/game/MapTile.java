package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/***
 * MapTile represents one tile of the game map. It contains obstacles, enemies, paintings, and water currents.
 */
public class MapTile {

    private final Color backgroundColor;
    private final List<Obstacle> obstacles;
    private final List<Enemy> enemies;
    private final List<Painting> paintings;
    private final List<WaterCurrent> currents;

    public MapTile(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.obstacles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.paintings = new ArrayList<>();
        this.currents = new ArrayList<>();
    }

    public void draw(Graphics g) {
        // Fill the background with the tile's color
        g.setColor(backgroundColor);
        g.fillRect(0, 0, 800, 600); // Assuming fixed screen size for simplicity

        // Draw all objects
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        for (Painting painting : paintings) {
            painting.draw(g);
        }

        for (WaterCurrent current : currents) {
            current.draw(g);
        }
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addPainting(Painting painting) {
        paintings.add(painting);
    }

    public void addCurrent(WaterCurrent current) {
        currents.add(current);
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    public List<WaterCurrent> getCurrents() {
        return currents;
    }
}
