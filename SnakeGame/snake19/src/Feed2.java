
import java.util.Random;

public class Feed2 {
    private Tile2 food;
    private Random random;

    public Feed2(int boardWidth, int boardHeight, int dimensions) {
        random = new Random();
        placeFoodAtCenter(boardWidth, boardHeight, dimensions); // Place food at center initially
    }

    public Tile2 getFood() {
        return food;
    }

    public void placeFoodAtCenter(int boardWidth, int boardHeight, int dimensions) {
        int centerX = dimensions / 2;
        int centerY = dimensions / 2;
        food = new Tile2(centerX, centerY);
    }

    // Randomly place food on the grid after the first one
    public void placeFood(int boardWidth, int boardHeight, int dimensions) {
        int x = random.nextInt(dimensions);
        int y = random.nextInt(dimensions);
        food = new Tile2(x, y);
    }
}
