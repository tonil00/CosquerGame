package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class PaintingManager {
    private List<Painting> paintings;
    private int totalPaintings;

    public PaintingManager() {
        paintings = new ArrayList<>();
        initializePaintings();
        totalPaintings = paintings.size();
    }

    private void initializePaintings() {
        paintings.add(new Painting(100, 200, "/images/painting1.png"));
        paintings.add(new Painting(300, 400, "/images/painting2.png"));
        // paintings.add(new Painting(500, 150, "/images/painting3.png"));
        // paintings.add(new Painting(700, 300, "/images/painting4.png"));
        // paintings.add(new Painting(200, 500, "/images/painting5.png"));
    }

    public void update(Player player) {
        for (Painting painting : paintings) {
            if (!painting.isCollected() && player.getBounds().intersects(painting.getBounds())) {
                painting.collect();
                System.out.println("Painting collected!");
            }
        }
    }

    public void draw(Graphics2D g) {
        for (Painting painting : paintings) {
            painting.draw(g);
        }
    }

    public int getCollectedCount() {
        int collectedCount = 0;
        for (Painting painting : paintings) {
            if (painting.isCollected()) {
                collectedCount++;
            }
        }
        return collectedCount;
    }

    public int getTotalPaintings() {
        return totalPaintings;
    }

    public boolean allPaintingsCollected() {
        return getCollectedCount() == totalPaintings;
    }

    public void reset() {
        for (Painting painting : paintings) {
            painting.reset();
        }
    }
}
