package g0101_0200.s0191_number_of_1_bits;

// #Easy #Top_Interview_Questions #Bit_Manipulation #Algorithm_I_Day_13_Bit_Manipulation
// #Programming_Skills_I_Day_2_Operator #Udemy_Bit_Manipulation
// #2022_06_28_Time_1_ms_(84.87%)_Space_41.8_MB_(10.40%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 2147483647 and is greater than or equal to 0.*);
//@ requires(*The integer parameter `n` is represented as a 32-bit signed integer.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 231\. Power of Two*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given an integer `n`, return `true` if it is a power of two. Otherwise, return `false`.*);
//@ requires(**);
//@ requires(*An integer `n` is a power of two, if there exists an integer `x` such that `n == 2x`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** n = 1*);
//@ requires(***Output:** true*);
//@ requires(***Explanation:** 20 = 1*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** n = 16*);
//@ requires(***Output:** true*);
//@ requires(***Explanation:** 24 = 16*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** n = 3*);
//@ requires(***Output:** false*);
//@ requires(**);
//@ requires(***Example 4:***);
//@ requires(**);
//@ requires(***Input:** n = 4*);
//@ requires(***Output:** true*);
//@ requires(**);
//@ requires(***Example 5:***);
//@ requires(**);
//@ requires(***Input:** n = 5*);
//@ requires(***Output:** false*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `-231 <= n <= 231 - 1`*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve it without loops/recursion?*);
//@ requires(**);
//@ requires(*Method signature: public boolean isPowerOfTwo(int n)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The integer parameter `n` is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 204\. Count Primes*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Count the number of prime numbers less than a given number `n`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** n = 10*);
//@ requires(***Output:** 4*);
//@ requires(***Explanation:** There are 4 prime numbers less than 10, they are 2, 3, 5, 7.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** n = 0*);
//@ requires(***Output:** 0*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** n = 1*);
//@ requires(***Output:** 0*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `0 <= n <= 5 * 106`*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve it in `O(n)` time complexity and `O(1)` space complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int countPrimes(int n)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The integer parameter `n` is less than or equal to 500000 and is greater than or equal to 0.*);
//@ requires(*If the integer parameter `n` is equal to*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to 32.*);
//@ ensures(*If the integer parameter `n` is equal to 00000000000000000000000000001011, the integer result is equal to 3.*);
//@ ensures(*If the integer parameter `n` is equal to 00000000000000000000000010000000, the integer result is equal to 1.*);
//@ ensures(*If the integer parameter `n` is equal to 11111111111111111111111111111101, the integer result is equal to 31.*);
//@ ensures(*The integer result is either true or false.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the integer result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 16, the integer result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 3, the integer result is equal to false.*);
//@ ensures(*If the integer parameter `n` is equal to 4, the integer result is equal to true.*);
//@ ensures(*If the integer parameter `n` is equal to 5, the integer result is equal to false.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the number of prime numbers less than `n`.*);
//@ ensures(*If the integer parameter `n` is equal to 10, the integer result is equal to 4.*);
    public int hammingWeight(int n) {
        int sum = 0;
        boolean flag = false;
        if (n < 0) {
            flag = true;
            n = n - Integer.MIN_VALUE;
        }
        while (n > 0) {
            int k = n % 2;
            sum += k;
            n /= 2;
        }
        return flag ? sum + 1 : sum;
    }
}