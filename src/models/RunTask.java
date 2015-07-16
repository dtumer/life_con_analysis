package models;

import controllers.AppController;

import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by Deniz on 7/15/2015.
 */
public class RunTask extends TimerTask {

    private ArrayList<Point> changedCells;
    private AppController controller;

    public RunTask(ArrayList<Point> changedCells, AppController controller) {
        this.changedCells = controller.getGameModel().calculateGeneration();
        this.controller = controller;
    }

    @Override
    public void run() {
        for (int i = 0; i < changedCells.size(); i++) {
            controller.changeBoardCellStatus(changedCells.get(i).x, changedCells.get(i).y);
        }

        controller.update();
    }
}
