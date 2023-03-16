import java.util.ArrayList;
import java.util.HashMap;
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
    public static void main(String[] args) {
        // 1--- Majority element...
//        final int nums[] = {1, 3, 2, 5, 1, 3, 1, 5, 1};
//
//        System.out.println(majorityElement(nums));
        // 1--- Majority element...

        System.out.println(isValidAnagrams("race", "care"));
        System.out.println(isValidAnagrams("tulip", "lipid"));
    }
}
