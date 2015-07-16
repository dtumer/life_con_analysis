package models;

import controllers.AppController;

import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by Deniz on 7/15/2015.
 */
public class RunTask extends TimerTask {

    private AppController controller;
    private int generationCounter;

    public RunTask(AppController controller) {
        this.controller = controller;
        this.generationCounter = 0;
    }

    @Override
    public void run() {
        ArrayList<Point> changedCells = controller.getGameModel().calculateGeneration();

        for (int i = 0; i < changedCells.size(); i++) {

            controller.changeBoardCellStatus(changedCells.get(i).x, changedCells.get(i).y);
        }

        //print generations
        System.out.println(++this.generationCounter);

        controller.update();
    }

    /**
     * Retrieves generation counter information.
     *
     * @return
     */
    public int getGenerationCounter() {
        return this.generationCounter;
    }
}
