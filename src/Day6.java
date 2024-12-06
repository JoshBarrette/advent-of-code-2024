import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {
    static int total = 0;

    static final char UP = '^';
    static final char DOWN = 'v';
    static final char LEFT = '<';
    static final char RIGHT = '>';

    static final char BLOCK = '#';
    static final char CHECKED = 'X';

    static int[] guardPos = {0, 0}; // row, col
    static char guardDirection = UP;
    static ArrayList<ArrayList<Character>> grid = new ArrayList<>();
    static int height = 0;
    static int width = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty()) {
                break;
            }

            ArrayList<Character> lineArr = new ArrayList<>();
            Arrays.stream(tempLine.split("")).forEach((s) -> {
                if (s.charAt(0) == UP) {
                    guardPos[0] = grid.size();
                    guardPos[1] = tempLine.indexOf('^');
                }

                lineArr.add(s.charAt(0));
            });
            grid.add(lineArr);
        }
        height = grid.size();
        width = grid.getFirst().size();

        parse();
//        printGrid();
        System.out.println("total:" + total);
    }

    public static void parse() {
        int[] newPos = {0 , 0};
        grid.get(guardPos[0]).set(guardPos[1], CHECKED);
        total++;

        while (true) {
            newPos[0] = guardPos[0];
            newPos[1] = guardPos[1];

            if (guardDirection == UP) {
                --newPos[0];
                if (newPos[0] < 0) {
//                    System.out.println("top");
                    break;
                } else if (grid.get(newPos[0]).get(newPos[1]) == BLOCK) {
                    guardDirection = RIGHT;
                } else if (grid.get(newPos[0]).get(newPos[1]) != CHECKED) {
                    grid.get(newPos[0]).set(newPos[1], CHECKED);
                    total++;
                    guardPos[0]--;
                } else {
                    guardPos[0]--;
                }
            } else if (guardDirection == DOWN) {
                ++newPos[0];
                if (newPos[0] >= height) {
//                    System.out.println("bottom");
                    break;
                } else if (grid.get(newPos[0]).get(newPos[1]) == BLOCK) {
                    guardDirection = LEFT;
                } else if (grid.get(newPos[0]).get(newPos[1]) != CHECKED) {
                    grid.get(newPos[0]).set(newPos[1], CHECKED);
                    total++;
                    guardPos[0]++;
                } else {
                    guardPos[0]++;
                }
            } else if (guardDirection == LEFT) {
                --newPos[1];
                if (newPos[1] < 0) {
//                    System.out.println("left");
                    break;
                } else if (grid.get(newPos[0]).get(newPos[1]) == BLOCK) {
                    guardDirection = UP;
                } else if (grid.get(newPos[0]).get(newPos[1]) != CHECKED) {
                    grid.get(newPos[0]).set(newPos[1], CHECKED);
                    total++;
                    guardPos[1]--;
                } else {
                    guardPos[1]--;
                }
            } else if (guardDirection == RIGHT) {
                ++newPos[1];
                if (newPos[1] >= width) {
//                    System.out.println("right");
                    break;
                } else if (grid.get(newPos[0]).get(newPos[1]) == BLOCK) {
                    guardDirection = DOWN;
                } else if (grid.get(newPos[0]).get(newPos[1]) != CHECKED) {
                    grid.get(newPos[0]).set(newPos[1], CHECKED);
                    total++;
                    guardPos[1]++;
                } else {
                    guardPos[1]++;
                }
            }
        }
    }

    public static void printGrid() {
        for (ArrayList<Character> row : grid) {
            for (Character c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
