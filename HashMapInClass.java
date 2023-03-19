import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HashMapInClass {
    public static int majorityElement (final int[] nums) {
        HashMap<Integer, Integer> frequency = new HashMap<>();

        for (int num : nums) {
            if (frequency.containsKey(num)) {
                frequency.put(num, frequency.get(num) + 1);
            } else {
                frequency.put(num, 1);
            }

//            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        Set<Integer> keys = frequency.keySet();

        for (Integer key : keys) {
            if (frequency.get(key) > (nums.length / 3)) {
                return key;
            }
        }

//        for (Integer key : frequency.keySet()) {
//            if (frequency.get(key) > (nums.length / 3)) {
//                return key;
//            }
//        }

        return -1;
    }
    public static boolean isValidAnagrams (final String s1, final String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        HashMap<Character, Integer> frequency = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            final char ch = s1.charAt(i);

            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < s2.length(); i++) {
            final char ch = s2.charAt(i);

            if (frequency.get(ch) == null) {
                return false;
            } else {
                if (frequency.get(ch) == 1) {
                    frequency.remove(ch);
                } else {
                    frequency.put(ch, frequency.get(ch) - 1);
                }
            }
        }

        return frequency.isEmpty();
    }
    public static int countDistinctElements (final int[] elements) {
        HashSet<Integer> uniqs = new HashSet<>();

        for (int ele : elements) {
            uniqs.add(ele);
        }

        return uniqs.size();
    }
    public static String getStart (HashMap<String, String> tickets) {
        HashMap<String, String> reverse = new HashMap<>();

        for (String key : tickets.keySet()) {
            reverse.put(tickets.get(key), key);
        }

        for (String key : tickets.keySet()) {
            if (!reverse.containsKey(key)) {
                return key; // starting point...
            }
        }

        return null;
    }
    public static void findItineraryForTickets () {
        HashMap<String, String> tickets = new HashMap<>();

        tickets.put("Chennai", "Bengaluru");
        tickets.put("Mumbai", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");

        // Starting point...
        String start = getStart(tickets);
        System.out.print(start);

        for (String key : tickets.keySet()) {
            System.out.print(" -> " + tickets.get(start));

            start = tickets.get(start);
        }

        System.out.println();
    }
    public static int findLargestSubArrayWithZeroSum (final int[] nums) {
        HashMap<Integer, Integer> sums = new HashMap<>();

        int sum = 0, largest = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sums.containsKey(sum)) {
                largest = Math.max(largest, i - sums.get(sum));
            } else {
                sums.put(sum, i);
            }

        }

        return largest;
    }
    public static int subArraySumEqualToK (final int[] nums, final int K) {
        HashMap<Integer, Integer> countFreq = new HashMap<>();

        countFreq.put(0, 1);

        int sum = 0, ans = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (countFreq.containsKey(sum - K)) {
                ans += countFreq.get(sum - K);
            }

            countFreq.put(sum, countFreq.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
    public static void main(String[] args) {
        // 1--- Majority element...
//        final int nums[] = {1, 3, 2, 5, 1, 3, 1, 5, 1};
//
//        System.out.println(majorityElement(nums));
        // 1--- Majority element...

//        System.out.println(isValidAnagrams("race", "care"));
//        System.out.println(isValidAnagrams("tulip", "lipid"));

//        final int[] elements = {4, 3, 2, 5, 6, 7, 3, 4, 2, 1};
//
//        System.out.println(countDistinctElements(elements));
//
//        findItineraryForTickets();

//        final int nums[] = {15, -2, 2, -8, 1, 7, 10, 23};
//
//        System.out.println(findLargestSubArrayWithZeroSum(nums));

        final int nums[] = {10, 2, -2, -20, 10};
        final int K = -10;

        System.out.println(subArraySumEqualToK(nums, K));
    }
}
