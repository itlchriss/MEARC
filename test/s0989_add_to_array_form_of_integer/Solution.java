package g0901_1000.s0989_add_to_array_form_of_integer;

// #Easy #Array #Math #Programming_Skills_II_Day_5
// #2022_03_31_Time_7_ms_(65.92%)_Space_62.4_MB_(29.05%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
//@ requires(*The length of the integer array parameter `num` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `num` are less than or equal to 9 and are greater than or equal to 0.*);
//@ requires(*The integer array parameter `num` does not contain any leading zeros except for the zero itself.*);
//@ requires(*The integer parameter `k` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ ensures(*The length of the integer array result is equal to the maximum length between the length of the integer array parameter `num` and the number of digits in the integer parameter `k` plus 1.*);
//@ ensures(*The integer array result represents the array form of the sum between the integer array parameter `num` and the integer parameter `k`.*);
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