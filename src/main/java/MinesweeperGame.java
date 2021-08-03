import java.util.Scanner;

public class MinesweeperGame {
    private static Scanner scanner;
    private static MinesweeperImpl minesweeper;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        minesweeper = new MinesweeperImpl();

        System.out.println("Enter thr Difficulty Level");
        System.out.println("Press 0 for BEGINNER (9 * 9 Cells and 10 Mines)");
        System.out.println("Press 1 for INTERMEDIATE (16 * 16 Cells and 40 Mines)");
        System.out.println("Press 2 for BEGINNER (24 * 24 Cells and 99 Mines)");

        int level = Integer.parseInt(scanner.nextLine());

        while (level < 0 || level > 2) {
            System.out.println("Invalid level! The level must be in range 0 - 2");
            level = Integer.parseInt(scanner.nextLine());
        }

        minesweeper.setLevel(level);
        minesweeper.initBoards();
        minesweeper.showBoard();

        boolean lost = false;

        minesweeper.initMines();

        while (!lost) {

            lost = play();
            if (minesweeper.isWin()) {
                System.out.println("You win!");
            }
            if (!lost) {
                minesweeper.setFirstInput(false);
                minesweeper.showBoard();
            }
        }
        minesweeper.showBoard();
        System.out.println("You lost!");
    }

    private static boolean play() {
        System.out.println("Enter your movie (row column)");
        System.out.print("->");
        int[] inputs = readInput();

        while (!minesweeper.isValidPosition(inputs[0], inputs[1])) {
            System.out.printf("Invalid row and column! Row and Column must be in range of 0 to %d in format (row column).%n", minesweeper.getLevel());
            System.out.println("Enter your movie (row column)");
            System.out.print("->");
            inputs = readInput();
        }
        if (minesweeper.isVisited(inputs[0], inputs[1])) {
            System.out.println("The cell is already visited!");
            System.out.println("Enter your movie (row column)");
            System.out.print("->");
            inputs = readInput();
        }

        int row = inputs[0];
        int col = inputs[1];

        return minesweeper.move(row, col);

    }

    private static int[] readInput() {
        int row = Integer.parseInt(String.valueOf(scanner.nextInt()));
        int col = Integer.parseInt(String.valueOf(scanner.nextInt()));
        return new int[]{row, col};
    }

}
