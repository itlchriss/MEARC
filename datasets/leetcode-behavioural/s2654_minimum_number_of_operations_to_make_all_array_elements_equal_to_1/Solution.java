package g2601_2700.s2654_minimum_number_of_operations_to_make_all_array_elements_equal_to_1;

// #Medium #Array #Math #Number_Theory #2023_09_06_Time_1_ms_(100.00%)_Space_40.9_MB_(56.76%)

public class Solution {
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least 2.*);
//@ ensures(*All elements in the input array `nums` are positive integers.*);
//@ ensures(*The maximum value of any element in the input array `nums` is 10^6.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of operations required to make all elements of `nums` equal to 1.*);
//@ ensures(*If it is impossible to make all elements of `nums` equal to 1, the method returns -1.*);

    public int minOperations(int[] nums) {
        int cnt1 = 0;
        int minsubarray = Integer.MAX_VALUE;
        for (int x : nums) {
            if (x == 1) {
                cnt1++;
            }
        }

        if (cnt1 > 0) {
            return nums.length - cnt1;
        }

        for (int i = 0; i < nums.length; i++) {
            int curgcd = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                curgcd = (gcd(curgcd, nums[j]));
                if (curgcd == 1) {
                    minsubarray = Math.min(minsubarray, j - i);
                    break;
                }
            }
        }
        if (minsubarray != Integer.MAX_VALUE) {
            return minsubarray + nums.length - 1;
        }
        return -1;
    }
}