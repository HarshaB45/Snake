
import javax.swing.*;

public class App2 {
    public static void main(String[] args) {
        String boardWidthInput = JOptionPane.showInputDialog(
                "Recommended board length and dimensions: 600 and 24. Enter the board length (e.g., 600):");
        int boardWidth = Integer.parseInt(boardWidthInput);

        String dimensionsInput = JOptionPane.showInputDialog("Enter the number of dimensions (e.g., 24 squares):");
        int dimensions = Integer.parseInt(dimensionsInput);

        // Get the target score input
        String targetScoreInput = JOptionPane.showInputDialog("Enter the target score to win (e.g., 10):");
        int targetScore = Integer.parseInt(targetScoreInput);

        JOptionPane.showMessageDialog(null, "Instructions\nThis is a two player snake game. " +
                "Player 1 controls the snake using WASD controls, and Player 2 uses ARROW KEYS.\nWhoever reaches the target score"
                +
                " first wins.\nNote: When a player collides into a wall, he will respawn at the position (0, 0) and his speed will be reduced.\n"
                +
                "Have Fun! Self Body Collision: Off, Turn in Opposite Direction: On\n"
                +
                "Type <Enter> to continue");

        int tileSize = boardWidth / dimensions;

        int boardHeight = boardWidth;

        JFrame frame = new JFrame("Snake (Versus)");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SnakeGame2 snakeGame = new SnakeGame2(boardWidth, boardHeight, tileSize, targetScore, dimensions);
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus();
    }
}
