
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeGame1 extends JPanel implements ActionListener, KeyListener {
    private Snake1 snake;
    private Feed1 feed;
    private Timer gameLoop;
    private boolean gameOver = false;
    private int tileSize = 24;
    private int boardWidth;
    private int boardHeight;

    private int initialDelay = 100;
    private int speedIncrement = 10;
    private int minDelay = 50;

    public SnakeGame1(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snake = new Snake1(5, 5);
        feed = new Feed1(boardWidth, boardHeight);

        gameLoop = new Timer(initialDelay, this);
        gameLoop.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g) {

        for (int i = 0; i < boardWidth / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
        }

        g.setColor(Color.red);
        Tile1 food = feed.getFood();
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        g.setColor(Color.green);
        Tile1 head = snake.getHead();
        g.fill3DRect(head.x * tileSize, head.y * tileSize, tileSize, tileSize, true);

        g.setColor(Color.green.darker());
        for (Tile1 bodyPart : snake.getBody()) {
            g.fill3DRect(bodyPart.x * tileSize, bodyPart.y * tileSize, tileSize, tileSize, true);
        }

        if (gameOver) {
            g.setColor(Color.red);
            g.drawString("Game Over! Your score was: " + snake.getBody().size(), tileSize - 16, tileSize);
        } else {
            g.drawString("Score: " + snake.getBody().size(), tileSize - 16, tileSize);
        }
    }

    public void move() {
        if (snake.getHead().x == feed.getFood().x && snake.getHead().y == feed.getFood().y) {
            snake.grow();
            feed.placeFood(boardWidth, boardHeight);
            adjustGameSpeed();
        }

        snake.move();

        for (Tile1 part : snake.getBody()) {
            if (part.x == snake.getHead().x && part.y == snake.getHead().y) {
                gameOver = true;
            }
        }

        if (snake.getHead().x < 0 || snake.getHead().x >= boardWidth / tileSize ||
                snake.getHead().y < 0 || snake.getHead().y >= boardHeight / tileSize) {
            gameOver = true;
        }
    }

    private void adjustGameSpeed() {
        int newDelay = initialDelay - (snake.getBody().size() * speedIncrement);
        if (newDelay < minDelay) {
            newDelay = minDelay;
        }

        gameLoop.setDelay(newDelay);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            repaint();
        } else {
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && snake.getHead().y > 0) {
            snake.setVelocity(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getHead().y < boardHeight / tileSize - 1) {
            snake.setVelocity(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getHead().x > 0) {
            snake.setVelocity(-1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getHead().x < boardWidth / tileSize - 1) {
            snake.setVelocity(1, 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
