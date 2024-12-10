import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Day10 {
    private static ArrayList<ArrayList<Integer>> grid = new ArrayList<>();
    private static int total = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty()) {
                break;
            }

            parseLine(tempLine);
        }

        count();
//        printGrid();
        System.out.println("total: " + total);
    }

    private static void count() {
        for (int row = 0; row < grid.size(); row++) {
            for (int col = 0; col < grid.getFirst().size(); col++) {
                if (grid.get(row).get(col) == 0) {
                    HashSet<String> set = new HashSet<>();
                    helper(row, col, 0, set);
                }
            }
        }
    }

    private static void helper(int row, int col, int current, HashSet<String> set) {
        if (row < 0 || row >= grid.size() || col < 0 || col >= grid.getFirst().size()) {
            return;
        } else if (grid.get(row).get(col) != current) {
            return;
        } else if (current == 9) {
            total++;
            return;
        }

        helper(row, col - 1, current + 1, set);
        helper(row, col + 1, current + 1, set);
        helper(row + 1, col, current + 1, set);
        helper(row - 1, col, current + 1, set);
    }

    private static void parseLine(String line) {
        ArrayList<Integer> arr = new ArrayList<>();
        Arrays.stream(line.split("")).forEach((s) ->
            arr.add(Integer.parseInt(s))
        );
        grid.add(arr);
    }

    private static void printGrid() {
        for (ArrayList<Integer> arr : grid) {
            for (Integer i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
