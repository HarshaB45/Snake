import javax.swing.*;

public class App1 {
    public static void main(String[] args) {
        int boardWidth = 600;
        int boardHeight = boardWidth;

        JOptionPane.showMessageDialog(null, "Instructions\nThis is a one player snake game. " +
                "Classic Snake Game. Keep growing until the snake collides with it's own body, or collides into a wall\n"
                +
                "Have Fun! Body Collision: On, Turn in Opposite Direction: Off\n"
                +
                "Type <Enter> to continue");

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame1 snakeGame = new SnakeGame1(boardWidth, boardHeight);
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus();
    }
}