package game;

/***
 * Camera class handles following the player as they move through the map.
 */
public class Camera {

    private int x, y;

    public Camera(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update(Player player) {
        // Camera follows player, but stops at map edges
        x = player.getX() - 400; // Center player horizontally on screen
        y = player.getY() - 300; // Center player vertically on screen

        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > 1600 - 800) x = 1600 - 800; // Assuming 1600x1200 map and 800x600 screen
        if (y > 1200 - 600) y = 1200 - 600;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
