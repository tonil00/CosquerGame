package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The MuteButton class represents a button that toggles the mute state of the game.
 */
public class MuteButton {
    private boolean muted;
    private Image unmutedImage;
    private Image mutedImage;
    public int x;
    public int y;
    public int width;
    private int height;

    /**
     * Constructor to initialize the mute button.
     *
     * @param x The x-coordinate of the button.
     * @param y The y-coordinate of the button.
     */
    public MuteButton(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 32;  // Adjust based on your image size
        this.height = 32; // Adjust based on your image size
        this.muted = false; // Start unmuted

        // Load button images
        try {
            unmutedImage = ImageIO.read(getClass().getResourceAsStream("/images/unmuted.png"));
            mutedImage = ImageIO.read(getClass().getResourceAsStream("/images/muted.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading mute button images.");
        }
    }

    /**
     * Toggle the mute state.
     */
    public void toggleMute() {
        muted = !muted;
    }

    /**
     * Draw the mute button on the screen.
     *
     * @param g The Graphics2D object used for drawing.
     */
    public void draw(Graphics2D g) {
        Image buttonImage = muted ? mutedImage : unmutedImage;
        g.drawImage(buttonImage, x, y, width, height, null);
    }

    /**
     * Check if the mute button was clicked.
     *
     * @param mouseX The x-coordinate of the mouse click.
     * @param mouseY The y-coordinate of the mouse click.
     * @return True if clicked, false otherwise.
     */
    public boolean isClicked(int mouseX, int mouseY) {
        Rectangle buttonBounds = new Rectangle(x, y, width, height);
        return buttonBounds.contains(mouseX, mouseY);
    }

    /**
     * Check if the music is muted.
     *
     * @return True if muted, false otherwise.
     */
    public boolean isMuted() {
        return muted;
    }
}
