package game;

/***
 * Camera class to handle following the player and transitions.
 */
public class Camera {
    private int x, y;
    private final int width = 800;  // Screen width
    private final int height = 600; // Screen height

    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(Player player) {
        // Center camera on player, but ensure it stays within map bounds
        x = player.getX() - width / 2;
        y = player.getY() - height / 2;

        // Example map limits
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > 1600 - width) x = 1600 - width; // Assuming map width is 1600
        if (y > 1600 - height) y = 1600 - height; // Assuming map height is 1600
    }

    // Get camera position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
