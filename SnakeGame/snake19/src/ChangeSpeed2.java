
public class ChangeSpeed2 {
    private int initialDelay;
    private double speedIncrement;
    private int minDelay;

    public ChangeSpeed2(int initialDelay, double speedIncrement, int minDelay) {
        this.initialDelay = initialDelay;
        this.speedIncrement = speedIncrement;
        this.minDelay = minDelay;
    }

    public int adjustSpeed(int maxSnakeSize) {
        int newDelay = initialDelay - (int) (maxSnakeSize * speedIncrement);
        return Math.max(newDelay, minDelay);
    }

    public double getSpeedIncrement() {
        return speedIncrement;
    }

    public void setSpeedIncrement(double speedIncrement) {
        this.speedIncrement = speedIncrement;
    }
}