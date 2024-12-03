import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    private static boolean skip = false;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int total = 0;

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty()) {
                break;
            }

            total += parseLine(tempLine);
        }

        System.out.println("total: " + total);
    }

    public static int parseLine(String line) {
        int total = 0;
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");

        String subS;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'd') {
                subS = line.substring(i);

                if (subS.startsWith("don't()")) {
                    skip = true;
                } else if (subS.startsWith("do()")) {
                    skip = false;
                }
            } else if (line.charAt(i) == 'm' && !skip) {
                subS = line.substring(i);

                Matcher matcher = pattern.matcher(subS);
                if (matcher.find() && matcher.start() == 0) {
                    String l = subS.substring(4, subS.indexOf(')'));
                    String[] nums = l.split((","));
                    total += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
                }
            }
        }

        return total;
    }
}
