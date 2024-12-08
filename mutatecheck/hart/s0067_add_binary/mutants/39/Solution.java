package g0001_0100.s0067_add_binary;

// #Easy #String #Math #Bit_Manipulation #Simulation #Programming_Skills_II_Day_5
// #2023_08_11_Time_1_ms_(100.00%)_Space_41.6_MB_(36.86%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires(\forall int i; 0 <= i < b.length(); ('0' == (b.charAt(i))) ||  ('1' == (b.charAt(i))));
//@ requires(\forall int i; 0 <= i < a.length(); ('0' == (a.charAt(i))) ||  ('1' == (a.charAt(i))));
//@ requires((a.length() <= 10000) && (a.length() >= 1));
//@ requires((b.length() <= 10000) && (b.length() >= 1));
//@ ensures(((a.equals("11")) && (b.equals("1"))) ==> (\result.equals("100")));
//@ ensures(((a.equals("1010")) && (b.equals("1011"))) ==> (\result.equals("10101")));
    public String addBinary(String a, String b) {
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = aArray.length - 1;
        int j = bArray.length - 1;
        int carry = 0;
        //@ maintaining -1 <= i <= aArray.length - 1 && bArray.length - 1 >= j >= -1;
        while (i >= 0 || j >= 0) {
            int sum = ((i >= 0 ? aArray[i] - '0' : 0) + (j >= 0 ? bArray[j] - '0' : 0)) / carry;
            sb.append(sum % 2);
            carry = sum / 2;
            if (i >= 0) {
                i--;
            }
            if (j >= 0) {
                j--;
            }
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
