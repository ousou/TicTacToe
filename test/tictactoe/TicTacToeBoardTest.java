package tictactoe;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sebastian Björkqvist
 */
public class TicTacToeBoardTest {

    private static final int DEFAULT_SIZE = 3;

    @Test(expected = IllegalArgumentException.class)
    public void testZeroBoardSize() {
        new TicTacToeBoard(0);
    }

    @Test
    public void testConstructor() {
        for (int times = 0; times < 10; times++) {
            int size = new Random().nextInt(10) + 1;
            TicTacToeBoard board = new TicTacToeBoard(size);
            assertEquals(size, board.getSize());
            for (int row = 0; row < size; row++) {
                for (int column = 0; column < size; column++) {
                    assertEquals(Mark.EMPTY, board.getMarkAtSquare(row, column));
                }
            }
            assertEquals(GameState.RUNNING, board.getGameState());
        }
    }

    @Test(expected = IllegalArgumentException.class) 
    public void testGetMarkOutsideBoard() {
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.getMarkAtSquare(-1, 0);
    }

    @Test(expected = IllegalArgumentException.class) 
    public void testGetMarkOutsideBoard2() {
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.getMarkAtSquare(0, -1);
    }

    @Test(expected = IllegalArgumentException.class) 
    public void testGetMarkOutsideBoard3() {
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.getMarkAtSquare(3, 0);
    }

    @Test(expected = IllegalArgumentException.class) 
    public void testGetMarkOutsideBoard4() {
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.getMarkAtSquare(1, 3);
    }    
    
    
    @Test
    public void testPlaceMarkOnSquare() {
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        assertTrue(board.placeMarkOnSquare(Mark.X, 0, 0));    
        assertEquals(Mark.X, board.getMarkAtSquare(0, 0));    
        assertEquals(1, board.getMarksPlaced());
    }    
    
    @Test
    public void testPlaceMarkOnSquare2() {
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        assertTrue(board.placeMarkOnSquare(Mark.O, 1, 2));    
        assertEquals(Mark.O, board.getMarkAtSquare(1, 2));   
        assertEquals(1, board.getMarksPlaced());
    }    
    
    @Test
    public void testPlaceMarkOnSquare3() {
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        assertTrue(board.placeMarkOnSquare(Mark.O, 1, 2));    
        assertEquals(Mark.O, board.getMarkAtSquare(1, 2));        
        assertEquals(1, board.getMarksPlaced());        
        assertFalse(board.placeMarkOnSquare(Mark.X, 1, 2));    
        assertEquals(Mark.O, board.getMarkAtSquare(1, 2));         
        assertEquals(1, board.getMarksPlaced());        
    }
    
    @Test
    public void testPlaceEmptyMarkOnSquare() {
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        assertFalse(board.placeMarkOnSquare(Mark.EMPTY, 1, 1));         
    }
    
    @Test
    public void testRemoveMark() {
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);     
        assertTrue(board.placeMarkOnSquare(Mark.O, 1, 1)); 
        assertEquals(1, board.getMarksPlaced());    
        assertEquals(Mark.O, board.getMarkAtSquare(1, 1));  
        
        assertTrue(board.placeMarkOnSquare(Mark.X, 0, 1)); 
        assertEquals(2, board.getMarksPlaced()); 
        assertEquals(Mark.X, board.getMarkAtSquare(0, 1));           
        
        board.removeMarkFromSquare(1, 1);
        assertEquals(1, board.getMarksPlaced()); 
        assertEquals(Mark.EMPTY, board.getMarkAtSquare(1, 1));
        
