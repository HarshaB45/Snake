public class Movement2 {
    private Snake2 snake1;
    private Snake2 snake2;
    private Feed2 feed;
    private int boardWidth;
    private int boardHeight;
    private int tileSize;
    private int dimensions;

    public Movement2(Snake2 snake1, Snake2 snake2, Feed2 feed, int boardWidth, int boardHeight, int tileSize,
            int dimensions) {
        this.snake1 = snake1;
        this.snake2 = snake2;
        this.feed = feed;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.tileSize = tileSize;
        this.dimensions = dimensions;
    }

    public boolean moveSnakes() {
        boolean gameOver = false;

        // Check if Snake 1 collides with food
        if (snake1.getHead().x == feed.getFood().x && snake1.getHead().y == feed.getFood().y) {
            snake1.grow();
            feed.placeFood(boardWidth, boardHeight, dimensions);
        }

        // Check if Snake 2 collides with food
        if (snake2.getHead().x == feed.getFood().x && snake2.getHead().y == feed.getFood().y) {
            snake2.grow();
            feed.placeFood(boardWidth, boardHeight, dimensions);
        }

        // Move both snakes
        snake1.move();
        snake2.move();

        // Check for wall collisions for Snake 1
        if (snake1.getHead().x < 0 || snake1.getHead().x >= boardWidth / tileSize ||
                snake1.getHead().y < 0 || snake1.getHead().y >= boardHeight / tileSize) {
            gameOver = true;
        }

        // Check for wall collisions for Snake 2
        if (snake2.getHead().x < 0 || snake2.getHead().x >= boardWidth / tileSize ||
                snake2.getHead().y < 0 || snake2.getHead().y >= boardHeight / tileSize) {
            gameOver = true;
        }

        return gameOver;
    }
}
