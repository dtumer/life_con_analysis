package models;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Deniz on 7/14/2015.
 */
public class AppModel {
    private BoardModel boardModel;
    private BoardConfigManager boardManager;

    public AppModel() {
        boardModel = new BoardModel();
        boardManager = new BoardConfigManager();
    }

    /**
     * Retrieves underlying board data model.
     *
     * @return Underlying board model.
     */
    public BoardModel getBoardModel() {
        return boardModel;
    }

    /**
     * Retrieves the board manager for the configuration of the board.
     *
     * @return The manager for the board configuration.
     */
    public BoardConfigManager getBoardManager() {
        return boardManager;
    }

    /**
     * TODO finish docs
     */
    public ArrayList<Point> calculateGeneration() {
        Point currentLocation;
        ArrayList<Point> changedCells = new ArrayList<>();

        //System.out.println("Calculating generational evolution...");

        for (int row = 0; row < ConfConstants.NUM_ROWS; row++) {
            for (int col = 0; col < ConfConstants.NUM_COLS; col++) {
                currentLocation = new Point(row, col);

                if (isCellChanged(getLiveNeighbors(currentLocation), getBoardModel().getCellStatus(row, col))) {
                    changedCells.add(currentLocation);
                }
            }
        }

        return changedCells;
    }

    /**
     * Retrieves all the live neighbors of the cell in question.
     * TODO probably change this to return only an int instead of creating an arraylist.
     *
     * @param loc Location of the cell in question to find neighbors for.
     * @return List containing all live neighbors
     */
    private ArrayList<CellStatus> getLiveNeighbors(Point loc) {
        CellStatus tempNeighbor;
        ArrayList<CellStatus> liveNeighbors = new ArrayList<>();

        for (NeighborDirection dir : NeighborDirection.values()) {
            tempNeighbor = getNeighbor(dir, loc.x, loc.y);
            if (tempNeighbor != null && tempNeighbor == CellStatus.LIVE) {
                liveNeighbors.add(tempNeighbor);
            }
        }

        return liveNeighbors;
    }

    /**
     * Checks if a cell needs to be changed given the rules of the game.
     *
     * @param liveNeighbors
     * @param curCellStatus
     * @return
     */
    private boolean isCellChanged(ArrayList<CellStatus> liveNeighbors, CellStatus curCellStatus) {
        boolean isChanged = false;

        if (curCellStatus == CellStatus.LIVE) {
            //Rule 1: Any live cell with fewer than two live neighbours dies, as if caused by under-population.
            if (liveNeighbors.size() < 2) {
                //System.out.println("Death by under-pop");
                isChanged = true;
            }
            //Rule 2: Any live cell with two or three live neighbours lives on to the next generation.
            //don't need to change anything
            //Rule 3: Any live cell with more than three live neighbours dies, as if by overcrowding.
            else if (liveNeighbors.size() > 3) {
                //System.out.println("Death by over-pop");
                isChanged = true;
            }
        }
        else {
            //Rule 4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
            if (liveNeighbors.size() == 3) {
                //System.out.println("Reproduction");
                isChanged = true;
            }
        }

        return isChanged;
    }

    /**
     * TODO finish doc
     * @param dir
     * @param row
     * @param col
     * @return
     */
    private CellStatus getNeighbor(NeighborDirection dir, int row, int col) {
       CellStatus status;

        switch (dir) {
           case NORTH:
               status = getBoardModel().getCellStatus(row - 1, col);
               break;
           case EAST:
               status = getBoardModel().getCellStatus(row, col + 1);
               break;
           case SOUTH:
               status = getBoardModel().getCellStatus(row + 1, col);
               break;
           case WEST:
               status = getBoardModel().getCellStatus(row, col - 1);
               break;
           case N_WEST:
               status = getBoardModel().getCellStatus(row - 1, col - 1);
               break;
           case N_EAST:
               status = getBoardModel().getCellStatus(row - 1, col + 1);
               break;
           case S_WEST:
               status = getBoardModel().getCellStatus(row + 1, col - 1);
               break;
           case S_EAST:
               status = getBoardModel().getCellStatus(row + 1, col + 1);
               break;
           default:
               status = null;
               break;
       }

        return status;
    }
}
