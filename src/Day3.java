import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
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
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String l = line.substring(matcher.start() + 4, line.indexOf(')', matcher.start() + 4));
            String[] nums = l.split((","));
            total += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
        }

        return total;
    }
}
