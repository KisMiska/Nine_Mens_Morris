import javax.swing.*;
import java.awt.*;

public class GameScreen extends JFrame {
    private MusicPlayer musicPlayer;
    private GameLogic gameLogic;
    private EffectPlayer placeSound;
    private EffectPlayer removeSound;
    private JLabel player1Counter;
    private JLabel player2Counter;
    private JLabel timerLabel;
    private Timer timer;
    private int timeLeft;
    private boolean gameFrozen;
    private GameBoard board;

    public GameScreen(MainMenu mainMenu, MusicPlayer musicPlayer, EffectPlayer placeSound, EffectPlayer removeSound) {
        this.musicPlayer = musicPlayer;
        this.placeSound = placeSound;
        this.removeSound = removeSound;
        this.gameLogic = new GameLogic(this);

        setTitle("Nine Men's Morris");
        setSize(900, 900);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        board = new GameBoard();
        board.setPreferredSize(new Dimension(800, 800));
        board.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                handleMouseClick(e.getPoint());
            }
        });
        add(board, BorderLayout.CENTER);

        player1Counter = new JLabel("   0", SwingConstants.CENTER);
        player1Counter.setFont(new Font("Arial", Font.BOLD, 32));
        player1Counter.setForeground(Color.BLUE);
        player1Counter.setBackground(new Color(200, 200, 200));

        player2Counter = new JLabel("0   ", SwingConstants.CENTER);
        player2Counter.setFont(new Font("Arial", Font.BOLD, 32));
        player2Counter.setForeground(Color.RED);
        player2Counter.setBackground(new Color(200, 200, 200));

        add(player1Counter, BorderLayout.WEST);
        add(player2Counter, BorderLayout.EAST);

        timerLabel = new JLabel("Time Left: 20 seconds", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 38));
        timerLabel.setForeground(Color.BLACK);
        add(timerLabel, BorderLayout.NORTH);

        timeLeft = 20;
        gameFrozen = false;
        timer = new Timer(1000, e -> {
            if (gameFrozen) return;
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + " seconds");
            if (timeLeft <= 0) {
                timer.stop();
                timeOut();
            }
        });
        timer.start();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonPanel.setBackground(new Color(200, 200, 200));
        buttonPanel.add(MainButtons.createButton("Back", e -> {
            mainMenu.setVisible(true);
            dispose();
        }));
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);

        updateCounters();
    }

    public void handleMouseClick(Point click) {
        if (gameFrozen) return;

        Point boardClick = new Point((click.x - 50) * 100 / (board.getWidth() - 100), (click.y - 50) * 100 / (board.getHeight() - 100));

        if (gameLogic.isRemoveMode()) {
            if (gameLogic.canRemovePiece(boardClick)) {
                removeSound.play();
                updateBoard();
                gameLogic.nextTurn();
            }
        } else if (gameLogic.getGamePhase() == 1) {
            if (gameLogic.handlePlace(boardClick)) {
                placeSound.play();
                updateBoard();
                resetTimer();
            }
        } else if (gameLogic.getGamePhase() == 2) {
            if (gameLogic.handleMovement(boardClick)) {
                placeSound.play();
                updateBoard();
                resetTimer();
            }
        }
    }

    public void updateBoard() {
        board.updatePieces(gameLogic.getPlacedPieces());
        updateCounters();
    }

    public void updateCounters() {
        int player1Count = gameLogic.getPieceCount(1);
        int player2Count = gameLogic.getPieceCount(2);

        if ((player1Count < 3 || player2Count < 3) && gameLogic.getGamePhase() == 2) {
            gotWinner(player1Count > player2Count ? 1 : 2);
        }

        player1Counter.setText("   " + player1Count);
        player2Counter.setText(player2Count + "   ");
    }

    public void timeOut() {
        int currentPlayer = gameLogic.getCurrentPlayer();
        String winner = (currentPlayer == 1) ? "RED" : "BLUE";
        gameFrozen = true;
        timerLabel.setText("Time Out! Player " + winner + " wins!");
    }

    public void resetTimer() {
        timeLeft = 20;
        timerLabel.setText("Time Left: " + timeLeft + " seconds");
        timer.restart();
    }

    public void gotWinner(int winner) {
        gameFrozen = true;
        String winnermsg = (winner == 1) ? "Player BLUE wins!" : "Player RED wins!";
        timerLabel.setText(winnermsg);
    }
}
