package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    private final Player player;

    public InputHandler(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            player.setSpeedX(-5);  // Move left
        }
        if (key == KeyEvent.VK_RIGHT) {
            player.setSpeedX(5);   // Move right
        }
        if (key == KeyEvent.VK_UP) {
            player.setSpeedY(-5);  // Move up
        }
        if (key == KeyEvent.VK_DOWN) {
            player.setSpeedY(5);   // Move down
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            player.setSpeedX(0);  // Stop horizontal movement
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            player.setSpeedY(0);  // Stop vertical movement
        }
    }
}
