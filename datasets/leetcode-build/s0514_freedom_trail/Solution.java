package g0501_0600.s0514_freedom_trail;

// #Hard #String #Dynamic_Programming #Depth_First_Search #Breadth_First_Search
// #2022_07_25_Time_8_ms_(95.63%)_Space_42.9_MB_(87.34%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Solution {
	//@ requires(*The input strings `ring` and `key` are not null.*);
	//@ requires(*The lengths of `ring` and `key` are both greater than or equal to 1.*);
	//@ requires(*The characters in `ring` and `key` are all lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the minimum number of steps required to spell all the characters in the keyword.*);
	//@ ensures(*The characters in `ring` are rotated clockwise or anticlockwise to align each character of `key` at the "12:00" direction.*);
	//@ ensures(*Each rotation of the ring counts as one step.*);
	//@ ensures(*If a character in `key` is already aligned at the "12:00" direction, pressing the center button counts as one step.*);
	//@ ensures(*After pressing the center button, the method proceeds to spell the next character in `key`.*);
	//@ ensures(*If all characters in `key` have been spelled, the method finishes and returns the total number of steps taken.*);
    public int findRotateSteps(String ring, String key) {
        List<Integer>[] indexs = new List[26];
        for (int i = 0; i < ring.length(); i++) {
            int num = ring.charAt(i) - 'a';
            if (indexs[num] == null) {
                indexs[num] = new ArrayList<>();
            }
            indexs[num].add(i);
        }
        int[][] dp = new int[101][101];
        return find(ring, 0, key, 0, dp, indexs);
    }

    private int find(String ring, int i, String key, int j, int[][] cache, List<Integer>[] indexs) {
        if (j == key.length()) {
            return 0;
        }
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        int ans = Integer.MAX_VALUE;
        List<Integer> targets = indexs[key.charAt(j) - 'a'];
        for (int t : targets) {
            int delta = Math.abs(i - t);
            delta = Math.min(delta, Math.abs(ring.length() - delta));
            ans = Math.min(ans, 1 + delta + find(ring, t, key, j + 1, cache, indexs));
        }
        cache[i][j] = ans;
        return ans;
    }
}