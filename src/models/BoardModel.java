package models;

import java.util.Arrays;

/**
 * Created by Deniz on 7/14/2015.
 */
public class BoardModel {
    //contains the underlying data model
    private Object[][] board;

    /**
     * Setup initial data model and initialize board to be completely empty.
     */
    public BoardModel() {
        this.board = new CellStatus[ConfConstants.NUM_ROWS][ConfConstants.NUM_COLS];

        initBoardModel();
    }

    /**
     * Initializes board to be empty.
     * TODO potentially change this so that preconfigured locations can be added in.
     */
    public void initBoardModel() {
        for (int i = 0; i < ConfConstants.NUM_ROWS; i++) {
            for (int j = 0; j < ConfConstants.NUM_COLS; j++) {
                board[i][j] = CellStatus.EMPTY;
            }
        }
    }

    /**
     * Retrieves the board data for storing when the run button is clicked.
     *
     * @return The data of the original board.
     */
    public Object[][] getBoardData() {
        return board;
    }

    public void setBoardData(Object[][] boardData) {
        this.board = boardData;
    }

    /**
     * Retrieves the state of a cell on the board.
     *
     * @param row Specified row in question.
     * @param col Specified column in question.
     * @return Status of the cell in question. Null if outside of scope
     */
    public CellStatus getCellStatus(int row, int col) {
        try {
            return (CellStatus)board[row][col];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Changes the cell status of the cell in question specified by the row and column.
     *
     * @param value Status to change the cell in question to.
     * @param row Row in question.
     * @param col Column in question.
     */
    public void setCellStatus(Object value, int row, int col) {
        this.board[row][col] = value;
    }

    /**
     * Computes a deep copy of the board array for utilizing in the "save" feature of the application.
     *
     * @return A deep copy of the board configuration.
     */
    public CellStatus[][] deepCopyBoardData() {
        Object[][] boardCopy = new CellStatus[ConfConstants.NUM_ROWS][ConfConstants.NUM_COLS];

        for (int i = 0; i < ConfConstants.NUM_COLS; i++) {
            boardCopy[i] = Arrays.copyOf(board[i], ConfConstants.NUM_ROWS);
        }

        return (CellStatus[][])boardCopy;
    }
}
