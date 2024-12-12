import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

// 2048

public class Day11 {
    private static long length = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String tempLine = scan.nextLine();
        ListNode head = parseList(tempLine);

        for (int i = 0; i < 20; i++) {
            head = blinkList(head);
            ListNode reader = head;
            while (reader != null) {
                System.out.print(reader.val + " ");
                reader = reader.next;
            }
            System.out.println();
        }

//        while (head != null) {
//            System.out.print(head.val + " ");
//            head = head.next;
//        }
//        System.out.println();
        System.out.println("Total: " + length);
    }

    private static long getLongLength(long num) {
        long len = 1;
        long newNum = num;

        while (newNum >= 10) {
            newNum = newNum / 10L;
            len++;
        }

        return len;
    }

    private static ListNode blinkList(ListNode head) {
        ListNode newHead = new ListNode();
        length = 0;

        ListNode currentReading = head;
        ListNode currentWriting = newHead;
        while (currentReading != null) {
            long numLength = getLongLength(currentReading.val);

            if (currentReading.val == 0) {
                currentWriting.next = new ListNode(1);

                length++;
                currentReading = currentReading.next;
                currentWriting = currentWriting.next;
            } else if (numLength % 2 == 0) {
                currentWriting.next = new ListNode(currentReading.val / (long) Math.pow(10L, (double) numLength / 2L));
                currentWriting.next.next = new ListNode(currentReading.val % (long) Math.pow(10L, (double) numLength / 2L));

                length += 2;
                currentReading = currentReading.next;
                currentWriting = currentWriting.next.next;
            } else {
                currentWriting.next = new ListNode(currentReading.val * 2024L);

                length++;
                currentReading = currentReading.next;
                currentWriting = currentWriting.next;
            }
        }

        return newHead.next;
    }

    private static ListNode parseList(String stoneLine) {
        ListNode head = new ListNode();
        String[] stones = stoneLine.split(" ");
        head.val = Long.parseLong(stones[0]);

        ListNode current = head;
        for (int i = 1; i < stones.length; i++) {
            current.next = new ListNode(Long.parseLong(stones[i]));
            current = current.next;
        }

        return head;
    }

    private static class ListNode {
        public long val;
        public ListNode next;

        ListNode() {
            this.next = null;
        }

        ListNode(long val) {
            this.val = val;
            this.next = null;
        }
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
