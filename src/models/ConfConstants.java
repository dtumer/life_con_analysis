package models;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Deniz on 7/14/2015.
 */
public class ConfConstants {
    public static final String VERSION = "v0.1";

    /** Board constants */
    //defines the number of columns on the board
    public static final int NUM_COLS = 30;
    //defines the number of rows on the board
    public static final int NUM_ROWS = 30;
    //defines the width and height of a cell (cells are squares)
    public static final int CELL_SIZE = 20;
    //defines background color
    public static final Color BKGD_COLOR = Color.GRAY;
    //defines grid color
    public static final Color GRID_COLOR = Color.GRAY;

    /** Life Constants */
    public static final int TIME_STEP = 100;
}
