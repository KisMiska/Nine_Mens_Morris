import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class GameLogic {
    private int currentPlayer;
    private ArrayList<Point> validPositions;
    private HashMap<Point, Integer> boardState;
    private int[] playerPieces;
    private ArrayList<ArrayList<Point>> mills;
    private boolean removePiece = false;
    private int gamePhase = 1;
    private Point selectedPiece = null;
    private GameScreen gameScreen;
    private ArrayList<ArrayList<Point>> adjacencies;

    public GameLogic(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        currentPlayer = 1;
        validPositions = new ArrayList<>();
        boardState = new HashMap<>();
        playerPieces = new int[]{0, 0, 0};
        mills = new ArrayList<>();
        initValidPositions();
        initMills();
        initAdjacencies();
    }

    public void initValidPositions() {
        validPositions.add(new Point(0, 0));
        validPositions.add(new Point(50, 0));
        validPositions.add(new Point(100, 0));
        validPositions.add(new Point(0, 50));
        validPositions.add(new Point(100, 50));
        validPositions.add(new Point(0, 100));
        validPositions.add(new Point(50, 100));
        validPositions.add(new Point(100, 100));

        validPositions.add(new Point(14, 14));
        validPositions.add(new Point(50, 14));
        validPositions.add(new Point(86, 14));
        validPositions.add(new Point(14, 50));
        validPositions.add(new Point(86, 50));
        validPositions.add(new Point(14, 86));
        validPositions.add(new Point(50, 86));
        validPositions.add(new Point(86, 86));

        validPositions.add(new Point(28, 28));
        validPositions.add(new Point(50, 28));
        validPositions.add(new Point(72, 28));
        validPositions.add(new Point(28, 50));
        validPositions.add(new Point(72, 50));
        validPositions.add(new Point(28, 72));
        validPositions.add(new Point(50, 72));
        validPositions.add(new Point(72, 72));

        for (Point pos : validPositions) {
            boardState.put(pos, 0);
        }
    }

    public void initMills() {
        mills.add(new ArrayList<>());
        mills.get(0).add(new Point(0, 0));
        mills.get(0).add(new Point(50, 0));
        mills.get(0).add(new Point(100, 0));

        mills.add(new ArrayList<>());
        mills.get(1).add(new Point(0, 50));
        mills.get(1).add(new Point(0, 0));
        mills.get(1).add(new Point(0, 100));

        mills.add(new ArrayList<>());
        mills.get(2).add(new Point(100, 0));
        mills.get(2).add(new Point(100, 50));
        mills.get(2).add(new Point(100, 100));

        mills.add(new ArrayList<>());
        mills.get(3).add(new Point(0, 100));
        mills.get(3).add(new Point(50, 100));
        mills.get(3).add(new Point(100, 100));

        mills.add(new ArrayList<>());
        mills.get(4).add(new Point(14, 14));
        mills.get(4).add(new Point(50, 14));
        mills.get(4).add(new Point(86, 14));

        mills.add(new ArrayList<>());
        mills.get(5).add(new Point(14, 14));
        mills.get(5).add(new Point(14, 50));
        mills.get(5).add(new Point(14, 86));

        mills.add(new ArrayList<>());
        mills.get(6).add(new Point(86, 14));
        mills.get(6).add(new Point(86, 50));
        mills.get(6).add(new Point(86, 86));

        mills.add(new ArrayList<>());
        mills.get(7).add(new Point(14, 86));
        mills.get(7).add(new Point(50, 86));
        mills.get(7).add(new Point(86, 86));

        mills.add(new ArrayList<>());
        mills.get(8).add(new Point(28, 28));
        mills.get(8).add(new Point(50, 28));
        mills.get(8).add(new Point(72, 28));

        mills.add(new ArrayList<>());
        mills.get(9).add(new Point(28, 28));
        mills.get(9).add(new Point(28, 50));
        mills.get(9).add(new Point(28, 72));

        mills.add(new ArrayList<>());
        mills.get(10).add(new Point(72, 28));
        mills.get(10).add(new Point(72, 50));
        mills.get(10).add(new Point(72, 72));

        mills.add(new ArrayList<>());
        mills.get(11).add(new Point(28, 72));
        mills.get(11).add(new Point(50, 72));
        mills.get(11).add(new Point(72, 72));

        mills.add(new ArrayList<>());
        mills.get(12).add(new Point(0, 50));
        mills.get(12).add(new Point(14, 50));
        mills.get(12).add(new Point(28, 50));

        mills.add(new ArrayList<>());
        mills.get(13).add(new Point(100, 50));
        mills.get(13).add(new Point(86, 50));
        mills.get(13).add(new Point(72, 50));

        mills.add(new ArrayList<>());
        mills.get(14).add(new Point(50, 0));
        mills.get(14).add(new Point(50, 14));
        mills.get(14).add(new Point(50, 28));

        mills.add(new ArrayList<>());
        mills.get(15).add(new Point(50, 100));
        mills.get(15).add(new Point(50, 86));
        mills.get(15).add(new Point(50, 72));

    }

    public void initAdjacencies() {
        adjacencies = new ArrayList<>();

        adjacencies.add(new ArrayList<>());
        adjacencies.get(0).add(new Point(0, 0));
        adjacencies.get(0).add(new Point(50, 0));
        adjacencies.get(0).add(new Point(0, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(1).add(new Point(50, 0));
        adjacencies.get(1).add(new Point(100, 0));
        adjacencies.get(1).add(new Point(50, 14));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(2).add(new Point(100, 0));
        adjacencies.get(2).add(new Point(100, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(3).add(new Point(0, 50));
        adjacencies.get(3).add(new Point(0, 100));
        adjacencies.get(3).add(new Point(14, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(4).add(new Point(100, 50));
        adjacencies.get(4).add(new Point(100, 100));
        adjacencies.get(4).add(new Point(86, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(5).add(new Point(0, 100));
        adjacencies.get(5).add(new Point(50, 100));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(6).add(new Point(50, 100));
        adjacencies.get(6).add(new Point(100, 100));
        adjacencies.get(6).add(new Point(50, 86));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(7).add(new Point(100, 100));
        adjacencies.get(7).add(new Point(100, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(8).add(new Point(14, 14));
        adjacencies.get(8).add(new Point(50, 14));
        adjacencies.get(8).add(new Point(14, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(9).add(new Point(50, 14));
        adjacencies.get(9).add(new Point(86, 14));
        adjacencies.get(9).add(new Point(50, 28));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(10).add(new Point(86, 14));
        adjacencies.get(10).add(new Point(86, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(11).add(new Point(14, 50));
        adjacencies.get(11).add(new Point(14, 86));
        adjacencies.get(11).add(new Point(28, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(12).add(new Point(86, 50));
        adjacencies.get(12).add(new Point(86, 86));
        adjacencies.get(12).add(new Point(72, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(13).add(new Point(14, 86));
        adjacencies.get(13).add(new Point(50, 86));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(14).add(new Point(50, 86));
        adjacencies.get(14).add(new Point(86, 86));
        adjacencies.get(14).add(new Point(50, 72));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(15).add(new Point(86, 86));
        adjacencies.get(15).add(new Point(86, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(16).add(new Point(28, 28));
        adjacencies.get(16).add(new Point(50, 28));
        adjacencies.get(16).add(new Point(28, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(17).add(new Point(50, 28));
        adjacencies.get(17).add(new Point(72, 28));
        adjacencies.get(17).add(new Point(50, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(18).add(new Point(72, 28));
        adjacencies.get(18).add(new Point(72, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(19).add(new Point(28, 50));
        adjacencies.get(19).add(new Point(28, 72));
        adjacencies.get(19).add(new Point(50, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(20).add(new Point(72, 50));
        adjacencies.get(20).add(new Point(72, 72));
        adjacencies.get(20).add(new Point(50, 50));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(21).add(new Point(28, 72));
        adjacencies.get(21).add(new Point(50, 72));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(22).add(new Point(50, 72));
        adjacencies.get(22).add(new Point(72, 72));
        adjacencies.get(22).add(new Point(50, 86));

        adjacencies.add(new ArrayList<>());
        adjacencies.get(23).add(new Point(72, 72));
        adjacencies.get(23).add(new Point(72, 50));

    }

    public boolean isAdjacent(Point from, Point to) {
        for (ArrayList<Point> adjacency : adjacencies) {
            if (adjacency.contains(from) && adjacency.contains(to)) {
                return true;
            }
        }
        return false;
    }

    public boolean handlePlace(Point click) {

        for (Point validPos : validPositions) {
            int tolerance = 10;
            if (Math.abs(click.x - validPos.x) <= tolerance && Math.abs(click.y - validPos.y) <= tolerance) {
                if (boardState.get(validPos) == 0) {
                    boardState.put(validPos, currentPlayer);
                    playerPieces[currentPlayer]++;
                    gameScreen.updateCounters();

                    int maxPiecesPerPlayer = 9;
                    if (playerPieces[1] == maxPiecesPerPlayer && playerPieces[2] == maxPiecesPerPlayer) {
                        gamePhase = 2;
                    }

                    if (isMillFormed(validPos, currentPlayer)) {
                        removePiece = true;
                    }

                    if (!removePiece) {
                        nextTurn();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean handleMovement(Point click) {

        for (Point validPos : validPositions) {
            int tolerance = 10;
            if (Math.abs(click.x - validPos.x) <= tolerance && Math.abs(click.y - validPos.y) <= tolerance) {
                if (selectedPiece == null) {

                    if (boardState.get(validPos) == currentPlayer) {
                        selectedPiece = validPos;
                        return true;
                    }
                } else {
                    if (boardState.get(validPos) == 0 && (isAdjacent(selectedPiece, validPos) || getPieceCount(currentPlayer) == 3)) {
                        boardState.put(selectedPiece, 0);
                        boardState.put(validPos, currentPlayer);
                        if (isMillFormed(validPos, currentPlayer)) {
                            removePiece = true;
                        }
                        selectedPiece = null;
                        if (!removePiece) {
                            nextTurn();
                        }
                        return true;
                    } else {
                        selectedPiece = null;
                    }
                }
            }
        }
        return false;
    }


    public boolean isMillFormed(Point position, int player) {
        for (ArrayList<Point> mill : mills) {
            if (mill.contains(position)) {
                boolean allOwned = true;
                for (Point p : mill) {
                    if (boardState.get(p) != player) {
                        allOwned = false;
                        break;
                    }
                }
                if (allOwned) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean canRemovePiece(Point point) {
        if (!removePiece) return false;
        for (Point validPos : validPositions) {
            int tolerance = 10;
            if (Math.abs(point.x - validPos.x) <= tolerance && Math.abs(point.y - validPos.y) <= tolerance) {
                if (boardState.get(validPos) == getOpponent()) {
                    boardState.put(validPos, 0);
                    gameScreen.updateCounters();
                    removePiece = false;

                    return true;
                }
            }
        }
        return false;
    }

    public int getOpponent() {
        return (currentPlayer == 1) ? 2 : 1;
    }


    public boolean isRemoveMode() {
        return removePiece;
    }

    public ArrayList<GamePiece> getPlacedPieces() {
        /*
        ArrayList<PieceInfo> placedPieces = new ArrayList<>();
        for (Point position : boardState.keySet()) {
            int player = boardState.get(position);
            if (player != 0) {
                placedPieces.add(new PieceInfo(position, player));
            }
        }
        return placedPieces;
        */
        return boardState.entrySet().stream().filter(x -> x.getValue() != 0).map(x -> new GamePiece(x.getKey(), x.getValue())).collect(Collectors.toCollection(ArrayList::new));

    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void nextTurn() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    public int getPieceCount(int player) {
        return (int) boardState.values().stream().filter(x -> x == player).count();
    }

    public int getGamePhase() {
        return gamePhase;
    }


}
