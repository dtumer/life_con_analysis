package views;

import models.BoardModel;
import models.CellStatus;
import models.ConfConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.net.URL;

/**
 * Board model for handling graphics changes. Utilizing an underlying board data model
 * to grab data from each cell and update the view accordingly. This is basically an
 * implementation of the Obeserver/Observable pattern.
 *
 * Created by Deniz on 7/14/2015.
 */
public class LifeBoard extends AbstractTableModel {

    //underlying data model
    private BoardModel boardModel;
    //image icon of the image to utilize in each cell that is LIVE
    //TODO change this to either an icon that is drawable or figure out how to utilize local images.
    private ImageIcon resident;

    /**
     * Constructor for creating the connection between gui and model.
     *
     * @param boardModel The underlying data model for the board.
     */
    public LifeBoard(BoardModel boardModel) {
        this.boardModel = boardModel;

        //creates teh image icon
        //TODO get rid of this and change to something more meaningful
        try {
            this.resident = new ImageIcon(ImageIO.read(new URL("http://remodelingclay.com/wp-content/uploads/2013/05/large_white_box_grayborder.gif")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves number of rows.
     *
     * @return number of rows defined by the configuration constants.
     */
    @Override
    public int getRowCount() {
        return ConfConstants.NUM_ROWS;
    }

    /**
     * Retrieves number of columns.
     *
     * @return number of columns defined by the configuration constants.
     */
    @Override
    public int getColumnCount() {
        return ConfConstants.NUM_COLS;
    }

    /**
     * Retrieves the image value at the specified cell.
     *
     *  @param rowIndex Row of cell in question.
     * @param columnIndex Column of cell in question.
     * @return Image object of cell. Returns null if empty.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CellStatus status = boardModel.getCellStatus(rowIndex, columnIndex);

        if (status == CellStatus.LIVE) {
            return resident;
        }

        return null;
    }

    /**
     * Sets the value of a specified cell. Not used and probably shouldnt be.
     *
     * @param value
     * @param rowIndex
     * @param columnIndex
     * TODO check if this is actually being used and get rid of it if not.
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        boardModel.setCellStatus(value, rowIndex, columnIndex);
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
