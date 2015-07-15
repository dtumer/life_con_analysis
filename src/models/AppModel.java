package models;

import java.util.ArrayList;

/**
 * Created by Deniz on 7/14/2015.
 */
public class AppModel {
    private BoardModel boardModel;

    public AppModel() {
        boardModel = new BoardModel();
    }

    /**
     * Retrieves underlying board data model.
     * TODO FINISH ALGORITHM
     * @return Underlying board model.
     */
    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void calculateGeneration() {
        CellStatus currentStatus;
        ArrayList<CellStatus> neighborStatuses = new ArrayList();

        for (int row = 0; row < ConfConstants.NUM_ROWS; row++) {
            for (int col = 0; col < ConfConstants.NUM_COLS; col++) {

            }
        }
    }
}
