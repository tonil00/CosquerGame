package game;

public class Camera {

    private int x;
    private int y;
    private int worldWidth;
    private int worldHeight;
    private int screenWidth;
    private int screenHeight;
    private final double zoom;

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
        this.zoom = 2.0; // Adjust the zoom factor
    }

    /**
     * Update the camera's position to follow the player.
     * 
     * @param playerX The player's x-coordinate.
     * @param playerY The player's y-coordinate.
     */
    public void update(int playerX, int playerY) {
        // Calculate the viewport size based on zoom
        int viewportWidth = (int) (screenWidth / zoom);
        int viewportHeight = (int) (screenHeight / zoom);

        // Desired camera position to center on the player
        int targetX = playerX - viewportWidth / 2 + 16; // Adjust for player width
        int targetY = playerY - viewportHeight / 2 + 16; // Adjust for player height

        // Smoothly move towards the target position
        x += (targetX - x) * 0.1; // Adjust the factor for smoothing
        y += (targetY - y) * 0.1;

        // Keep the camera within the world bounds
        x = Math.max(0, Math.min(x, worldWidth - viewportWidth));
        y = Math.max(0, Math.min(y, worldHeight - viewportHeight));

        
    }

    // Getters for camera position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZoom() {
        return (int) zoom;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
