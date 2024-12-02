import java.util.*;

public class Day1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        HashMap<Integer, int[]> map = new HashMap<>();

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty()) {
                break;
            }

            String[] split = tempLine.split(" {3}");
            int left = Integer.parseInt(split[0]);
            int right = Integer.parseInt(split[1]);

            int[] tempLeft = map.get(left);
            if (tempLeft == null) {
                map.put(left, new int[] {1, 0});
            } else {
                map.replace(left, new int[] { tempLeft[0] + 1, tempLeft[1] });
            }

            int[] tempRight = map.get(right);
            if (tempRight == null) {
                map.put(right, new int[] {0, 1});
            } else {
                map.replace(right, new int[] { tempRight[0], tempRight[1] + 1 });
            }
        }

        int total = 0;
        for (int key : map.keySet()) {
            int[] thing = map.get(key);
            total += key * thing[0] * thing[1];
        }

        System.out.println("total: " + total);
    }
}
