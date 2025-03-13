import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameBoard extends JPanel {
    private BufferedImage boardImage;
    private ArrayList<GamePiece> pieces;

    public GameBoard() {
        try {
            boardImage = ImageIO.read(new File("images/board2.png"));
        } catch (IOException e) {
            System.out.println("Error: board image");
        }

        pieces = new ArrayList<>();
        setLayout(new BorderLayout());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (boardImage != null) {
            g2d.drawImage(boardImage, 50, 50, getWidth() - 100, getHeight() - 100, this);
        }

        for (GamePiece piece : pieces) {
            piece.draw(g2d, getWidth(), getHeight());
        }
    }

    public void updatePieces(ArrayList<GamePiece> placedPieces) {
        pieces.clear();
        for (GamePiece info : placedPieces) {
            pieces.add(new GamePiece(info.getPosition(), info.getPlayer()));
        }
        repaint();
    }

}
