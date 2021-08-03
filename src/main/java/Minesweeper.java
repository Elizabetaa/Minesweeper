public interface Minesweeper {
     boolean isWin();

    void addMine();

    void findNeighbours(int x, int y);

    void fillMinesToBoardToShown();

    boolean isValidPosition(int i, int j);

    void initCells();

    void initMines();

    void showBoard();

    void initBoards();

    boolean move(int row, int col);

}
