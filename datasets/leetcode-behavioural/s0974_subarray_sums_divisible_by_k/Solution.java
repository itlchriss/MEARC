package g0901_1000.s0974_subarray_sums_divisible_by_k;

// #Medium #Array #Hash_Table #Prefix_Sum #2022_03_31_Time_3_ms_(99.95%)_Space_46.1_MB_(95.83%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(*The input integer `k` is greater than or equal to 2.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of non-empty subarrays that have a sum divisible by `k`.*);
    public int subarraysDivByK(int[] nums, int k) {
        int[] map = new int[k];
        int ans = 0;
        int sum = 0;
        map[0] = 1;
        for (int num : nums) {
            sum += num;
            int temp = sum % k;
            if (temp < 0) {
                temp += k;
            }
            ans += map[temp]++;
        }
        return ans;
    }
}