import java.util.Arrays;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        long total = 0;

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty()) {
                break;
            }

            long goal = Long.parseLong(tempLine.split(": ")[0]);
            long[] nums = Arrays.stream(tempLine.split(": ")[1]
                    .split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();

            if (parse(goal, 0, nums, 0)) {
                total += goal;
            }
        }

        System.out.println("total: " + total);
    }

    public static boolean parse(long goal, long current, long[] nums, int startIdx) {
        if (startIdx == nums.length && goal == current) {
            return true;
        }else if (startIdx >= nums.length) {
            return false;
        } else if (parse(goal, current + nums[startIdx], nums, startIdx + 1)) {
            return true;
        } else if (parse(goal, current * nums[startIdx], nums, startIdx + 1)) {
            return true;
        }

        return false;
    }
}
