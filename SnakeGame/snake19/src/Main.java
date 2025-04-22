import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        // Display a dialog to ask for the game mode
        String input = JOptionPane.showInputDialog(null,
                "Choose game mode:\n1 player or 2 player?\nType 1 for 1 player and 2 for 2 player:");

        // Convert input to lowercase for consistency
        input = input != null ? input.toLowerCase() : "";

        if (input.equals("1")) {
            // Run 1-player app
            App1.main(args);
        } else if (input.equals("2")) {
            // Run 2-player app
            App2.main(args);
        } else {
            // Show an error message if the input is invalid
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter '1 player' or '2 player'.");
        }
    }
}
