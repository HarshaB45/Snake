
import java.util.ArrayList;

public class Snake1 {
    private ArrayList<Tile1> body;
    private Tile1 head;
    private int velocityX;
    private int velocityY;

    public Snake1(int initialX, int initialY) {
        head = new Tile1(initialX, initialY);
        body = new ArrayList<>();
        velocityX = 0;
        velocityY = 0;
    }

    public Tile1 getHead() {
        return head;
    }

    public ArrayList<Tile1> getBody() {
        return body;
    }

    public void setVelocity(int x, int y) {
        velocityX = x;
        velocityY = y;
    }

    public void move() {
        // Move the snake's body
        for (int i = body.size() - 1; i >= 0; i--) {
            Tile1 snakePart = body.get(i);
            if (i == 0) {
                snakePart.x = head.x;
                snakePart.y = head.y;
            } else {
                Tile1 prevSnakePart = body.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }
        head.x += velocityX;
        head.y += velocityY;
    }

    public void grow() {
        body.add(new Tile1(head.x, head.y));
    }
}
