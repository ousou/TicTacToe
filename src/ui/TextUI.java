package ui;

import java.util.Scanner;
import tictactoe.GameState;
import tictactoe.Mark;
import tictactoe.Square;
import tictactoe.TicTacToeGame;
import tictactoe.ai.AIPlayer;
import tictactoe.ai.MinMaxAI;

/**
 * Text user interface for tic-tac-toe.
 *
 * @author Sebastian Björkqvist
 */
public class TextUI implements UI {

    private TicTacToeGame game;
    private final Scanner scanner;
    private final static int BOARD_SIZE = 3;
    private Mark aiMark;
    private AIPlayer aiPlayer;

    public TextUI() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
        boolean startNewGame = true;
        while (startNewGame) {
            game = new TicTacToeGame(BOARD_SIZE);
            askIfGameAgainstAI();
            while (game.getGameState() == GameState.RUNNING) {
                playTurn();
            }
            printGameResult();
            startNewGame = askIfPlayerWantsToPlayAnotherGame();
        }
    }

    private void playTurn() {
        Mark playerInTurn = game.getPlayerInTurn();
        System.out.println("Player in turn: " + playerInTurn);
        System.out.println(game.getBoardAsString());
        boolean markPlaced = false;
        if (playerInTurn == aiMark) {
            Square move = aiPlayer.getMove(game.getCopyOfBoard());
            game.placeMarkOnSquare(move);
        } else {
            while (!markPlaced) {
                int[] input = getInput();
                int row = input[0];
                int column = input[1];
                if (game.getMarkAtSquare(row, column) == Mark.EMPTY) {
                    game.placeMarkOnSquare(row, column);
                    markPlaced = true;
                } else {
                    System.out.println("There is already a mark on row " + (row + 1)
                            + ", column " + (column + 1));
                }
            }
        }
    }

    private int[] getInput() {
        while (true) {
            boolean inputReceived = true;
            System.out.println("");
            System.out.print("Enter move as coordinate row,column (i.e. 1,2), numbering from 0 to " + (BOARD_SIZE - 1) + ": ");
            String inputString = scanner.nextLine();
            String[] rowAndColumn = inputString.split(",");
            if (rowAndColumn.length != 2) {
                System.out.println("Give input in form row,column. Numbers must be from 1 to 3.");
            }
            int[] input = new int[2];
            try {
                input[0] = Integer.parseInt(rowAndColumn[0]);
                input[1] = Integer.parseInt(rowAndColumn[1]);
            } catch (NumberFormatException e) {
                System.out.println("One of the inputs wasn't an integer.");
                inputReceived = false;
            }
            if (input[0] < 0 || input[0] >= BOARD_SIZE) {
                System.out.println("First input not between 0 and " + (BOARD_SIZE - 1));
                inputReceived = false;
            }
            if (input[1] < 0 || input[1] >= BOARD_SIZE) {
                System.out.println("Second input not between 0 and " + (BOARD_SIZE - 1));
                inputReceived = false;
            }
            if (inputReceived) {
                return input;
            }
        }
    }

    private void printGameResult() {
        System.out.println(game.getBoardAsString());
        System.out.println("");
        if (game.getGameState() == GameState.TIE) {
            System.out.println("Game ended in a tie.");
        } else if (game.getGameState() == GameState.X_WON) {
            System.out.println("Winner: X");
        } else if (game.getGameState() == GameState.O_WON) {
            System.out.println("Winner: O");
        }
    }

    private boolean askIfPlayerWantsToPlayAnotherGame() {
        System.out.print("Play another game? (Y/N): ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("YES")) {
            return true;
        }
        return false;
    }

    private void askIfGameAgainstAI() {
        System.out.println("Starting a new tic-tac-toe game!");
        System.out.println("");
        System.out.print("Play against AI (Y/N)? ");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("YES")) {
            aiPlayer = new MinMaxAI();
            System.out.print("Pick your mark (X/O), X starts: ");
            answer = scanner.nextLine();            
            if (answer.equalsIgnoreCase("X") || answer.equalsIgnoreCase("YES")) {
                aiMark = Mark.O;
            } else {
                aiMark = Mark.X;
            }
            aiPlayer.setPlayerMark(aiMark);
        }

    }
}
