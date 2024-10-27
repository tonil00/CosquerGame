package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The PlayerController class handles the player's movement and key events.
 */
public class PlayerController implements KeyListener {
    private Player player;
    private CollisionMaskGenerator collisionMask;
    private int playerSpeed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private double speedX, speedY;
    private double acceleration = 0.3;
    private double deceleration = 0.1;
    private double maxSpeed = 3.0;

    /**
     * Constructs a PlayerController with the specified player, collision mask, and player speed.
     *
     * @param player the player to control
     * @param collisionMask the collision mask generator
     * @param playerSpeed the speed of the player
     */
    public PlayerController(Player player, CollisionMaskGenerator collisionMask, int playerSpeed) {
        this.player = player;
        this.collisionMask = collisionMask;
        this.playerSpeed = playerSpeed;
    }

    /**
     * Updates the player's speed based on the current key presses.
     */
    public void updatePlayerSpeed() {
        int nextX = player.getX();
        int nextY = player.getY();
    
        // Handle acceleration
        if (upPressed) {
            speedY -= acceleration;
        } else if (downPressed) {
            speedY += acceleration;
        } else {
            // Decelerate when no key is pressed
            if (speedY > 0) speedY -= deceleration;
            if (speedY < 0) speedY += deceleration;
        }
    
        if (leftPressed) {
            speedX -= acceleration;
        } else if (rightPressed) {
            speedX += acceleration;
        } else {
            // Decelerate when no key is pressed
            if (speedX > 0) speedX -= deceleration;
            if (speedX < 0) speedX += deceleration;
        }
    
        // Cap the speed at the maximum value
        if (speedX > maxSpeed) speedX = maxSpeed;
        if (speedX < -maxSpeed) speedX = -maxSpeed;
        if (speedY > maxSpeed) speedY = maxSpeed;
        if (speedY < -maxSpeed) speedY = -maxSpeed;
    
        nextX += speedX;
        nextY += speedY;
    
        // Check if the player can move to the next position
        if (player.canMove(nextX, nextY, collisionMask)) {
            player.setSpeed(speedX, speedY);
        } else {
            player.setSpeed(0.0, 0.0);
        }
    }

    // Implement KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_DOWN -> downPressed = true;
            case KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_RIGHT -> rightPressed = true;
            default -> {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_DOWN -> downPressed = false;
            case KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_RIGHT -> rightPressed = false;
            default -> {
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}
