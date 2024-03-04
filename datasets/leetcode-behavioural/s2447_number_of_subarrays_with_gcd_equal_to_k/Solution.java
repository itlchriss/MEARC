package g2401_2500.s2447_number_of_subarrays_with_gcd_equal_to_k;

// #Medium #Array #Math #Number_Theory #2022_12_14_Time_7_ms_(96.60%)_Space_41.9_MB_(82.89%)

public class Solution {
    private int sol(int a, int b) {
        if (b == 0) {
            return a;
        }
        return sol(b, a % b);
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are positive integers.*);
//@ ensures(*The input integer `k` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of subarrays where the greatest common divisor of the subarray's elements is `k`.*);
//@ ensures(*The method returns 0 if there are no subarrays where `k` is the greatest common divisor of all the subarray's elements.*);
//@ ensures(*The method correctly counts the number of subarrays where `k` is the greatest common divisor of all the subarray's elements.*);
//@ ensures(*The method does not modify the input array `nums`.*);
//@ ensures(*The method terminates and returns a result in a reasonable amount of time for inputs within the given constraints.*);

    public int subarrayGCD(int[] nums, int k) {
        int n = nums.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int gcd = 0;
            for (int j = i; j < n; j++) {
                gcd = sol(gcd, nums[j]);
                if (gcd == k) {
                    cnt++;
                }
                if (gcd < k) {
                    break;
                }
            }
        }
        return cnt;
    }
}