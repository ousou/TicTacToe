package tictactoe.ai;

import java.util.List;
import tictactoe.GameState;
import tictactoe.Mark;
import tictactoe.Square;
import tictactoe.TicTacToeBoard;

/**
 * An AI using the MinMax-algorithm.
 *
 * This implementation doesn't prune the search tree and does the search
 * all the way to the end of the game, so it doesn't work in real-time for
 * larger boards than 3x3.
 * 
 * @author Sebastian Björkqvist
 */
public class MinMaxAI implements AIPlayer {

    private Mark mark;

    public MinMaxAI(Mark mark) {
        if (mark == Mark.EMPTY) {
            throw new IllegalArgumentException("Mark must be X or O!");
        }
        this.mark = mark;
    }

    public MinMaxAI() {
    }

    @Override
    public Square getMove(TicTacToeBoard board) {
        return minMax(board, mark).square;
    }

    @Override
    public void setPlayerMark(Mark mark) {
        if (mark == Mark.EMPTY) {
            throw new IllegalArgumentException("Mark must be X or O!");
        }
        this.mark = mark;
    }

    @Override
    public Mark getPlayerMark() {
        return mark;
    }

    private SquareValue minMax(TicTacToeBoard board, Mark markToPlace) {
        GameState currentState = board.getGameState();
//        System.out.println(board.getBoardAsString());        
        if (currentState != GameState.RUNNING) {
//            System.out.println("Final board!");
            return scoreResult(currentState);
        }
        List<Square> availableSquares = board.getAvailableSquares();
        SquareValue bestSquareValue;
        // Maximize
        if (markToPlace == Mark.X) {
            bestSquareValue = new SquareValue(null, -10000000);
            for (Square s : availableSquares) {
                board.placeMarkOnSquare(Mark.X, s);
                SquareValue evaluation = minMax(board, Mark.O);
//                System.out.println("Evaluation: " + evaluation);                
                board.removeMarkFromSquare(s);
                /* If the evaluation square is null, it means
                the game ends on the next turn. We want to win
                as quickly as possible, so we value this more
                than a slower forced win.
                */               
                if (evaluation.value > bestSquareValue.value ||
                        (evaluation.value >= bestSquareValue.value && 
                        evaluation.square == null)) {
                    bestSquareValue = new SquareValue(s, evaluation.value);
                }
            }
        } else { // Minimize
            bestSquareValue = new SquareValue(null, 10000000);
            for (Square s : availableSquares) {
                board.placeMarkOnSquare(Mark.O, s);
                SquareValue evaluation = minMax(board, Mark.X);
//                System.out.println("Evaluation: " + evaluation);
                board.removeMarkFromSquare(s);
                if (evaluation.value < bestSquareValue.value ||
                        (evaluation.value <= bestSquareValue.value && 
                        evaluation.square == null)) {
                    bestSquareValue = new SquareValue(s, evaluation.value);
                }
            }
        }

        return bestSquareValue;
    }

    private SquareValue scoreResult(GameState currentState) {
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

    private class SquareValue {

        public final Square square;
        public final int value;

        public SquareValue(Square square, int value) {
            this.square = square;
            this.value = value;
        }

        @Override
        public String toString() {
            return "SquareValue{" + "square=" + square + ", value=" + value + '}';
        }
        
    }   
    
}
