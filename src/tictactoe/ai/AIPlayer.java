package tictactoe.ai;

import tictactoe.Mark;
import tictactoe.Square;
import tictactoe.TicTacToeBoard;

/**
 *
 * @author Sebastian Björkqvist
 */
public interface AIPlayer {

    /**
     * Retrieves the move of the AI player.
     * @param board The current board.
     * @return The square on which the ai places its mark
     */
    Square getMove(TicTacToeBoard board);
    
    /**
     * Sets the mark that the AI player has in the game
     * 
     * @param mark X or O
     */
    void setPlayerMark(Mark mark);
    
    /**
     * Retrieves the mark that the AI player has in the game
     * @return The mark, X or O
     */
    Mark getPlayerMark();
}
