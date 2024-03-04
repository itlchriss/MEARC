package g2301_2400.s2344_minimum_deletions_to_make_array_divisible;

// #Hard #Array #Math #Sorting #Heap_Priority_Queue #Number_Theory
// #2022_07_19_Time_13_ms_(88.89%)_Space_77.2_MB_(77.78%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `nums` and `numsDivide` are not null.*);
//@ ensures(*The length of `nums` and `numsDivide` is greater than or equal to 1.*);
//@ ensures(*The elements in `nums` and `numsDivide` are positive integers.*);
//@ ensures(*The length of `nums` and `numsDivide` is less than or equal to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of deletions required.*);
//@ ensures(*If it is not possible to make the smallest element in `nums` divide all the elements of `numsDivide`, the method returns -1.*);
    public int minOperations(int[] nums, int[] numsDivide) {
        int g = numsDivide[0];
        for (int i : numsDivide) {
            g = gcd(g, i);
        }
        int minOp = 0;
        int smallest = Integer.MAX_VALUE;
        for (int num : nums) {
            if (g % num == 0) {
                smallest = Math.min(smallest, num);
            }
        }
        for (int num : nums) {
            if (num < smallest) {
                ++minOp;
            }
        }
        return smallest == Integer.MAX_VALUE ? -1 : minOp;
    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }
}