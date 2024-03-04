package g0901_1000.s0989_add_to_array_form_of_integer;

// #Easy #Array #Math #Programming_Skills_II_Day_5
// #2022_03_31_Time_7_ms_(65.92%)_Space_62.4_MB_(29.05%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `num` must not be null.*);
//@ ensures(*The length of the input array `num` must be greater than or equal to 1.*);
//@ ensures(*The elements of the input array `num` must be integers between 0 and 9 (inclusive).*);
//@ ensures(*The input integer `k` must be greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output list must not be null.*);
//@ ensures(*The length of the output list must be equal to the length of the input array `num` or one greater.*);
//@ ensures(*The elements of the output list must be integers between 0 and 9 (inclusive).*);
//@ ensures(*The output list must represent the array-form of the sum of `num` and `k`.*);
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