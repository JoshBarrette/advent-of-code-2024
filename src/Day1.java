import java.util.*;

public class Day1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        int total = 0;

        while (true) {
            String temp = scan.nextLine();
            if (temp.isEmpty()) {
                break;
            }

            String[] split = temp.split(" {3}");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }

        Collections.sort(left);
        Collections.sort(right);

        for (int i = 0; i < left.size(); i++) {
            total += Math.abs(left.get(i) - right.get(i));
        }

        System.out.println("total: " + total);
    }
}
