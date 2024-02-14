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
	//@ requires(*The integer `n` must be greater than or equal to 1.*);
	//@ requires(*The length of the `blacklist` array must be less than or equal to the minimum of 10^5 and `n - 1`.*);
	//@ requires(*All values in the `blacklist` array must be unique.*);
	//@ requires(*The values in the `blacklist` array must be between 0 and `n - 1`, inclusive.*);
	//@ ensures(*The `Solution` object is initialized with the integer `n` and the `blacklist` array.*);
	//@ ensures(*The `pick` method returns a random integer between 0 and `n - 1` that is not in the `blacklist` array.*);
	//@ ensures(*The probability of each integer between 0 and `n - 1` (excluding the values in the `blacklist` array) being returned by the `pick` method is equal and is 1 divided by the number of valid integers.*);

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