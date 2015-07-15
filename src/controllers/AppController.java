package controllers;

import models.AppModel;
import models.BoardModel;
import models.CellStatus;
import models.ConfConstants;
import views.LifeFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Deniz on 7/14/2015.
 */
public class AppController {

    //contains data about the application model
    private AppModel gameModel;
    private LifeFrame gui;
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
     * TODO finish docs, also change gui to not have the action listeners in it.
     * @param frame
     */
    public void setGUI(LifeFrame frame) {
        this.gui = frame;
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
        this.timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ArrayList<Point> changedCells = getGameModel().calculateGeneration();

                for (int i = 0; i < changedCells.size(); i++) {
                    changeBoardCellStatus(changedCells.get(i).x, changedCells.get(i).y);
                }

                gui.repaint();
            }
        }, 0, ConfConstants.TIME_STEP);
    }

    public void stop() {
        timer.cancel();
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
