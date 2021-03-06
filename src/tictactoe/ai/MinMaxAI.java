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
public class MinMaxAI extends AbstractMinMaxAI {

    private int numberOfEvaluations;
    
    public MinMaxAI(Mark mark) {
        super(mark);
    }

    public MinMaxAI() {
        super();
    }

    @Override
    public Square getMove(TicTacToeBoard board) {
        numberOfEvaluations = 0;
        return evaluateBoard(board, mark).square;
    }    

    public int getNumberOfEvaluations() {
        return numberOfEvaluations;
    }
    
    private SquareValue evaluateBoard(TicTacToeBoard board, Mark markToPlace) {
        GameState currentState = board.getGameState();
        numberOfEvaluations++;
//        System.out.println(board.getBoardAsString());        
        if (currentState != GameState.RUNNING) {
//            System.out.println("Final board!");
            return scoreResult(currentState);
        }
        List<Square> availableSquares = board.getAvailableSquares();
        SquareValue bestSquareValue;
        // Maximize
        if (markToPlace == Mark.X) {
            bestSquareValue = new SquareValue(null, MIN_VALUE);
            for (Square s : availableSquares) {
                board.placeMarkOnSquare(Mark.X, s);
                SquareValue evaluation = evaluateBoard(board, Mark.O);
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
            bestSquareValue = new SquareValue(null, MAX_VALUE);
            for (Square s : availableSquares) {
                board.placeMarkOnSquare(Mark.O, s);
                SquareValue evaluation = evaluateBoard(board, Mark.X);
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

    
}
