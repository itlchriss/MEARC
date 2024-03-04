package g1201_1300.s1250_check_if_it_is_a_good_array;

// #Hard #Array #Math #Number_Theory #2022_03_12_Time_4_ms_(58.82%)_Space_60.8_MB_(5.43%)

public class Solution {
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `nums` is not empty.*);
//@ ensures(*The elements in the input array `nums` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if it is possible to obtain a sum of 1 from the array by any possible subset and multiplicand.*);
//@ ensures(*The method returns `false` if it is not possible to obtain a sum of 1 from the array by any possible subset and multiplicand.*);

    public boolean isGoodArray(int[] nums) {
        int ans = nums[0];
        for (int element : nums) {
            ans = gcd(ans, element);
            if (ans == 1) {
                return true;
            }
        }
        return false;
    }
}