package g0001_0100.s0091_decode_ways;

// #Medium #Top_Interview_Questions #String #Dynamic_Programming
// #Algorithm_II_Day_15_Dynamic_Programming #Dynamic_Programming_I_Day_10
// #2022_06_21_Time_2_ms_(66.37%)_Space_41.8_MB_(78.45%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only digits.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1035\. Uncrossed Lines*);
//@ requires(*Medium*);
//@ requires(*We write the integers of `A` and `B` (in the order they are given) on two separate horizontal lines.*);
//@ requires(**);
//@ requires(*Now, we may draw connecting lines: a straight line connecting two numbers `A[i]` and `B[j]` such that:*);
//@ requires(**);
//@ requires(**   `A[i] == B[j]`;*);
//@ requires(**   The line we draw does not intersect any other connecting (non-horizontal) line.*);
//@ requires(**);
//@ requires(*Note that connecting lines cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).*);
//@ requires(**);
//@ requires(*Return the maximum number of connecting lines we can draw in this way.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** A = \[1,4,2\], B = \[1,2,4\]*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** We can draw 2 uncrossed lines as in the diagram.*);
//@ requires(**);
//@ requires(*We cannot draw 3 uncrossed lines, because the line from A\[1\] = 1 to B\[2\] = 4 will intersect the line from A\[2\] = 2 to B\[1\] = 2.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** A = \[2,5,1,2,5\], B = \[10,5,2,1,5,2\]*);
//@ requires(***Output:** 3*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** A = \[1,3,7,1,7,5\], B = \[1,9,2,5,1\]*);
//@ requires(***Output:** 2*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= A.length <= 500`*);
//@ requires(**   `1 <= B.length <= 500`*);
//@ requires(**   `1 <= A[i], B[i] <= 2000`*);
//@ requires(**);
//@ requires(*Method signature: public int maxUncrossedLines(int[] A, int[] B)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `A` is less than or equal to 500 and is greater than or equal to 1.*);
//@ requires(*The length of the integer array parameter `B` is less than or equal to 500 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `A` are less than or equal to 2000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `B` are less than or equal to 2000 and is greater than or equal to 1.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to 231 - 1.*);
//@ ensures(*If the string parameter `s` is equal to "12", the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to "226", the integer result is equal to 3.*);
//@ ensures(*If the string parameter `s` is equal to "0", the integer result is equal to 0.*);
//@ ensures(*If the string parameter `s` is equal to "06", the integer result is equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the minimum length of the integer array parameters `A` and `B`.*);
//@ ensures(*If the integer array parameters `A` and `B` are equal to [1,4,2] and [1,2,4] respectively, the integer result is equal to 2.*);
//@ ensures(*If the integer array parameters `A` and `B` are equal to [2,5,1,2,5] and [10,5,2,1,5,2] respectively, the integer result is equal to 3.*);
//@ ensures(*If the integer array parameters `A` and `B` are equal to [1,3,7,1,7,5] and [1,9,2,5,1] respectively, the integer result is equal to 2.*);
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int[] f = new int[n + 1];
        // Auxiliary
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            // Calculate the independent number
            if (s.charAt(i - 1) != '0') {
                // As long as the current character is not 0, it means that the previous decoding
                // number can be inherited
                f[i] = f[i - 1];
            }
            // Calculate the number of combinations
            int twodigits = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (twodigits >= 10 && twodigits <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}