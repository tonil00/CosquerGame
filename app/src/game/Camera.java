package game;

public class Camera {
    private final Player player;
    private int screenWidth, screenHeight;
    private int offsetX, offsetY;

    // Define the imaginary red rectangle's size (e.g., middle 60% of the screen)
    private final int rectWidth, rectHeight;
    private final int rectX, rectY;

    public Camera(Player player, int screenWidth, int screenHeight) {
        this.player = player;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        // Define the boundaries of the imaginary red rectangle
        this.rectWidth = (int) (screenWidth * 0.6);  // 60% of the screen width
        this.rectHeight = (int) (screenHeight * 0.6); // 60% of the screen height
        this.rectX = (screenWidth - rectWidth) / 2;   // Center the rectangle horizontally
        this.rectY = (screenHeight - rectHeight) / 2; // Center the rectangle vertically
    }

    public void update() {
        // Check if the player (Fish) is outside the imaginary red rectangle
        if (player.getX() < rectX) {
            offsetX -= player.getSpeedX(); // Move camera left
        } else if (player.getX() + player.getWidth() > rectX + rectWidth) {
            offsetX += player.getSpeedX(); // Move camera right
        }

        if (player.getY() < rectY) {
            offsetY -= player.getSpeedY(); // Move camera up
        } else if (player.getY() + player.getHeight() > rectY + rectHeight) {
            offsetY += player.getSpeedY(); // Move camera down
        }
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }
}
