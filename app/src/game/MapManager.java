package game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/***
 * MapManager handles the transition between different map tiles as the player moves.
 */
public class MapManager {

    private final List<MapTile> tiles;
    private int currentTileIndex;

    public MapManager() {
        tiles = new ArrayList<>();
        currentTileIndex = 0; // Start at the first tile
    }

    public void addTile(MapTile tile) {
        tiles.add(tile);
    }

    public void drawCurrentTile(Graphics g) {
        if (currentTileIndex >= 0 && currentTileIndex < tiles.size()) {
            tiles.get(currentTileIndex).draw(g);
        }
    }

    public void updateTileTransition(Player player) {
        // Check if player is moving to the edge of the tile and needs to transition
        if (player.getX() < 0) {
            transitionToPreviousTile();
            player.setPosition(780, player.getY()); // Reposition player on right edge of previous tile
        } else if (player.getX() > 800) {
            transitionToNextTile();
            player.setPosition(20, player.getY()); // Reposition player on left edge of next tile
        }
    }

    private void transitionToPreviousTile() {
        if (currentTileIndex > 0) {
            currentTileIndex--;
        }
    }

    private void transitionToNextTile() {
        if (currentTileIndex < tiles.size() - 1) {
            currentTileIndex++;
        }
    }

    public List<Obstacle> getCurrentObstacles() {
        return tiles.get(currentTileIndex).getObstacles();
    }

    public List<WaterCurrent> getCurrentCurrents() {
        return tiles.get(currentTileIndex).getCurrents();
    }

    public List<Enemy> getCurrentEnemies() {
        return tiles.get(currentTileIndex).getEnemies();
    }

    public List<Painting> getCurrentPaintings() {
        return tiles.get(currentTileIndex).getPaintings();
    }
}
