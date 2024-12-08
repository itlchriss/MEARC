package g1901_2000.s1979_find_greatest_common_divisor_of_array;

// #Easy #Array #Math #Number_Theory #2022_06_20_Time_1_ms_(87.75%)_Space_43.7_MB_(53.31%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be at least 2.*);
//@ ensures(*Each element in the input array `nums` must be a positive integer between 1 and 1000 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer value representing the greatest common divisor of the smallest and largest numbers in the input array `nums`.*);
    public int findGCD(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (max < num) {
                max = num;
            }
            if (min > num) {
                min = num;
            }
        }
        return findGCD(max, min);
    }

    private int findGCD(int x, int y) {
        int r;
        int a;
        int b;
        a = (x > y) ? x : y;
        b = (x < y) ? x : y;
        r = b;
        while (a % b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return r;
    }
}