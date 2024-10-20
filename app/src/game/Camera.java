package game;

/***
 * Camera class handles the camera movement within the game.
 */
public class Camera {

    private int x;
    private int y;

    public Camera(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update(Player player) {
        // Corrected methods for getting width and height
        x = player.getX() - 500 + player.getWidth() / 2;  // Adjusted for large map
        y = player.getY() - 375 + player.getHeight() / 2;

        // Prevent the camera from moving beyond the map's boundaries
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > 4500) x = 4500;  // Assuming a map width of 5000
        if (y > 2625) y = 2625;  // Assuming a map height of 3000
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
