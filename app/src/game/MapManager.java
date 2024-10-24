package game;

import java.awt.Graphics;
import java.util.List;

public class MapManager {

    private List<MapTile> mapTiles;

    public MapManager(List<MapTile> mapTiles) {
        this.mapTiles = mapTiles;
    }

    public void draw(Graphics g, int cameraX, int cameraY) {
        for (MapTile tile : mapTiles) {
            tile.draw(g, cameraX, cameraY);
        }
    }

    public List<MapTile> getMapTiles() {
        return mapTiles;
    }

    public void setMapTiles(List<MapTile> mapTiles) {
        this.mapTiles = mapTiles;
    }
}
