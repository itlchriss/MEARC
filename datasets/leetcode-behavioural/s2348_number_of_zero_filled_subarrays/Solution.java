package g2301_2400.s2348_number_of_zero_filled_subarrays;

// #Medium #Array #Math #2022_07_30_Time_3_ms_(99.90%)_Space_59.8_MB_(95.67%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of subarrays filled with 0.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
//@ ensures(*The returned value is the sum of the occurrences of subarrays filled with 0 of different sizes.*);
//@ ensures(*The method does not modify the input array `nums`.*);
    public long zeroFilledSubarray(int[] nums) {
        long cnt = 0L;
        long local = 0L;
        for (int n : nums) {
            if (n == 0) {
                cnt += ++local;
            } else {
                local = 0;
            }
        }
        return cnt;
    }
}