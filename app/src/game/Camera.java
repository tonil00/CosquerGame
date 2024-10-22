package game;

public class Camera {

    private int x;
    private int y;
    private int worldWidth;
    private int worldHeight;
    private int screenWidth;
    private int screenHeight;

    /**
     * Constructor for the camera.
     * 
     * @param worldWidth   The width of the entire game world.
     * @param worldHeight  The height of the entire game world.
     * @param screenWidth  The width of the screen (viewport).
     * @param screenHeight The height of the screen (viewport).
     */
    public Camera(int worldWidth, int worldHeight, int screenWidth, int screenHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.x = 0;
        this.y = 0;
    }

    /**
     * Update the camera's position to follow the player.
     * 
     * @param playerX The player's x-coordinate.
     * @param playerY The player's y-coordinate.
     */
    public void update(int playerX, int playerY) {
        // Center the camera on the player
        x = playerX - screenWidth / 2;
        y = playerY - screenHeight / 2;

        // Keep the camera within the world bounds
        if (x < 0) { // Left edge
            x = 0;
        }
        if (y < 0) { // Top edge
            y = 0;
        }
        if (x + screenWidth > worldWidth) { // Right edge
            x = worldWidth - screenWidth;
        }
        if (y + screenHeight > worldHeight) { // Bottom edge
            y = worldHeight - screenHeight;
        }
    }

    // Getters for camera position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
