package g0201_0300.s0233_number_of_digit_one;

// #Hard #Dynamic_Programming #Math #Recursion
// #2022_07_04_Time_0_ms_(100.00%)_Space_41.2_MB_(25.50%)

@SuppressWarnings("java:S127")
public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 1000000000 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2521\. Count the Digits That Divide a Number II*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given an integer `num`, return _the number of digits in_ `num` _that divide_ `num`.*);
//@ requires(**);
//@ requires(*An integer `val` divides `num` if `num % val == 0`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** num = 7*);
//@ requires(**);
//@ requires(***Output:** 1*);
//@ requires(**);
//@ requires(***Explanation:** 7 divides itself, hence the answer is 1.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** num = 121*);
//@ requires(**);
//@ requires(***Output:** 2*);
//@ requires(**);
//@ requires(***Explanation:** 121 is divisible by 1, 11, and 121. Hence the answer is 2.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** num = 1248*);
//@ requires(**);
//@ requires(***Output:** 4*);
//@ requires(**);
//@ requires(***Explanation:** 1248 is divisible by 1, 2, 4, and 8. Hence the answer is 4.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= num <= 1000000000`*);
//@ requires(**);
//@ requires(*Method signature: public int countDigits(int x)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The integer parameter `x` is less than or equal to 1000000000 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2771\. Maximum Score After N Operations*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(*You are given `nums`, an array of positive integers of size `2 * n`. You can perform `n` operations on this array.*);
//@ requires(**);
//@ requires(*In the `ith` operation (1-indexed), you will:*);
//@ requires(*Choose two elements, `x` and `y`.*);
//@ requires(**);
//@ requires(*Receive a score of `i * gcd(x, y)`.*);
//@ requires(**);
//@ requires(*Here, `gcd(x, y)` is the greatest common divisor of `x` and `y`.*);
//@ requires(**);
//@ requires(*Return _the maximum score you can receive after performing_ `n` _operations_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,2\]*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:***);
//@ requires(*For the first operation, gcd(1, 2) = 1, so you receive a score of 1 * 1 = 1.*);
//@ requires(**);
//@ requires(*For the second operation, gcd(1, 2) = 1, so you receive a score of 2 * 1 = 2.*);
//@ requires(**);
//@ requires(*The total score is 1 + 2 = 3.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[3,4,6,8\]*);
//@ requires(***Output:** 11*);
//@ requires(***Explanation:***);
//@ requires(*For the first operation, gcd(3, 4) = 1, so you receive a score of 1 * 1 = 1.*);
//@ requires(**);
//@ requires(*For the second operation, gcd(3, 6) = 3, so you receive a score of 2 * 3 = 6.*);
//@ requires(**);
//@ requires(*For the third operation, gcd(3, 8) = 1, so you receive a score of 3 * 1 = 3.*);
//@ requires(**);
//@ requires(*For the fourth operation, gcd(4, 8) = 4, so you receive a score of 4 * 4 = 16.*);
//@ requires(**);
//@ requires(*The total score is 1 + 6 + 3 + 16 = 26.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 105`*);
//@ requires(**   `1 <= nums[i] <= 2 * 105`*);
//@ requires(**);
//@ requires(*Method signature: public long*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the integer parameter `n`.*);
//@ ensures(*If the integer parameter `n` is equal to 13, the integer result is equal to 6.*);
//@ ensures(*If the integer parameter `n` is equal to 0, the integer result is equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to the number of digits in the integer parameter `x`.*);
//@ ensures(*If the integer parameter `x` is equal to 7, the integer result is equal to 1.*);
//@ ensures(*If the integer parameter `x` is equal to 121, the integer result is equal to 2.*);
//@ ensures(*If the integer parameter `x` is equal to 1248, the integer result is equal to 4.*);
    public int countDigitOne(int n) {
        int ans = 0;
        // count total number of 1s appearing in every digit, starting from the last digit
        for (int k = n, cum = 0, curr10 = 1; k > 0; curr10 *= 10) {
            int rem = k % 10;
            int q = k / 10;
            if (rem == 0) {
                ans += q * curr10;
            } else if (rem == 1) {
                ans += q * curr10 + cum + 1;
            } else {
                ans += (q + 1) * curr10;
            }
            k = q;
            // if loop is at 3rd last digit and n = 54321, cum is now = 321
            cum += rem * curr10;
        }
        return ans;
    }
}