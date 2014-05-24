package tictactoe;

/**
 *
 * @author Sebastian Björkqvist
 */
public enum Mark {

    X("X"),
    O("O"),
    EMPTY(" ");
    
    private final String markString;
    
    private Mark(String markString) {
        this.markString = markString;
    }

    @Override
    public String toString() {
        return markString;
    }
    
    
}
