import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    static char[] word = {'X', 'M', 'A', 'S'};
    static int total = 0;
    static char[][] matrix;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<char[]> matrixList = new ArrayList<>();

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty()) {
                break;
            }

            matrixList.add(tempLine.toCharArray());
        }

        matrix = new char[matrixList.size()][];
        for (int i = 0; i < matrixList.size(); i++) {
            matrix[i] = matrixList.get(i);
        }

        parse();
        System.out.println("total: " + total);
    }

    public static void parse() {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == word[0]) {
                    helper(row, col, -1, 0, 0);
                    helper(row, col, -1, -1, 0);
                    helper(row, col, -1, 1, 0);

                    helper(row, col, 1, 0, 0);
                    helper(row, col, 1, -1, 0);
                    helper(row, col, 1, 1, 0);

                    helper(row, col, 0, 1, 0);
                    helper(row, col, 0, -1, 0);
                }
            }
        }
    }

    public static void helper(int row, int col, int xOffset, int yOffset, int wordIdx) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return;
        } else if (wordIdx >= word.length || matrix[row][col] != word[wordIdx]) {
            return;
        } else if (wordIdx == word.length - 1) {
            total++;
            return;
        }

        boolean vertical = row + yOffset < matrix.length && row + yOffset >= 0;
        boolean horizontal = col + xOffset < matrix[0].length && col + xOffset >= 0;

        if (vertical && horizontal) {
            helper(row + yOffset, col + xOffset, xOffset, yOffset, wordIdx + 1);
        }
    }
}
