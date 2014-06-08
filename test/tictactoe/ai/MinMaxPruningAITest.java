package tictactoe.ai;

import org.junit.Test;
import static org.junit.Assert.*;
import tictactoe.Mark;
import tictactoe.Square;
import tictactoe.TicTacToeBoard;

/**
 *
 * @author Sebastian Björkqvist
 */
public class MinMaxPruningAITest {

    private static final int DEFAULT_SIZE = 3;    

    @Test
    public void testWhenOnlyOnePlaceToMove() {
        System.out.println("testWhenOnlyOnePlaceToMove");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.X, 0,0);
        board.placeMarkOnSquare(Mark.O, 0,1);
        board.placeMarkOnSquare(Mark.X, 1,1);
        board.placeMarkOnSquare(Mark.O, 2,2);
        board.placeMarkOnSquare(Mark.X, 1,2);
        board.placeMarkOnSquare(Mark.O, 1,0);
        board.placeMarkOnSquare(Mark.X, 2,0);
        board.placeMarkOnSquare(Mark.O, 0,2);
        
        MinMaxPruningAI ai = new MinMaxPruningAI(Mark.X);
        
        Square move = ai.getMove(board);
        
        assertEquals(2, move.row);
        assertEquals(1, move.column);
        System.out.println("Number of evaluations: " + ai.getNumberOfEvaluations());        
    }

    @Test
    public void testTwoPossibleMoves() {
        System.out.println("testTwoPossibleMoves");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.X, 0,0);
        board.placeMarkOnSquare(Mark.O, 0,1);
        board.placeMarkOnSquare(Mark.X, 1,1);
        board.placeMarkOnSquare(Mark.O, 2,2);
        board.placeMarkOnSquare(Mark.X, 1,2);
        board.placeMarkOnSquare(Mark.O, 1,0);
        board.placeMarkOnSquare(Mark.X, 2,0);
        
        MinMaxPruningAI ai = new MinMaxPruningAI(Mark.O);
        
        Square move = ai.getMove(board);
        
        assertEquals(0, move.row);
        assertEquals(2, move.column);
        System.out.println("Number of evaluations: " + ai.getNumberOfEvaluations());        
    }

    @Test
    public void testFourPossibleMoves() {
        System.out.println("testFourPossibleMoves");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.X, 1,1);
        board.placeMarkOnSquare(Mark.O, 0,2);
        board.placeMarkOnSquare(Mark.X, 2,1);
        board.placeMarkOnSquare(Mark.O, 0,1);
        board.placeMarkOnSquare(Mark.X, 0,0);
        
        MinMaxPruningAI ai = new MinMaxPruningAI(Mark.O);
        
        Square move = ai.getMove(board);
        
        assertEquals(2, move.row);
        assertEquals(2, move.column);
        System.out.println("Number of evaluations: " + ai.getNumberOfEvaluations());        
    }

    @Test
    public void testFivePossibleMoves() {
        System.out.println("testFivePossibleMoves");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.X, 2,0);
        board.placeMarkOnSquare(Mark.O, 0,2);
        board.placeMarkOnSquare(Mark.X, 1,1);
        board.placeMarkOnSquare(Mark.O, 0,0);
        
        MinMaxPruningAI ai = new MinMaxPruningAI(Mark.X);
        
        Square move = ai.getMove(board);
        
        assertEquals(0, move.row);
        assertEquals(1, move.column);
        System.out.println("Number of evaluations: " + ai.getNumberOfEvaluations());        
    }

    @Test
    public void testFivePossibleMovesWin() {
        System.out.println("testFivePossibleMovesWin");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.X, 0,0);
        board.placeMarkOnSquare(Mark.O, 0,2);
        board.placeMarkOnSquare(Mark.X, 1,0);
        board.placeMarkOnSquare(Mark.O, 0,1);
        
        MinMaxPruningAI ai = new MinMaxPruningAI(Mark.X);
        
        Square move = ai.getMove(board);
        
        assertEquals(2, move.row);
        assertEquals(0, move.column);
        System.out.println("Number of evaluations: " + ai.getNumberOfEvaluations());        
    }

    @Test
    public void testSixPossibleMoves() {
        System.out.println("testSixPossibleMoves");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.X, 2,0);
        board.placeMarkOnSquare(Mark.O, 0,2);
        board.placeMarkOnSquare(Mark.X, 1,1);
        
        MinMaxPruningAI ai = new MinMaxPruningAI(Mark.O);
        
        Square move = ai.getMove(board);
        
        assertEquals(0, move.row);
        assertEquals(0, move.column);
        System.out.println("Number of evaluations: " + ai.getNumberOfEvaluations());        
    }

    @Test
    public void testFirstMove() {
        System.out.println("testFirstMove");
        // Done just to check how long this takes
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        
        MinMaxPruningAI ai = new MinMaxPruningAI(Mark.X);
        
        Square move = ai.getMove(board);
        System.out.println("Number of evaluations: " + ai.getNumberOfEvaluations());
    }


}