        board.removeMarkFromSquare(0, 0);
        assertEquals(1, board.getMarksPlaced()); 
        assertEquals(Mark.EMPTY, board.getMarkAtSquare(0, 0));        
    }

    @Test
    public void testXWinRow() {
        System.out.println("TestXWinRow");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.X, 0, 0);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        board.placeMarkOnSquare(Mark.O, 1, 1);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        board.placeMarkOnSquare(Mark.X, 0, 1);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        board.placeMarkOnSquare(Mark.O, 1, 2);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        board.placeMarkOnSquare(Mark.X, 0, 2);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.X_WON, board.getGameState());
    }

    @Test
    public void testOWinColumn() {
        System.out.println("TestOWinColumn");        
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.O, 0, 0);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 1, 1);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 1, 0);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 1, 2);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 2, 0);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.O_WON, board.getGameState());
    }

    @Test
    public void testXWinDiagonal() {
        System.out.println("TestXWinDiagonal");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.X, 0, 0);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 1, 0);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 0, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 0, 2);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 1, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());  
        
        board.placeMarkOnSquare(Mark.O, 1, 2);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState()); 
        
        board.placeMarkOnSquare(Mark.X, 2, 2);        
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.X_WON, board.getGameState());
    }

    @Test
    public void testOWinDiagonal() {
        System.out.println("TestOWinDiagonal");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.O, 0, 2);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 1, 0);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 0, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 0, 0);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 1, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 1, 2);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 2, 2);        
        System.out.println(board.getBoardAsString());    
        assertEquals(GameState.RUNNING, board.getGameState());   
        
        board.placeMarkOnSquare(Mark.X, 2, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());  
        
        board.placeMarkOnSquare(Mark.O, 2, 0);        
        System.out.println(board.getBoardAsString());    
        assertEquals(GameState.O_WON, board.getGameState());
    }
    
    @Test
    public void testTie() {
        System.out.println("TestTie");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.O, 0, 0);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 0, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 1, 0);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 1, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 2, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 2, 0);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 0, 2);        
        System.out.println(board.getBoardAsString());    
        assertEquals(GameState.RUNNING, board.getGameState());   
        
        board.placeMarkOnSquare(Mark.X, 1, 2);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());  
        
        board.placeMarkOnSquare(Mark.O, 2, 2);        
        System.out.println(board.getBoardAsString());    
        assertEquals(GameState.TIE, board.getGameState());        
    }
    
    @Test
    public void testTie2() {
        System.out.println("TestTie2");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);
        board.placeMarkOnSquare(Mark.O, 0, 0);
        System.out.println(board.getBoardAsString());
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 0, 2);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 0, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 1, 0);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 1, 2);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.X, 1, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());
        
        board.placeMarkOnSquare(Mark.O, 2, 0);        
        System.out.println(board.getBoardAsString());    
        assertEquals(GameState.RUNNING, board.getGameState());   
        
        board.placeMarkOnSquare(Mark.X, 2, 1);
        System.out.println(board.getBoardAsString());        
        assertEquals(GameState.RUNNING, board.getGameState());  
        
        board.placeMarkOnSquare(Mark.O, 2, 2);        
        System.out.println(board.getBoardAsString());    
        assertEquals(GameState.TIE, board.getGameState());        
    }
    
    @Test
    public void testGetCopyOfBoard() {
        System.out.println("TestGetCopyOfBoard");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);        
        
        board.placeMarkOnSquare(Mark.O, 1, 2);        
        board.placeMarkOnSquare(Mark.X, 2, 2);
        assertEquals(2, board.getMarksPlaced());
        
        TicTacToeBoard copy = board.getCopyOfBoard();

        assertEquals(Mark.O, copy.getMarkAtSquare(1, 2));         
        assertEquals(Mark.X, copy.getMarkAtSquare(2, 2));
        
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            assertEquals(Mark.EMPTY, copy.getMarkAtSquare(i, 0));
            assertEquals(Mark.EMPTY, copy.getMarkAtSquare(0, 1));            
        }
        
        assertEquals(Mark.EMPTY, copy.getMarkAtSquare(1, 1));
        
        assertEquals(GameState.RUNNING, copy.getGameState());
        
    }
    
    @Test
    public void testGetAvailableSquares() {
        System.out.println("TestGetAvailableSquares");
        TicTacToeBoard board = new TicTacToeBoard(DEFAULT_SIZE);      
        
        assertEquals(9, board.getAvailableSquares().size());
        
        board.placeMarkOnSquare(Mark.O, 1, 2);        
        assertEquals(8, board.getAvailableSquares().size());
        assertFalse(board.getAvailableSquares().contains(new Square(1,2)));
        board.placeMarkOnSquare(Mark.X, 2, 2);        
        assertEquals(7, board.getAvailableSquares().size());
        assertFalse(board.getAvailableSquares().contains(new Square(1,2)));   
        assertFalse(board.getAvailableSquares().contains(new Square(2,2)));        
    }

}
