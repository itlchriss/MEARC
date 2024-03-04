package g2501_2600.s2571_minimum_operations_to_reduce_an_integer_to_0;

// #Medium #Dynamic_Programming #Greedy #Bit_Manipulation
// #2023_08_21_Time_0_ms_(100.00%)_Space_39.4_MB_(38.98%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` is a positive integer.*);
//@ ensures(*The input integer `n` is within the range of 1 to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of operations required to make `n` equal to 0.*);
//@ ensures(*The value of `n` is modified to 0 after the minimum number of operations are performed.*);
    public int minOperations(int n) {
        int cnt = 1;
        while (n != 0) {
            int num = 1;
            while (num <= n) {
                if (num == n) {
                    return cnt;
                }
                num *= 2;
            }
            n = Math.min(num - n, n - num / 2);
            cnt++;
        }
        return cnt;
    }
}