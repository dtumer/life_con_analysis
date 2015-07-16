package controllers;

import models.*;
import views.LifeFrame;

/**
 * Class that represents the main controller of the application. This controller hands off access to the models
 * as necessary and updates the view.
 *
 * Created by Deniz on 7/14/2015.
 */
public class AppController {

    private AppModel gameModel;
    private boolean isAppCalcRunning;
    private LifeFrame gui;
    private LifeAppTimeScheduler scheduler;

    /**
     * Constructor for creating a new instance of the app controller.
     */
    public AppController() {
        gameModel = new AppModel();
        isAppCalcRunning = false;
        scheduler = new LifeAppTimeScheduler();
    }

    /**
     * Retrieves main app model class.
     *
     * @return The representation of the main model class.
     */
    public AppModel getGameModel() {
        return gameModel;
    }

    /**
     * Sets the gui for the controller.
     *
     * @param frame GUI frame of the application.
     */
    public void setGUI(LifeFrame frame) {
        this.gui = frame;
    }

    public void update() {
        gui.repaint();
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
    public void run(int interval) {
        RunTask compute = new RunTask(this);

        System.out.println("Starting generational calculations...");

        //check if generation calculations are already running.
        //if they are not then set the original board state.
        if (!this.isAppCalcRunning) {
            getGameModel().getBoardManager().setBoardConfig(getGameModel().getBoardModel().deepCopyBoardData());
            this.isAppCalcRunning = true;
        }

        scheduler.start(compute, interval);
    }

    /**
     * Pauses the simulation by cancelling the timer.
     */
    public void pause() {
        scheduler.stop();
    }

    /**
     * Stops the simulation and resets to original placement of cells.
     */
    public void stop() {
        scheduler.stop();
        getGameModel().getBoardModel().setBoardData(getGameModel().getBoardManager().getBoardConfig());
        this.isAppCalcRunning = false;
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
