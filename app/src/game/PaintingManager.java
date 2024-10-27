package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection and state of paintings in the game.
 */
public class PaintingManager {
    private List<Painting> paintings;
    private int totalPaintings;

    /**
     * Constructs a new PaintingManager and initializes the paintings.
     */
    public PaintingManager() {
        paintings = new ArrayList<>();
        initializePaintings();
        totalPaintings = paintings.size();
    }

    private void initializePaintings() {
        paintings.add(new Painting(564, 120, "/images/painting1.png"));
        paintings.add(new Painting(734, 932, "/images/painting2.png"));
        paintings.add(new Painting(528, 908, "/images/painting3.png"));
        paintings.add(new Painting(524, 608, "/images/painting4.png"));
        paintings.add(new Painting(96, 872, "/images/painting5.png"));
    }

    /**
     * Updates the state of the paintings based on the player's position.
     * If the player intersects with a painting, it is marked as collected.
     *
     * @param player the player whose position is checked against the paintings
     */
    public void update(Player player) {
        for (Painting painting : paintings) {
            if (!painting.isCollected() && player.getBounds().intersects(painting.getBounds())) {
                painting.collect();
                System.out.println("Painting collected!");
            }
        }
    }

    /**
     * Draws all the paintings on the screen.
     *
     * @param g the Graphics2D object used for drawing
     */
    public void draw(Graphics2D g) {
        for (Painting painting : paintings) {
            painting.draw(g);
        }
    }

    /**
     * Returns the number of paintings that have been collected.
     *
     * @return the count of collected paintings
     */
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

    /**
     * Resets the state of all paintings to their initial state.
     */
    public void reset() {
        for (Painting painting : paintings) {
            painting.reset();
        }
    }
}
