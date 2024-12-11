import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String tempLine = scan.nextLine();
        ArrayList<String> stones = parse(tempLine);
        System.out.println("Initial: " + Arrays.toString(stones.toArray()));
        for (int i = 0; i < 25; i++) {
            blink(stones);
//            System.out.println("After " + i + 1 + " blinks: " + Arrays.toString(stones.toArray()));
        }

        System.out.println("Total stones: " + stones.size());
    }

    private static void blink(ArrayList<String> oldStones) {
        ArrayList<String> newStones = new ArrayList<>();
        for (String stone : oldStones) {
            if (Long.parseLong(stone) == 0) {
                newStones.add("1");
            } else if (stone.length() % 2 == 0) {
                newStones.add(Long.parseLong(stone.substring(0, stone.length() / 2)) + "");
                newStones.add(Long.parseLong(stone.substring(stone.length() / 2)) + "");
            } else {
                newStones.add((Long.parseLong(stone) * 2024) + "");
            }
        }

        oldStones.clear();
        oldStones.addAll(newStones);
    }

    private static ArrayList<String> parse(String stoneLine) {
        ArrayList<String> newStones = new ArrayList<>();
        Collections.addAll(newStones, stoneLine.split(" "));

        return newStones;
    }
}
