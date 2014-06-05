package tictactoe;

/**
 *
 * @author Sebastian Björkqvist
 */
public class Square {

    public final int row;
    public final int column;

    public Square(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Square{" + "row=" + row + ", column=" + column + '}';
    }
    
}
