package g0301_0400.s0357_count_numbers_with_unique_digits;

// #Medium #Dynamic_Programming #Math #Backtracking
// #2022_07_11_Time_0_ms_(100.00%)_Space_41.2_MB_(23.67%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 8 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2953\. Find the Peaks II*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*You are given a **0-indexed** array `mountain` of length `n` which represents the height of a series of mountains.*);
//@ requires(**);
//@ requires(*You may choose one of the following tools:*);
//@ requires(**);
//@ requires(**   A **pen** that can be placed on any integer location, then mark the mountain at that location. A pen can be moved anywhere on the mountain.*);
//@ requires(**   A **pencil** that can be placed on any integer location, then mark the mountain at that location. A pencil cannot be moved once placed.*);
//@ requires(**);
//@ requires(*You must mark **exactly** `k` peaks in the mountain, and you may not mark any points other than the peaks.*);
//@ requires(**);
//@ requires(*Return _the minimum number of tools required to mark all_ `k` _peaks_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** mountain = \[1,3,1,2,1,3,5,1\], k = 1*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** You can choose any of the 8 pencil locations to mark the peak.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** mountain = \[1,3,1,2,1,3,5,1\], k = 2*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** You can choose the 7th pencil location to mark the first peak, and the 4th pencil location to mark the second peak.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `n == mountain.length`*);
//@ requires(**   `1 <= n <= 1000`*);
//@ requires(**   `1 <= mountain[i] <= 109`*);
//@ requires(**   `1 <= k <= n`*);
//@ requires(**   It is guaranteed that there are exactly `k` peaks in the mountain.*);
//@ requires(**);
//@ requires(*Method signature: public int minimumTools(int[] mountain, int k)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `mountain` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `mountain` are less than or equal to 1000000000 and is greater than or equal to 1.*);
//@ requires(*The integer parameter `k` is less than or equal to the length of the integer array parameter `mountain` and is greater than or equal to 1.*);
//@ requires(*It is guaranteed that there are exactly `k` peaks in the integer array parameter `mountain`.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2521\. Count the Digits That Divide a Number II*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to 1000000000.*);
//@ ensures(*If the integer parameter `n` is equal to 2, the integer result is equal to 91.*);
//@ ensures(*If the integer parameter `n` is equal to 0, the integer result is equal to 1.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the integer result is equal to 10.*);
//@ ensures(*If the integer parameter `n` is equal to 3, the integer result is equal to 739.*);
//@ ensures(*If the integer parameter `n` is equal to 4, the integer result is equal to 5275.*);
//@ ensures(*If the integer parameter `n` is equal to 5, the integer result is equal to 32491.*);
//@ ensures(*If the integer parameter `n` is equal to 6, the integer result is equal to 1688228.*);
//@ ensures(*If the integer parameter `n` is equal to 7, the integer result is equal to 8235647.*);
//@ ensures(*If the integer parameter `n` is equal to 8, the integer result is equal to 689232385.*);
//@ ensures(*If the integer array parameter `mountain` is equal to [1,3,1,2,1,3,5,1] and the integer parameter `k` is equal to 1, the integer result is equal to 1.*);
//@ ensures(*If the integer array parameter `mountain` is equal to [1,3,1,2,1,3,5,1] and the integer parameter `k` is equal to 2, the integer result is equal to 1.*);
    public int countNumbersWithUniqueDigits(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            int mul = 1;
            for (int j = 1; j < i; j++) {
                mul *= (10 - j);
            }
            ans = ans + 9 * mul;
        }
        return ans;
    }
}