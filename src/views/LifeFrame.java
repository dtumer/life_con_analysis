package views;

import models.BoardModel;
import models.ConfConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Deniz on 7/14/2015.
 */
public class LifeFrame extends JFrame {

    private Scene gameScene;
    private BoardModel model;
    private ControlPanel ctrlPanel;
    private JMenuBar menu;
    private int selectedRow;
    private int selectedCol;

    /* Listener to respond to mouse clicks on the table */
    private MouseAdapter myMouseListener = new MouseAdapter()
    {
        public void mouseReleased(MouseEvent ev)
        {
            // obtain the selected cell coordinates
            selectedRow = gameScene.getSelectedRow();
            selectedCol = gameScene.getSelectedColumn();

            //System.out.println("ROW: " + selectedRow + ", COL: " + selectedCol);
            model.changeCellStatus(selectedRow, selectedCol);
            repaint();
        }
    };

    /**
     * Constructor for initializing the gui display for life.
     */
    public LifeFrame() {
        super();

        model = new BoardModel();

        initLayout();
        pack();

        //centers the window, must be called after pack()
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initializes the layout of the gui including adding the menus and content layouts to the
     * overall gui design. Also initializes the board model and abstract table model
     * that handles the repainting of the gui.
     */
    private void initLayout() {
        //set layout to vertical
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        //set title and menu bar
        setTitle("Conway's Game of Life - " + ConfConstants.VERSION);
        initMenu();
        getContentPane().add(menu, BorderLayout.PAGE_START);

        //set values associated with the scene panel including mouse listeners
        //and cell size
        gameScene = new Scene(model, Color.DARK_GRAY);
        gameScene.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gameScene.addMouseListener(myMouseListener);
        gameScene.setCellSelectionEnabled(false);
        gameScene.setShowGrid(true);
        setCellSize();
        getContentPane().add(gameScene, BorderLayout.CENTER);

        //control panel setup
        initControlPanel();
        getContentPane().add(ctrlPanel, BorderLayout.PAGE_END);
    }

    /**
     * Set cell size data for the board.
     */
    private void setCellSize() {
        gameScene.setRowHeight(ConfConstants.CELL_SIZE);

        for (int col = 0; col < ConfConstants.NUM_COLS; col++)
        {
            TableColumn column = gameScene.getColumnModel().getColumn(col);
            column.setMaxWidth(ConfConstants.CELL_SIZE);
            column.setMinWidth(ConfConstants.CELL_SIZE);
        }
    }

    /**
     * Initialize the bottom control panel.
     * TODO add start and stop buttons for game
     */
    private void initControlPanel() {
        this.ctrlPanel = new ControlPanel();
    }

    /**
     * Initializes the menu bar.
     * TODO Section for initializing the board based on interesting configurations
     */
    private void initMenu() {
        this.menu = new JMenuBar();
        JMenu file = new JMenu("File");

        file.add("Edit");

        menu.add(file);
    }
}
