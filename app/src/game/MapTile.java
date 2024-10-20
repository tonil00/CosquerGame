package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/***
 * MapTile class represents one section of the cave map.
 */
public class MapTile {
    
    private List<Obstacle> obstacles;
    private List<Painting> paintings;
    private List<Enemy> enemies;
    private List<WaterCurrent> currents;
    private Color backgroundColor;

    public MapTile(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.obstacles = new ArrayList<>();
        this.paintings = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.currents = new ArrayList<>(); // Initialize currents list
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

        // Draw all obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }

        // Draw all paintings
        for (Painting painting : paintings) {
            painting.draw(g);
        }

        // Draw all enemies
        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        // Draw all water currents
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
