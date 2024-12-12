import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day12 {
    private static ArrayList<ArrayList<Character>> grid = new ArrayList<>();
    private static HashSet<String> visited = new HashSet<>();
    private static long fenceCounter = 0;
    private static long total = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty()) {
                break;
            }

            parseLine(tempLine);
        }

        printGrid();
        count();
        System.out.println("total: " + total);
    }

    private static void count() {
        for (int row = 0; row < grid.size(); row++) {
            for (int col = 0; col < grid.getFirst().size(); col++) {
                if (!visited.contains(row + "," + col)) {
                    handleRegion(row, col, grid.get(row).get(col));
                }
            }
        }
    }

    private static void handleRegion(int row, int col, char plant) {
        HashSet<String> set = new HashSet<>();
        fenceCounter = 0;

        handleRegionHelper(row, col, plant, set);

        total += fenceCounter * set.size();
    }

    private static void handleRegionHelper(int row, int col, char plant, HashSet<String> set) {
        if (row < 0 || row >= grid.size() || col < 0 || col >= grid.getFirst().size()) {
            fenceCounter++;
            return;
        } else if (plant != grid.get(row).get(col)) {
            fenceCounter++;
            return;
        } else if (set.contains(row + "," + col)) {
            return;
        }

        set.add(row + "," + col);
        visited.add(row + "," + col);

        handleRegionHelper(row - 1, col, plant, set);
        handleRegionHelper(row + 1, col, plant, set);
        handleRegionHelper(row, col - 1, plant, set);
        handleRegionHelper(row, col + 1, plant, set);
    }

    private static void printGrid() {
        for (ArrayList<Character> arr : grid) {
            for (Character c : arr) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    private static void parseLine(String line) {
        ArrayList<Character> arr = new ArrayList<>();
        for (String s : line.split("")) {
            arr.add(s.charAt(0));
        }
        grid.add(arr);
    }
}
