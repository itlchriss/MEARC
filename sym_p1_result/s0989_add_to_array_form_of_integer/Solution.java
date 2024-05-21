package g0901_1000.s0989_add_to_array_form_of_integer;

// #Easy #Array #Math #Programming_Skills_II_Day_5
// #2022_03_31_Time_7_ms_(65.92%)_Space_62.4_MB_(29.05%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
//@ requires(*The array-form of an integer param_num is an array representing its digits in left to right order.*);
//@ requires(*For example, for `num = 1321`, the array form is `[1,3,2,1]`.*);
//@ requires(*Example 1:*);
//@ requires(*Input: num = [1,2,0,0], k = 34*);
//@ requires(*Output: [1,2,3,4]*);
//@ requires(*Explanation: 1200 + 34 = 1234*);
//@ requires(*Example 2:*);
//@ requires(*Input: num = [2,7,4], k = 181*);
//@ requires(*Output: [4,5,5]*);
//@ requires(*Explanation: 274 + 181 = 455*);
//@ requires(*Example 3:*);
//@ requires(*Input: num = [2,1,5], k = 806*);
//@ requires(*Output: [1,0,2,1]*);
//@ requires(*Explanation: 215 + 806 = 1021*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= num.length <= 10<sup>4</sup></code>*);
//@ requires(*`0 <= num[i] <= 9`*);
//@ requires(*param_num does not contain any leading zeros except for the zero itself.*);
//@ requires(*<code>1 <= k <= 10<sup>4</sup></code>*);
//@ ensures(*Given param_num, the array-form of an integer, and an integer param_k, the result is the array-form of the integer `num + k`.*);
    public List<Integer> addToArrayForm(int[] num, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        int carry = 0;
        for (int i = num.length - 1; i >= 0; i--) {
            int temp = num[i] + k % 10 + carry;
            result.add(temp % 10);
            carry = temp / 10;
            k /= 10;
        }
        while (k > 0) {
            int t = k % 10 + carry;
            result.add(t % 10);
            carry = t / 10;
            k /= 10;
        }
        if (carry == 1) {
            result.add(1);
        }
        Collections.reverse(result);
        return result;
    }
}