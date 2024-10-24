package game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Background {

    private Image backgroundImage;

    public Background(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    public void draw(Graphics g, int cameraX, int cameraY) {
        g.drawImage(backgroundImage, -cameraX, -cameraY, null);
    }
}
