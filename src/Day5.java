import java.util.*;

public class Day5 {
    static HashMap<Integer, ArrayList<Integer>> rules = new HashMap<>();
    static int total = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean rulesParsed = false;

        while (true) {
            String tempLine = scan.nextLine();
            if (tempLine.isEmpty() && rulesParsed) {
                break;
            } else if (tempLine.isEmpty()) {
                rulesParsed = true;
                continue;
            }

            if (rulesParsed) {
                parseUpdate(tempLine);
            } else {
                parseRule(tempLine);
            }
        }

        System.out.println("total: " + total);
    }

    public static void parseUpdate(String update) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        ArrayList<Integer> pages = new ArrayList<>();
        Arrays.stream(update.split(",")).forEach((a) -> pages.add(Integer.parseInt(a)));

        rules.get(pages.getFirst()).forEach((a) -> map.put(a, false));
        for (int i = 1; i < pages.size(); i++) {
            // Check of the current page needs to satisfy anything
            if (map.containsKey(pages.get(i))) {
                map.replace(pages.get(i), true);
            }

            // Add the requirements of teh current page
            ArrayList<Integer> pageRules = rules.get(pages.get(i));
            if (pageRules == null) continue;

            pageRules.forEach((a) -> {
                map.put(a, false);
            });
        }
        
        for (int page : pages) {
            if (rules.get(page) == null) continue;

            for (int rule : rules.get(page)) {
                if (!map.get(rule) && pages.contains(rule)) {
                    return;
                }
            }
        }

        total += pages.get(pages.size() / 2);
    }

    public static void parseRule(String r) {
        String[] s = r.split("\\|");
        int[] nums = {Integer.parseInt(s[0]), Integer.parseInt(s[1])};

        ArrayList<Integer> arr = rules.get(nums[0]);
        if (arr != null && !arr.contains(nums[1])) {
            arr.add(nums[1]);
        } else if (arr == null) {
            arr = new ArrayList<>();
            arr.add(nums[1]);
            rules.put(nums[0], arr);
        }
    }
}
