package controllers;

import models.AppModel;
import models.BoardModel;
import models.CellStatus;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Deniz on 7/14/2015.
 */
public class AppController {

    //contains data about the application model
    private AppModel gameModel;
    private boolean isRunning;
    private Timer timer;

    /**
     * TODO finish docs
     */
    public AppController() {
        gameModel = new AppModel();
    }

    /**
     * Retrieces main app model class.
     *
     * @return The representation of the main model class.
     */
    public AppModel getGameModel() {
        return gameModel;
    }

    /**
     * Resets the view and clears all living matter in the table.
     */
    public void resetLifeTable() {
        this.getGameModel().getBoardModel().initBoardModel();
    }

    /**
     * Runs the application by flagging the model for execution.
     */
    public void run() {
        this.isRunning = true;
        this.timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getGameModel().calculateGeneration();
            }
        }, 0, 1000);
    }

    public void stop() {
        this.isRunning = false;
        timer.cancel();
    }

    /**
     * TODO finish this doc.
     * @return
     */
    private boolean isRunning() {
        return isRunning;
    }


    /**
     * Handles changing the status of a cell on the board.
     *
     * @param row Row of cell in question.
     * @param col Column of cell in question.
     */
    public void changeBoardCellStatus(int row, int col) {
        CellStatus status = this.getGameModel().getBoardModel().getCellStatus(row, col);

        if (status == CellStatus.EMPTY) {
            this.getGameModel().getBoardModel().setCellStatus(CellStatus.LIVE, row, col);
        }
        else if (status == CellStatus.LIVE) {
            this.getGameModel().getBoardModel().setCellStatus(CellStatus.EMPTY, row, col);
        }
    }
}
