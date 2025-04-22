
import java.util.ArrayList;

public class Snake2 {
    private ArrayList<Tile2> body;
    private Tile2 head;
    private int velocityX;
    private int velocityY;

    public Snake2(int initialX, int initialY) {
        head = new Tile2(initialX, initialY);
        body = new ArrayList<>();
        velocityX = 0;
        velocityY = 0;
    }

    public Tile2 getHead() {
        return head;
    }

    public ArrayList<Tile2> getBody() {
        return body;
    }

    public void setVelocity(int x, int y) {
        velocityX = x;
        velocityY = y;
    }

    public void move() {
        // Move the snake's body
        for (int i = body.size() - 1; i >= 0; i--) {
            Tile2 snakePart = body.get(i);
            if (i == 0) {
                snakePart.x = head.x;
                snakePart.y = head.y;
            } else {
                Tile2 prevSnakePart = body.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // Move the snake's head
        head.x += velocityX;
        head.y += velocityY;
    }

    public void grow() {
        body.add(new Tile2(head.x, head.y));
    }

    public void reset(ChangeSpeed2 changeSpeed) {

        head = new Tile2(0, 0);

        body = new ArrayList<>(body.subList(0, body.size()));

        double newSpeedIncrement = changeSpeed.getSpeedIncrement() * 0.9;
        double minSpeedIncrement = 0.1;

        if (newSpeedIncrement < minSpeedIncrement) {
            newSpeedIncrement = minSpeedIncrement;
        }

        changeSpeed.setSpeedIncrement(newSpeedIncrement);
    }

}
