package tictactoe.ai;

import java.util.List;
import tictactoe.GameState;
import tictactoe.Mark;
import tictactoe.Square;
import tictactoe.TicTacToeBoard;

/**
 *
 * @author Sebastian Björkqvist
 */
public class MinMaxPruningAI extends AbstractMinMaxAI {

    private int numberOfEvaluations;
    
    public MinMaxPruningAI(Mark mark) {
        super(mark);
    }

    public MinMaxPruningAI() {
    }

    @Override
    public Square getMove(TicTacToeBoard board) {
        numberOfEvaluations = 0;
        return evaluateBoard(board, mark, MIN_VALUE, MAX_VALUE).square;
    }

    public int getNumberOfEvaluations() {
        return numberOfEvaluations;
    }
    
    private SquareValue evaluateBoard(TicTacToeBoard board, Mark markToPlace
            , int alpha, int beta) {
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
            bestSquareValue = new SquareValue(null, alpha);
            for (Square s : availableSquares) {
                board.placeMarkOnSquare(Mark.X, s);
                SquareValue evaluation = evaluateBoard(board, Mark.O, alpha, beta);
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
                    alpha = evaluation.value;
                }
                // Beta cut-off;
                if (beta <= alpha) {
                    break;
                }
                
            }
        } else { // Minimize
            bestSquareValue = new SquareValue(null, beta);
            for (Square s : availableSquares) {
                board.placeMarkOnSquare(Mark.O, s);
                SquareValue evaluation = evaluateBoard(board, Mark.X, alpha, beta);
//                System.out.println("Evaluation: " + evaluation);
                board.removeMarkFromSquare(s);
                if (evaluation.value < bestSquareValue.value ||
                        (evaluation.value <= bestSquareValue.value && 
                        evaluation.square == null)) {
                    bestSquareValue = new SquareValue(s, evaluation.value);
                    beta = evaluation.value;
                }
                // Alpha cut-off;
                if (beta <= alpha) {
                    break;
                }                
            }
        }

        return bestSquareValue;
    }    
}
