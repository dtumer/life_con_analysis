package views;

import controllers.AppController;
import models.BoardModel;
import models.ConfConstants;
import models.RunMode;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Deniz on 7/14/2015.
 */
public class LifeFrame extends JFrame {

    private Scene gameScene;
    private AppController controller;
    private JMenuBar menu;
    private JPanel controlPanel;
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
            controller.changeBoardCellStatus(selectedRow, selectedCol);
            repaint();
        }
    };

    /**
     * Constructor for initializing the gui display for life.
     */
    public LifeFrame(AppController controller) {
        super();

        this.controller = controller;

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
        gameScene = new Scene(controller.getGameModel().getBoardModel(), ConfConstants.BKGD_COLOR);
        gameScene.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gameScene.addMouseListener(myMouseListener);
        gameScene.setCellSelectionEnabled(false);
        gameScene.setShowGrid(true);
        setCellSize();
        getContentPane().add(gameScene, BorderLayout.CENTER);

        //control panel setup
        initControlPanel();
        getContentPane().add(controlPanel, BorderLayout.PAGE_END);
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
     */
    private void initControlPanel() {
        controlPanel = new JPanel();

        JToggleButton startPause = new JToggleButton("Start");
        startPause.addActionListener(e -> {
            if (e.getActionCommand().equals("Start")) {
                controller.run(ConfConstants.TIME_STEP);
                startPause.setText("Pause");
            }
            else {
                controller.pause();
                startPause.setText("Start");
            }
        });

        //stop button configuration
        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> {
            controller.stop();
            startPause.setText("Start");

            repaint();
        });

        //reset button configuration
        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> {
            controller.resetLifeTable();
            startPause.setText("Start");

            repaint();
        });

        JLabel generationIntervalLabel = new JLabel("Time Interval");
        JSpinner generationInterval = new JSpinner();
        generationInterval.setValue(ConfConstants.TIME_STEP);
        generationInterval.setPreferredSize(new Dimension(100, 30));
        generationInterval.addChangeListener(l -> {
            controller.stop();
            controller.run((Integer)generationInterval.getValue());
        });

        controlPanel.add(startPause);
        controlPanel.add(stop);
        controlPanel.add(reset);
        controlPanel.add(generationIntervalLabel);
        controlPanel.add(generationInterval);
    }

    /**
     * Initializes the menu bar.
     * TODO Section for initializing the board based on interesting configurations
     */
    private void initMenu() {
        this.menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");

        /* EDIT MENU OPTIONS SETUP */
        JMenuItem lifeReset = new JMenuItem("Reset Table");
        lifeReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.resetLifeTable();
                repaint();
            }
        });
        edit.add(lifeReset);

        menu.add(file);
        menu.add(edit);
    }
}
