package tictactoe.ai;

import tictactoe.GameState;
import tictactoe.Mark;
import tictactoe.Square;

/**
 * Class containing some basic methods for MinMax AI:s.
 * 
 * @author Sebastian Björkqvist
 */
public abstract class AbstractMinMaxAI implements AIPlayer {
    
    protected Mark mark;    
    protected final static int MIN_VALUE = -10000000;
    protected final static int MAX_VALUE = 10000000;

    public AbstractMinMaxAI(Mark mark) {
        if (mark == Mark.EMPTY) {
            throw new IllegalArgumentException("Mark must be X or O!");
        }        
        this.mark = mark;
    }

    public AbstractMinMaxAI() {
    }
    
    @Override
    public Mark getPlayerMark() {
        return mark;
    }

    protected SquareValue scoreResult(GameState currentState) {
        int result;
        if (currentState == GameState.X_WON) {
            result = 1;
        } else if (currentState == GameState.O_WON) {
            result = -1;
        } else {
            result = 0;
        }
        return new SquareValue(null, result);
    }

    @Override
    public void setPlayerMark(Mark mark) {
        if (mark == Mark.EMPTY) {
            throw new IllegalArgumentException("Mark must be X or O!");
        }
        this.mark = mark;
    }

    protected class SquareValue {

        public final Square square;
        public final int value;

        public SquareValue(Square square, int value) {
            super();
            this.square = square;
            this.value = value;
        }

        @Override
        public String toString() {
            return "SquareValue{" + "square=" + square + ", value=" + value + '}';
        }
    }

    
}
