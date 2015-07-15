package views;

import models.BoardModel;
import models.ConfConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Deniz on 7/14/2015.
 */
public class Scene extends JTable {

    private static final int WIDTH = ConfConstants.CELL_SIZE * ConfConstants.NUM_COLS;
    private static final int HEIGHT = ConfConstants.CELL_SIZE * ConfConstants.NUM_ROWS;

    /**
     * Constructor for creating the background grid pattern and making it usable.
     *
     * @param boardModel The underlying model that contains cell data for the abstract table model to process.
     * @param sceneColor Background color of the scene.
     */
    public Scene(BoardModel boardModel, Color sceneColor) {
        super(new LifeBoard(boardModel));

        setBackground(sceneColor);

        //sets up grid layout for displaying the grid
        setLayout(new GridLayout(ConfConstants.NUM_ROWS, ConfConstants.NUM_COLS));

        //set size of table and make the program display fixed
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    /**
     * Retrieves class of columns. Needed for AbstractTableModel to recognize the columns of the
     * table and update information appropriately.
     *
     * @param column The column in question.
     * @return Class of the underlying model columns.
     */
    @Override
    public Class getColumnClass(int column)
    {
        return ImageIcon.class;
    }
}
