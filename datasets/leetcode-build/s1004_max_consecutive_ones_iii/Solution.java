package g1001_1100.s1004_max_consecutive_ones_iii;

// #Medium #Array #Binary_Search #Prefix_Sum #Sliding_Window
// #2022_02_27_Time_3_ms_(79.01%)_Space_68.2_MB_(65.91%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` has at least one element.*);
	//@ requires(*The input array `nums` contains only binary values (0 or 1).*);
	//@ requires(*The input integer `k` is non-negative.*);
	//@ requires(*The input integer `k` is less than or equal to the length of the input array `nums`.*);
	//@ ensures(*The method returns an integer representing the maximum number of consecutive 1's in the array after flipping at most `k` 0's.*);
	//@ ensures(*The input array `nums` remains unchanged after the method is executed.*);
    public int longestOnes(int[] a, int k) {
        int result = 0;
        int i = 0;
        for (int j = 0; j < a.length; j++) {
            if (a[j] == 0) {
                k--;
            }
            while (k < 0) {
                if (a[i] == 0) {
                    k++;
                }
                i++;
            }
            result = Math.max(result, j - i + 1);
        }
        return result;
    }
}