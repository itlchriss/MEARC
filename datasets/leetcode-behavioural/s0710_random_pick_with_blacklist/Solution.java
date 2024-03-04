package g0701_0800.s0710_random_pick_with_blacklist;

// #Hard #Hash_Table #Math #Sorting #Binary_Search #Randomized
// #2022_03_18_Time_56_ms_(74.41%)_Space_70.7_MB_(46.45%)

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("java:S2245")
public class Solution {
    private final Map<Integer, Integer> map;
    private final Random r;
    private final int upperLimit;
//@ ensures(*The integer parameter `n` must be greater than or equal to 1.*);
//@ ensures(*The integer array parameter `blacklist` must not be null.*);
//@ ensures(*The integer array parameter `blacklist` must contain unique integers.*);
//@ ensures(*The integer array parameter `blacklist` must have a length less than or equal to the minimum of 10^5 and `n - 1`.*);
//@ ensures(*The integer elements in the array parameter `blacklist` must be in the range from 0 to `n - 1`.*);
//@ ensures(*The integer result of the `pick()` method must be in the range from 0 to `n - 1`.*);
//@ ensures(*The integer result of the `pick()` method must not be in the `blacklist`.*);
//@ ensures(*The integer result of the `pick()` method must be equally likely to be any integer from the range `[0, n - 1]` that is not in the `blacklist`.*);

    public Solution(int n, int[] blacklist) {
        map = new HashMap<>();
        r = new Random();
        upperLimit = n - blacklist.length;
        for (int val : blacklist) {
            map.put(val, -1);
        }
        int j = n - 1;
        for (int val : blacklist) {
            if (val < upperLimit) {
                while (map.containsKey(j)) {
                    j--;
                }
                map.put(val, j);
                j--;
            }
        }
    }

    public int pick() {
        int val = r.nextInt(upperLimit);
        if (map.containsKey(val)) {
            return map.get(val);
        }
        return val;
    }
}