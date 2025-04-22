import java.util.Random;

public class Feed1 {
    private Tile1 food;
    private Random random;

    public Feed1(int boardWidth, int boardHeight) {
        random = new Random();
        placeFood(boardWidth, boardHeight);
    }

    public Tile1 getFood() {
        return food;
    }

    public void placeFood(int boardWidth, int boardHeight) {
        int x = random.nextInt(boardWidth / 24);
        int y = random.nextInt(boardHeight / 24);
        food = new Tile1(x, y);
    }
}
