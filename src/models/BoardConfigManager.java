package models;

/**
 * Created by Deniz on 7/15/2015.
 */
public class BoardConfigManager {
    private static CellStatus[][] origBoardState;

    /**
     * Retrieves the original configuration of the board.
     *
     * @return The original configuration of the board.
     */
    public CellStatus[][] getBoardConfig() {
        return origBoardState;
    }

    /**
     * Sets the original configuration of the board.
     *
     * @param boardConfig The original configuration of the board.
     */
    public void setBoardConfig(CellStatus[][] boardConfig) {
        this.origBoardState = boardConfig;
    }
}
