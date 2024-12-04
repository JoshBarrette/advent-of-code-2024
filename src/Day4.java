import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    static ArrayList<Character> word = new ArrayList<>();
    static int total = 0;
    static char[][] matrix;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<char[]> matrixList = new ArrayList<>();
        word.add('M');
        word.add('S');

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
                if (matrix[row][col] == 'A') {
                    helper(row, col);
                }
            }
        }
    }

    public static void helper(int row, int col) {
        if (row < 1 || row > matrix.length - 2 || col < 1 || col > matrix[0].length - 2) {
            return;
        }

        char topLeft = matrix[row - 1][col - 1];
        char topRight = matrix[row - 1][col + 1];

        char bottomLeft = matrix[row + 1][col - 1];
        char bottomRight = matrix[row + 1][col + 1];

        if (!word.contains(topLeft) || !word.contains(topRight) ||
                !word.contains(bottomLeft) || !word.contains(bottomRight)) {
            return;
        }

        if (topLeft == topRight && bottomLeft == bottomRight && topLeft != bottomLeft) {
            total++;
        } else if (topLeft == bottomLeft && topRight == bottomRight && topLeft != topRight) {
            total++;
        }
    }
}
