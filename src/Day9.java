import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day9 {
    private static ArrayList<int[]> arr = new ArrayList<>(); // [isData, fileID]
    private static boolean isData = true;
    private static int fileID = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty()) {
                break;
            }

            Arrays.stream(tempLine.split(""))
                    .forEach((s) -> parseCell(Integer.parseInt(s)));
        }

        printData();
        reOrderData();
        printData();
        System.out.println("total: " + countData());
    }

    public static long countData() {
        long total = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i)[0] == 0) {
                return total;
            } else {
                total += (long) i * arr.get(i)[1];
            }
        }

        return total;
    }

    public static void reOrderData() {
        int left = 0;
        int right = arr.size() - 1;

        while (left < right) {
            if (arr.get(left)[0] == 1) {
                left++;
                continue;
            } else if (arr.get(right)[0] == 0) {
                right--;
                continue;
            }

            int[] temp = arr.get(left);
            arr.set(left, arr.get(right));
            arr.set(right, temp);
            left++;
            right--;
        }
    }

    public static void parseCell(int cell) {
        for (int i = 0; i < cell; i++) {
            if (isData) {
                arr.add(new int[]{1, fileID});
            } else {
                arr.add(new int[]{0, 0});
            }
        }

        if (isData) {
            fileID++;
        }

        isData = !isData;
    }

    public static void printData() {
        for (int[] item : arr) {
            System.out.print(Arrays.toString(item) + ", ");
        }
        System.out.println();
    }
}
