import java.awt.*;

public class GamePiece {
    private Point position;
    private Color color;
    private int player;

    public GamePiece(Point position, int player) {
        this.position = position;
        this.player = player;
        this.color = (player == 1) ? Color.BLUE : Color.RED;
    }

    public void draw(Graphics2D g2d, int width, int height) {
        g2d.setColor(color);
        int radius = 25;
        int x = (int) (position.x / 100.0 * (width - 100)) + 50;
        int y = (int) (position.y / 100.0 * (height - 100)) + 50;
        g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);

    }

    public Point getPosition() {
        return position;
    }

    public int getPlayer() {
        return player;
    }

}
