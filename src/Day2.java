import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int total = 0;

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty()) {
                break;
            }

            String[] split = tempLine.split(" ");
            int[] temp = new int[split.length];
            for (int i =0; i < split.length; i++) {
                temp[i] = Integer.parseInt(split[i]);
            }

            if (reportIsSafe(temp)) {
                total++;
            }
        }

        System.out.println("total: " + total);
    }

    public static boolean reportIsSafe(int[] report) {
        if (report[0] == report[1]) return false;
        boolean isPos = report[0] - report[1] > 0;

        for (int i = 0; i < report.length - 1; i++) {
            int diff = report[i] - report[i + 1];
            int absDiff = Math.abs(report[i] - report[i + 1]);
            if (diff > 0 != isPos) {
                return false;
            } else if (absDiff < 1 || absDiff > 3) {
                return false;
            }
        }

        return true;
    }
}
