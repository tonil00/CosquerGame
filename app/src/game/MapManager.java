package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/***
 * MapManager manages the map tiles, obstacles, enemies, and paintings.
 */
public class MapManager {

    private final List<MapTile> tiles;

    public MapManager() {
        tiles = new ArrayList<>();
    }

    public void addTile(MapTile tile) {
        tiles.add(tile);
    }

    public void updateTileTransition(Player player) {
        // Logic to transition between tiles can be added here.
    }

    public void drawCurrentTile(Graphics g) {
        for (MapTile tile : tiles) {
            tile.draw(g);
        }
    }

    // Methods to retrieve current entities for collision and interaction
    public List<Obstacle> getCurrentObstacles() {
        List<Obstacle> allObstacles = new ArrayList<>();
        for (MapTile tile : tiles) {
            allObstacles.addAll(tile.getObstacles());
        }
        return allObstacles;
    }

    public List<WaterCurrent> getCurrentCurrents() {
        List<WaterCurrent> allCurrents = new ArrayList<>();
        for (MapTile tile : tiles) {
            allCurrents.addAll(tile.getCurrents());
        }
        return allCurrents;
    }

    public List<Enemy> getCurrentEnemies() {
        List<Enemy> allEnemies = new ArrayList<>();
        for (MapTile tile : tiles) {
            allEnemies.addAll(tile.getEnemies());
        }
        return allEnemies;
    }

    public List<Painting> getCurrentPaintings() {
        List<Painting> allPaintings = new ArrayList<>();
        for (MapTile tile : tiles) {
            allPaintings.addAll(tile.getPaintings());
        }
        return allPaintings;
    }
}
