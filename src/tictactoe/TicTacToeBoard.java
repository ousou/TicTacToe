package tictactoe;

/**
 *
 * @author Sebastian Björkqvist
 */
public class TicTacToeBoard {

    private final int size;
    private final Mark[][] board;
    private int marksPlaced;

    /**
     * Creates a square tic-tac-toe board with the given side length.
     *
     * @param size
     */
    public TicTacToeBoard(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be positive!");
        }
        this.size = size;
        this.board = new Mark[this.size][this.size];
        this.marksPlaced = 0;
        fillBoardWithEmptyEnum();
    }

    public int getSize() {
        return size;
    }

    private void fillBoardWithEmptyEnum() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                this.board[row][column] = Mark.EMPTY;
            }
        }
    }

    /**
     * Returns the mark that is currently at the given square.
     * 
     * @param row 
     * @param column 
     * @return The mark at the given square
     * @throws IllegalArgumentException if row or column is outside the dimensions of the board
     */
    public Mark getMarkAtSquare(int row, int column) {
        checkDimension(row, column);
        return board[row][column];
    }

    /**
     * Places the given mark on the given square.
     * 
     * @param mark The mark to place
     * @param row 
     * @param column 
     * @return True if the square wasn't taken and the mark placed wasn't empty
     * @throws IllegalArgumentException if row or column is outside the dimensions of the board
     */
    public boolean placeMarkOnSquare(Mark mark, int row, int column) {
        checkDimension(row, column);
        if (mark == Mark.EMPTY) {
            return false;
        }
        if (board[row][column] == Mark.EMPTY) {
            board[row][column] = mark;
            marksPlaced++;
            return true;
        }
        return false;
    }

    /**
     * Removes the mark from the given square.
     * 
     * @param row 
     * @param column 
     * @throws IllegalArgumentException if row or column is outside the dimensions of the board
     */
    public void removeMarkFromPlace(int row, int column) {
        checkDimension(row, column);
        if (board[row][column] == Mark.EMPTY) {
            return;
        }
        board[row][column] = Mark.EMPTY;
        marksPlaced--;
    }

    private void checkDimension(int x, int y) throws IllegalArgumentException {
        if (x < 0 || x >= size) {
            throw new IllegalArgumentException("Coordinate x: " + x
                    + " y: " + y + " out of bounds!");
        }
        if (y < 0 || y >= size) {
            throw new IllegalArgumentException("Coordinate x: " + x
                    + " y: " + y + " out of bounds!");
        }
    }

    /**
     * Returns the state of the game.
     *
     * @return
     */
    public GameState getGameState() {

        // Checking rows:
        for (int row = 0; row < size; row++) {
            Mark firstMark = board[row][0];
            if (firstMark == Mark.EMPTY) {
                continue;
            }
            boolean allSameOnThisRow = true;
            for (int column = 1; column < size; column++) {
                if (board[row][column] != firstMark) {
                    allSameOnThisRow = false;
                }
            }
            if (allSameOnThisRow) {
                if (firstMark == Mark.X) {
                    System.out.println("X won at row " + row);
                    return GameState.X_WON;
                } else {
                    return GameState.O_WON;
                }
            }
        }

        // Checking columnss:
        for (int column = 0; column < size; column++) {
            Mark firstMark = board[0][column];
            if (firstMark == Mark.EMPTY) {
                continue;
            }
            boolean allSameOnThisColumn = true;
            for (int row = 1; row < size; row++) {
                if (board[row][column] != firstMark) {
                    allSameOnThisColumn = false;
                }
            }
            if (allSameOnThisColumn) {
                if (firstMark == Mark.X) {
                    System.out.println("X won at column " + column);
                    return GameState.X_WON;
                } else {
                    return GameState.O_WON;
                }
            }
        }

        // Checking westUp-eastDown diagonal:
        Mark firstMark = board[0][0];
        boolean allSameOnWestUpColumn = true;
        if (firstMark != Mark.EMPTY) {
            for (int j = 1; j < size; j++) {
                if (board[j][j] != firstMark) {
                    allSameOnWestUpColumn = false;
                    break;
                }
            }
            if (allSameOnWestUpColumn) {
                if (firstMark == Mark.X) {
                    return GameState.X_WON;
                } else {
                    return GameState.O_WON;
                }
            }
        }

        // Checking eastUp-westDown diagonal:
        Mark firstMark2 = board[0][size - 1];
        boolean allSameOnEastUpColumn = true;
        if (firstMark2 != Mark.EMPTY) {
            for (int j = 1; j < size; j++) {
                if (board[j][size - 1 - j] != firstMark2) {
                    allSameOnEastUpColumn = false;
                    break;
                }
            }
            if (allSameOnEastUpColumn) {
                if (firstMark2 == Mark.X) {
                    return GameState.X_WON;
                } else {
                    return GameState.O_WON;
                }
            }
        }

        if (marksPlaced < size * size) {
            return GameState.RUNNING;
        }

        return GameState.TIE;
    }

    public String getBoardAsString() {
        StringBuilder boardString = new StringBuilder();

        for (int i = 0; i < size + 2; i++) {
            boardString.append("-");
        }
        boardString.append("\n");
        for (int row = 0; row < size; row++) {
            boardString.append("¦");
            for (int column = 0; column < size; column++) {
                boardString.append(board[row][column].toString());
            }
            boardString.append("¦");
            boardString.append("\n");
        }
        for (int i = 0; i < size + 2; i++) {
            boardString.append("-");
        }

        return boardString.toString();
    }

    public int getMarksPlaced() {
        return marksPlaced;
    }

}
