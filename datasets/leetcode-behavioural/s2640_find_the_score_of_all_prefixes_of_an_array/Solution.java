package g2601_2700.s2640_find_the_score_of_all_prefixes_of_an_array;

// #Medium #Array #Prefix_Sum #2023_09_05_Time_3_ms_(70.00%)_Space_62.2_MB_(68.21%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `ans` is not null.*);
//@ ensures(*The length of the output array `ans` is equal to the length of the input array `nums`.*);
//@ ensures(*The elements in the output array `ans` are long integers.*);
//@ ensures(*The elements in the output array `ans` are the scores of the prefixes of the input array `nums`.*);
    public long[] findPrefixScore(int[] nums) {
        int max = Integer.MIN_VALUE;
        long sum = 0L;
        long[] res = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            max = Math.max(max, curr);
            sum += max + curr;
            res[i] = sum;
        }
        return res;
    }
}