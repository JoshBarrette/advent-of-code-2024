import java.util.ArrayList;
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
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (String s : split) {
                temp.add(Integer.parseInt(s));
            }

            if (reportIsSafe(temp, false)) {
                total++;
            }
        }

        System.out.println("total: " + total);
    }

    public static boolean reportIsSafe(ArrayList<Integer> report, boolean levelRemoved) {
        boolean isPos = report.get(0) - report.get(1) > 0;

        for (int i = 0; i < report.size() - 1; i++) {
            int diff = report.get(i) - report.get(i + 1);
            int absDiff = Math.abs(report.get(i) - report.get(i + 1));
            
            if (diff > 0 == isPos && absDiff >= 1 && absDiff <= 3) {
                continue;
            }
            else if (levelRemoved) return false;
            else if (i == report.size() - 2) return true;

            ArrayList<Integer> temp;
            for (int j = 0; j < report.size(); j++) {
                temp = (ArrayList<Integer>) report.clone();
                temp.remove(j);
                if (reportIsSafe(temp, true)) {
                    return true;
                }
            }

            return false;
        }

        return true;
    }
}
