package g0901_1000.s0989_add_to_array_form_of_integer;

// #Easy #Array #Math #Programming_Skills_II_Day_5
// #2022_03_31_Time_7_ms_(65.92%)_Space_62.4_MB_(29.05%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
//@ requires(*The length of the integer array parameter `num` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `num` are less than or equal to 9 and are greater than or equal to 0.*);
//@ requires(*The integer parameter `k` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The integer array parameter `num` does not contain leading zeros unless the array consists of a single zero.*);
//@ ensures(*The length of the list result is greater than or equal to the length of the integer array parameter `num`.*);
//@ ensures(*The first element of the list result is not equal to 0 unless the list result consists of a single zero.*);
//@ ensures(*The integer value represented by the list result is equal to the sum of the integer value represented by the integer array parameter `num` and the integer parameter `k`.*);
//@ ensures(*If the integer array parameter `num` is equal to [1,2,0,0] and the integer parameter `k` is equal to 34, the list result is equal to [1,2,3,4].*);
//@ ensures(*If the integer array parameter `num` is equal to [2,7,4] and the integer parameter `k` is equal to 181, the list result is equal to [4,5,5].*);
//@ ensures(*If the integer array parameter `num` is equal to [2,1,5] and the integer parameter `k` is equal to 806, the list result is equal to [1,0,2,1].*);
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