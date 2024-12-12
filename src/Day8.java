import java.util.*;

// 350: too high

public class Day8 {
    private static final HashMap<Character, ArrayList<int[]>> map = new HashMap<>();
    private static final HashSet<String> points = new HashSet<>();
    private static int rows = 0;
    private static int cols = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty()) {
                break;
            }

            cols = tempLine.length();
            parseLine(tempLine, rows++);
        }

//        printMap();
        count();
        System.out.println(points.size());
    }

    private static void count() {
        for (Character c : map.keySet()) {
            ArrayList<int[]> vals = map.get(c);

            for (int i = 0; i < vals.size() - 1; i++) {
                for (int j = i + 1; j < vals.size(); j++) {
                    int[] pointOne = vals.get(i);
                    int[] pointTwo = vals.get(j);

                    int[] antiOne = new int[2];
                    int[] antiTwo = new int[2];
                    int yDiff = Math.abs(pointOne[0] - pointTwo[0]);
                    int xDiff = Math.abs(pointOne[1] - pointTwo[1]);

                    if (pointOne[0] < pointTwo[0]) {
                        antiOne[0] = pointOne[0] - yDiff;
                        antiTwo[0] = pointTwo[0] + yDiff;
                    } else {
                        antiOne[0] = pointOne[0] + yDiff;
                        antiTwo[0] = pointTwo[0] - yDiff;
                    }

                    if (pointOne[1] < pointTwo[1]) {
                        antiOne[1] = pointOne[1] - xDiff;
                        antiTwo[1] = pointTwo[1] + xDiff;
                    } else {
                        antiOne[1] = pointOne[1] + xDiff;
                        antiTwo[1] = pointTwo[1] - xDiff;
                    }

                    if (antiOne[0] >= 0 && antiOne[0] <= rows &&
                            antiOne[1] >= 0 && antiOne[1] <= cols) {
                        points.add(antiOne[0] + "," + antiOne[1]);
                    }

                    if (antiTwo[0] >= 0 && antiTwo[0] <= rows &&
                            antiTwo[1] >= 0 && antiTwo[1] <= cols) {
                        points.add(antiTwo[0] + "," + antiTwo[1]);
                    }
                }
            }
        }
    }

    private static void parseLine(String line, int lineNum) {
        String[] s = line.split("");
        for (int i = 0; i < s.length; i++) {
            char c = s[i].charAt(0);
            if (c == '.' || c == '#') continue;

            ArrayList<int[]> temp = map.get(c);

            if (temp == null) {
                temp = new ArrayList<>();
            }

            temp.add(new int[]{lineNum, i});
            map.put(c, temp);
        }
    }

    private static void printMap() {
        for (Character c : map.keySet()) {
            System.out.println(c + ": " + Arrays.toString(map.get(c).stream().map(Arrays::toString).toArray()));
        }
    }
}
