import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;

public class MinesweeperTest {

    int[][] board = new int[9][9];
    char[][] boardForShown = new char[9][9];
    boolean[][] visited = new boolean[9][9];;
    int mines;
    MinesweeperImpl minesweeper = new MinesweeperImpl(board, boardForShown, visited, 0, mines);


    @Before
    public void setUp() {
        minesweeper = new MinesweeperImpl(board, boardForShown, visited, 0, 10);
    }

    @Before
    public void seedDataInBoard() {

        for (int[] ints : board) {
            Arrays.fill(ints, 0);
        }
        for (char[] chars : boardForShown) {
            Arrays.fill(chars, '-');
        }
    }


    @Test
    public void levelMustSetCorrectValue() {
        minesweeper.setLevel(0);
        Assert.assertEquals(0, minesweeper.getLevel());
        minesweeper.setLevel(1);
        Assert.assertEquals(1, minesweeper.getLevel());
    }

    @Test
    public void isWinWorkCorrectForAllOpenedCells() {
        for (int i = 0; i < boardForShown.length; i++) {
            for (int j = 0; j < boardForShown[i].length; j++) {
                if (i != 0) {
                    boardForShown[i][j] = '0';
                }
            }
        }
        minesweeper.setMines(10);
        Assert.assertTrue(minesweeper.isWin());
    }

    @Test
    public void IsWinShouldReturnFalseFor11UnopenedCells() {
        for (int i = 0; i < boardForShown.length; i++) {
            for (int j = 0; j < boardForShown[i].length; j++) {
                    boardForShown[i][j] = '0';
                    if (i == 0){
                        boardForShown[i][j] = '-';
                    }
            }
        }
        boardForShown[1][0]='-';
        boardForShown[1][1]='-';
        Assert.assertFalse(minesweeper.isWin());
    }

    @Test
    public void AddMineShouldAddMine() {
        minesweeper.addMine();
        boolean isContain = false;

        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == -1) {
                    isContain = true;
                }
            }
        }

        Assert.assertTrue(isContain);
    }

    @Test
    public void fillMinesToBoardShouldAdd10Mines() {
        minesweeper.setMines(10);
        minesweeper.initMines();
        int minesCount = 0;
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == -1) {
                    minesCount++;
                }
            }
        }

        Assert.assertEquals(10, minesCount);
    }


}
