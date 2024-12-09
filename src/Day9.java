import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day9 {
    private static ArrayList<int[]> arr = new ArrayList<>(); // [isData, fileID, size]
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

//        printData();
        reOrderDataPart2();
//        printData();
        System.out.println("total: " + countData());
    }

    public static long countData() {
        long total = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i)[0] == 1) {
                total += (long) i * (long) arr.get(i)[1];
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

    public static void reOrderDataPart2() {
        for (int right = arr.size() - 1; right >= 0; right--) {
            int[] rightItem = arr.get(right);
            int size = rightItem[2];

            if (rightItem[0] == 0) continue;

            for (int left = 0; left < right - size; left++) {
                int[] leftItem = arr.get(left);

                if (leftItem[0] == 1 || leftItem[2] < size) {
                    continue;
                }

                int emptySpace = leftItem[2];

                for (int idx = 0; idx < size; idx++) {
                    int[] temp = arr.get(left + idx);
                    arr.set(left + idx, arr.get(right - idx));
                    arr.set(right - idx, temp);
                }

                for (int i = left + size; i < left + emptySpace; i++) {
                    if (arr.get(i)[0] == 1) break;
                    arr.get(i)[2] -= size;
                }

                right -= size - 1;

                break;
            }
        }
    }

    public static void parseCell(int cell) {
        for (int i = 0; i < cell; i++) {
            if (isData) {
                arr.add(new int[]{1, fileID, cell});
            } else {
                arr.add(new int[]{0, 0, cell});
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
