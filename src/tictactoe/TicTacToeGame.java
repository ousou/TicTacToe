package tictactoe;

/**
 * Class handling a tic-tac-toe game.
 * 
 * @author Sebastian Björkqvist
 */
public class TicTacToeGame {
    
    private final TicTacToeBoard board;
    private Mark playerInTurn;

    public TicTacToeGame(int boardSize) {
        this.board = new TicTacToeBoard(boardSize);
        this.playerInTurn = Mark.X;
    }
    
    public TicTacToeGame(int boardSize, Mark playerToStart) {
        if (playerToStart == Mark.EMPTY) {
            throw new IllegalArgumentException("Starting player must be X or O.");
        }
        this.board = new TicTacToeBoard(boardSize);
        this.playerInTurn = playerToStart;
    }    
    
    public GameState getGameState() {
        return board.getGameState();
    }
    
    public String getBoardAsString() {
        return board.getBoardAsString();
    }
    
    /**
     * Places given mark on the given square.
     * 
     * If the mark was placed on the square, the turn will be moved
     * to the other player.
     * 
     * @param row
     * @param column
     * @return true if the square wasn't taken, false if it was.
     */
    public boolean placeMarkOnSquare(int row, int column) {
        boolean couldBePlaced = board.placeMarkOnSquare(playerInTurn, row, column);
        if (couldBePlaced) {
            switchCurrentPlayer();
            return true;
        }
        return false;
    }
    
    public boolean placeMarkOnSquare(Square square) {
        return placeMarkOnSquare(square.row, square.column);
    }

    private void switchCurrentPlayer() {
        if (playerInTurn == Mark.X) {
            playerInTurn = Mark.O;
        } else {
            playerInTurn = Mark.X;
        }
    }
    
    public Mark getPlayerInTurn() {
        return playerInTurn;
    }
    
    /**
     * Retrieves copy of the current board.
     * 
     * @return 
     */
    public TicTacToeBoard getCopyOfBoard() {
        return board.getCopyOfBoard();
    }
    
    public Mark getMarkAtSquare(int row, int column) {
        return board.getMarkAtSquare(row, column);
    }
}
