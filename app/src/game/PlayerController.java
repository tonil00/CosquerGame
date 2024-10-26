package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {
    private Player player;
    private CollisionMaskGenerator collisionMask;
    private int playerSpeed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    public PlayerController(Player player, CollisionMaskGenerator collisionMask, int playerSpeed) {
        this.player = player;
        this.collisionMask = collisionMask;
        this.playerSpeed = playerSpeed;
    }

    public void updatePlayerSpeed() {
        int speedX = 0;
        int speedY = 0;

        int nextX = player.getX();
        int nextY = player.getY();

        if (upPressed) {
            speedY = -playerSpeed;
        }
        if (downPressed) {
            speedY = playerSpeed;
        }
        if (leftPressed) {
            speedX = -playerSpeed;
        }
        if (rightPressed) {
            speedX = playerSpeed;
        }

        nextX += speedX;
        nextY += speedY;

        if (player.canMove(nextX, nextY, collisionMask)) {
            player.setSpeed(speedX, speedY);
        } else {
            player.setSpeed(0, 0);
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