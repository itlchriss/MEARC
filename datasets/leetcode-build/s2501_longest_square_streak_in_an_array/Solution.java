package g2501_2600.s2501_longest_square_streak_in_an_array;

// #Medium #Array #Hash_Table #Dynamic_Programming #Sorting #Binary_Search
// #2023_03_19_Time_4_ms_(99.73%)_Space_56_MB_(96.80%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is at least 2.*);
	//@ ensures(*The returned value is an integer.*);
	//@ ensures(*If there is a square streak in the input array `nums`, the returned value is the length of the longest square streak.*);
	//@ ensures(*If there is no square streak in the input array `nums`, the returned value is -1.*);
    public int longestSquareStreak(int[] nums) {
        int result = -1;
        final int max = 100000;
        boolean[] isExisted = new boolean[max + 1];
        boolean[] isVisited = new boolean[max + 1];
        for (int num : nums) {
            isExisted[num] = true;
        }
        for (int i = 2; i * i <= max; i++) {
            if (!isExisted[i] || isVisited[i]) {
                continue;
            }
            isVisited[i] = true;
            int length = 1;
            int j = i * i;
            while (j >= 0 && j <= max && isExisted[j]) {
                isVisited[j] = true;
                length++;
                j = j * j;
            }
            if (length > 1) {
                result = Math.max(result, length);
            }
        }
        return result;
    }
}