
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeGame2 extends JPanel implements ActionListener, KeyListener {
    private Snake2 snake1;
    private Snake2 snake2;
    private Feed2 feed;
    private Timer gameLoop;
    private int tileSize;
    private int boardWidth;
    private int boardHeight;
    private int targetScore;
    @SuppressWarnings("unused")
    private int dimensions;

    private ChangeSpeed2 changeSpeed;
    private Movement2 movement;

    private Timer resetDelayTimer;
    private boolean resetInProgress;
    private Snake2 snakeToReset;

    public SnakeGame2(int boardWidth, int boardHeight, int tileSize, int targetScore, int dimensions) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.tileSize = tileSize;
        this.targetScore = targetScore;
        this.dimensions = dimensions;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.white);
        addKeyListener(this);
        setFocusable(true);

        snake1 = new Snake2(2, 2);
        snake2 = new Snake2(boardWidth / tileSize - 3, boardHeight / tileSize - 3);

        feed = new Feed2(boardWidth, boardHeight, dimensions);

        changeSpeed = new ChangeSpeed2(100, 10, 50);
        movement = new Movement2(snake1, snake2, feed, boardWidth, boardHeight, tileSize, dimensions);

        gameLoop = new Timer(100, this); // Start with initial speed
        gameLoop.start();

        // Initialize the reset delay timer
        resetDelayTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the snake after the delay
                if (resetInProgress && snakeToReset != null) {
                    snakeToReset.reset(changeSpeed);
                    resetInProgress = false; // Stop the delay timer
                }
            }
        });
        resetDelayTimer.setRepeats(false); // Timer runs only once
        resetInProgress = false;
        snakeToReset = null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Draw grid
        for (int i = 0; i < boardWidth / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);
            g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
        }

        // Draw food
        g.setColor(Color.red);
        Tile2 food = feed.getFood();
        g.fill3DRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize, true);

        // Draw Snake 1
        g.setColor(Color.green);
        Tile2 head1 = snake1.getHead();
        g.fill3DRect(head1.x * tileSize, head1.y * tileSize, tileSize, tileSize, true);

        // Draw Snake 1 body
        g.setColor(Color.green.darker());
        for (Tile2 bodyPart : snake1.getBody()) {
            g.fill3DRect(bodyPart.x * tileSize, bodyPart.y * tileSize, tileSize, tileSize, true);
        }

        // Draw Snake 2
        g.setColor(Color.blue);
        Tile2 head2 = snake2.getHead();
        g.fill3DRect(head2.x * tileSize, head2.y * tileSize, tileSize, tileSize, true);

        // Draw Snake 2 body
        g.setColor(Color.blue.darker());
        for (Tile2 bodyPart : snake2.getBody()) {
            g.fill3DRect(bodyPart.x * tileSize, bodyPart.y * tileSize, tileSize, tileSize, true);
        }

        // Scores
        g.setColor(Color.black);

        g.drawString("Player 1 Score: " + snake1.getBody().size(), 10, 20);
        g.drawString("Player 2 Score: " + snake2.getBody().size(), 10, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        movement.moveSnakes();

        // Check for wall collision and start delay if needed
        if (!resetInProgress) {
            if (snake1.getHead().x < 0 || snake1.getHead().x >= boardWidth / tileSize || snake1.getHead().y < 0
                    || snake1.getHead().y >= boardHeight / tileSize) {
                snakeToReset = snake1; // Snake 1 collided
                resetInProgress = true;
                resetDelayTimer.start();
            }

            if (snake2.getHead().x < 0 || snake2.getHead().x >= boardWidth / tileSize || snake2.getHead().y < 0
                    || snake2.getHead().y >= boardHeight / tileSize) {
                snakeToReset = snake2; // Snake 2 collided
                resetInProgress = true;
                resetDelayTimer.start();
            }
        }

        // Check if a player has won
        if (snake1.getBody().size() >= targetScore) {
            gameLoop.stop();
            JOptionPane.showMessageDialog(this, "Player 1 wins!");
        } else if (snake2.getBody().size() >= targetScore) {
            gameLoop.stop();
            JOptionPane.showMessageDialog(this, "Player 2 wins!");
        } else {
            // Adjust speed based on the longest snake
            gameLoop.setDelay(changeSpeed.adjustSpeed(Math.max(snake1.getBody().size(), snake2.getBody().size())));

            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Controls for Player 1
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            snake1.setVelocity(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            snake1.setVelocity(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake1.setVelocity(-1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake1.setVelocity(1, 0);
        }

        // Controls for Player 2
        if (e.getKeyCode() == KeyEvent.VK_W) {
            snake2.setVelocity(0, -1);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            snake2.setVelocity(0, 1);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            snake2.setVelocity(-1, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            snake2.setVelocity(1, 0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
