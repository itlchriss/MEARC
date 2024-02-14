package g2101_2200.s2134_minimum_swaps_to_group_all_1s_together_ii;

// #Medium #Array #Sliding_Window #2022_06_04_Time_11_ms_(72.59%)_Space_86.6_MB_(27.41%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are either 0 or 1.*);
	//@ ensures(*The output is an integer representing the minimum number of swaps required to group all 1's together in the input array `nums`.*);
    public int minSwaps(int[] nums) {
        int l = nums.length;
        int[] ones = new int[l];
        ones[0] = nums[0] == 1 ? 1 : 0;
        for (int i = 1; i < l; i++) {
            if (nums[i] == 1) {
                ones[i] = ones[i - 1] + 1;
            } else {
                ones[i] = ones[i - 1];
            }
        }
        if (ones[l - 1] == l || ones[l - 1] == 0) {
            return 0;
        }
        int ws = ones[l - 1];
        int minSwaps = Integer.MAX_VALUE;
        int si = 0;
        int ei;
        while (si < nums.length) {
            ei = (si + ws - 1) % l;
            int totalones;
            if (ei >= si) {
                totalones = ones[ei] - (si == 0 ? 0 : ones[si - 1]);
            } else {
                totalones = ones[ei] + (ones[l - 1] - ones[si - 1]);
            }
            int swapsreq = ws - totalones;
            if (swapsreq < minSwaps) {
                minSwaps = swapsreq;
            }
            si++;
        }
        return minSwaps;
    }
}