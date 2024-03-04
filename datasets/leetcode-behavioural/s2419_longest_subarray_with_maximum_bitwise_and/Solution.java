package g2401_2500.s2419_longest_subarray_with_maximum_bitwise_and;

// #Medium #Array #Bit_Manipulation #Brainteaser
// #2022_11_18_Time_4_ms_(83.94%)_Space_93.5_MB_(63.54%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input array `nums` is not null.*);
//@ ensures(*2. The length of the input array `nums` is greater than 0.*);
//@ ensures(*3. The elements in the input array `nums` are integers.*);
//@ ensures(*4. The elements in the input array `nums` are non-negative.*);
//@ ensures(*5. The elements in the input array `nums` are within the range of 1 to 10^6.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The returned value is an integer.*);
//@ ensures(*2. The returned value represents the length of the longest subarray with the maximum possible bitwise AND.*);
//@ ensures(*3. The returned value is greater than or equal to 1.*);
//@ ensures(*4. The returned value is less than or equal to the length of the input array `nums`.*);
    public int longestSubarray(int[] nums) {
        int maxVal = 0;
        int res = 0;
        int i = 0;
        while (i < nums.length) {
            int n = nums[i];
            if (n < maxVal) {
                i++;
                continue;
            }
            if (n > maxVal) {
                maxVal = n;
                res = 0;
            }
            int j = i + 1;
            while (j < nums.length && nums[j] == n) {
                j++;
            }
            res = Math.max(res, j - i);
            i = j - 1;
            i++;
        }
        return res;
    }
}