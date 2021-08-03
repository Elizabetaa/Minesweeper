import java.util.Arrays;

public class MinesweeperImpl implements Minesweeper {
    private int[][] board;
    private char[][] boardForShown;
    private boolean[][] visited;
    private int mines;
    private boolean firstInput = true;
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setFirstInput(boolean firstInput) {
        this.firstInput = firstInput;
    }

    public boolean isWin() {
        int minesCount = 0;
        for (char[] ints : boardForShown) {
            for (int anInt : ints) {
                if (anInt == '-') {
                    minesCount++;
                    if (minesCount > level) {
                        return false;
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == -1) {
                    boardForShown[i][j] = '*';
                }
            }
        }
        return true;
    }

    public void addMine() {
        int row = (int) (Math.random() * (-board.length) + board.length);
        int col = (int) (Math.random() * (-board.length) + board.length);

        if (board[row][col] != -1 && !visited[row][col]) {
            board[row][col] = -1;
        } else {
            addMine();
        }
    }

    public void findNeighbours(int row, int col) {
        if (!isValidPosition(row, col)) return;
        if (board[row][col] == 0 && !isVisited(row,col)) {
            boardForShown[row][col] = '0';
            visited[row][col] = true;
            findNeighbours(row + 1, col);
            findNeighbours(row - 1, col);
            findNeighbours(row, col - 1);
            findNeighbours(row, col + 1);
        } else {
            if (!visited[row][col]) {
                boardForShown[row][col] = String.valueOf(board[row][col]).charAt(0);
                visited[row][col] = true;
            }
            return;
        }
    }

    public boolean isVisited(int row, int col) {
        return visited[row][col];
    }

    public void fillMinesToBoardToShown() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == -1) {
                    boardForShown[row][col] = '*';
                }
            }
        }
    }

    public boolean isValidPosition(int i, int j) {
        return i <= board.length - 1 && i >= 0 && j <= board.length - 1 && j >= 0;
    }

    public void initCells() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != -1) {
                    if (isValidPosition(i - 1, j - 1)) {
                        if (board[i - 1][j - 1] == -1) {
                            board[i][j]++;
                        }
                    }
                    if (isValidPosition(i - 1, j)) {
                        if (board[i - 1][j] == -1) {
                            board[i][j]++;
                        }
                    }
                    if (isValidPosition(i - 1, j + 1)) {
                        if (board[i - 1][j + 1] == -1) {
                            board[i][j]++;
                        }
                    }
                    if (isValidPosition(i, j - 1)) {
                        if (board[i][j - 1] == -1) {
                            board[i][j]++;
                        }
                    }

                    if (isValidPosition(i, j + 1)) {
                        if (board[i][j + 1] == -1) {
                            board[i][j]++;
                        }
                    }
                    if (isValidPosition(i + 1, j - 1)) {
                        if (board[i + 1][j - 1] == -1) {
                            board[i][j]++;
                        }
                    }
                    if (isValidPosition(i + 1, j)) {
                        if (board[i + 1][j] == -1) {
                            board[i][j]++;
                        }
                    }
                    if (isValidPosition(i + 1, j + 1)) {
                        if (board[i + 1][j + 1] == -1) {
                            board[i][j]++;
                        }
                    }
                }

            }
        }
    }

    public void initMines() {
        for (int i = 0; i < mines; i++) {
            int row = (int) (Math.random() * (-board.length) + board.length);
            int col = (int) (Math.random() * (-board.length) + board.length);

            if (board[row][col] != -1) {
                board[row][col] = -1;

            } else {
                i--;
                continue;
            }

        }
    }

    public void showBoard() {
        System.out.println("Current status of board:");
        System.out.print(" ");
        for (int row = 0; row < boardForShown.length; row++) {
            if (row > 9) {
                System.out.print(" " + row);
            } else {
                System.out.print("  " + row);
            }
        }
        System.out.println();
        for (int row = 0; row < boardForShown.length; row++) {
            if (row > 9) {
                System.out.print(row + "  ");
            } else {
                System.out.print(row + "   ");

            }
            for (int col = 0; col < boardForShown[row].length; col++) {
                System.out.print(boardForShown[row][col] + "  ");
            }
            System.out.println();
        }
    }

    public void initBoards() {
        switch (level) {
            case 0:
                board = new int[9][9];
                boardForShown = new char[9][9];
                visited = new boolean[9][9];
                mines = 10;
                break;
            case 1:
                board = new int[16][16];
                boardForShown = new char[16][16];
                visited = new boolean[16][16];
                mines = 40;
                break;
            case 2:
                board = new int[24][24];
                boardForShown = new char[24][24];
                visited = new boolean[24][24];
                mines = 99;
                break;
        }
        for (char[] chars : boardForShown) {
            Arrays.fill(chars, '-');
        }

    }

    public boolean move(int row, int col) {
        int value = board[row][col];
        if (value == -1) {
            if (firstInput) {
                visited[row][col] = true;
                addMine();
                firstInput = false;
                board[row][col] = 0;
                initCells();
                value = board[row][col];
                boardForShown[row][col] = String.valueOf(value).charAt(0);
                return false;
            }
            fillMinesToBoardToShown();
            return true;
        }
        if (firstInput) {
            initCells();
            value = board[row][col];
        }


        if (value == 0) {
            findNeighbours(row, col);
        }
        boardForShown[row][col] = String.valueOf(value).charAt(0);
        visited[row][col] = true;

        return false;
    }
}
