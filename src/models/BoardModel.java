package models;

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
    private void initBoardModel() {
        for (int i = 0; i < ConfConstants.NUM_ROWS; i++) {
            for (int j = 0; j < ConfConstants.NUM_COLS; j++) {
                board[i][j] = CellStatus.EMPTY;
            }
        }
    }

    /**
     * Retrieves the state of a cell on the board.
     *
     * @param row Specified row in question.
     * @param col Specified column in question.
     * @return Status of the cell in question.
     */
    public CellStatus getCellStatus(int row, int col) {
        return (CellStatus)this.board[row][col];
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
     * Switches the state of a cell. If cell is empty it becomes live and visa-versa.
     *
     * @param row Row of cell in question.
     * @param col Column of cell in question.
     */
    public void changeCellStatus(int row, int col) {
        CellStatus status = getCellStatus(row, col);

        if (status == CellStatus.EMPTY) {
            setCellStatus(CellStatus.LIVE, row, col);
        }
        else if (status == CellStatus.LIVE) {
            setCellStatus(CellStatus.EMPTY, row, col);
        }
    }
}
