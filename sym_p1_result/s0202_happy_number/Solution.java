package g0201_0300.s0202_happy_number;

// #Easy #Top_Interview_Questions #Hash_Table #Math #Two_Pointers #Algorithm_II_Day_21_Others
// #Programming_Skills_I_Day_4_Loop #Level_2_Day_1_Implementation/Simulation
// #2022_06_28_Time_1_ms_(98.59%)_Space_41_MB_(64.25%)

public class Solution {
//@ requires(*Write an algorithm to determine if a number param_n is happy.*);
//@ requires(*A happy number is a number defined by the following process:*);
//@ requires(*Starting with any positive integer, replace the number by the sum of the squares of its digits.*);
//@ requires(*Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.*);
//@ requires(*Those numbers for which this process ends in 1 are happy.*);
//@ requires(*Return `true` if param_n is a happy number, and `false` if not.*);
//@ requires(*Example 1:*);
//@ requires(*Input: n = 19*);
//@ requires(*Output: true*);
//@ requires(*Explanation:*);
//@ requires(*1<sup>2</sup> + 9<sup>2</sup> = 82*);
//@ requires(*8<sup>2</sup> + 2<sup>2</sup> = 68*);
//@ requires(*6<sup>2</sup> + 8<sup>2</sup> = 100*);
//@ requires(*1<sup>2</sup> + 0<sup>2</sup> + 0<sup>2</sup> = 1*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 2*);
//@ requires(*Output: false*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= n <= 2<sup>31</sup> - 1</code>*);
    public boolean isHappy(int n) {
        boolean happy;
        int a = n;
        int rem;
        int sum = 0;
        if (a == 1 || a == 7) {
            happy = true;
        } else if (a > 1 && a < 10) {
            happy = false;
        } else {
            while (a != 0) {
                rem = a % 10;
                sum = sum + (rem * rem);
                a = a / 10;
            }
            if (sum != 1) {
                happy = isHappy(sum);
            } else {
                happy = true;
            }
        }
        return happy;
    }
}