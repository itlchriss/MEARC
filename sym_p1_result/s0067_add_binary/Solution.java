package g0001_0100.s0067_add_binary;

// #Easy #String #Math #Bit_Manipulation #Simulation #Programming_Skills_II_Day_5
// #2023_08_11_Time_1_ms_(100.00%)_Space_41.6_MB_(36.86%)

public class Solution {
//@ requires(*Example 1:*);
//@ requires(*Input: a = "11", b = "1"*);
//@ requires(*Output: "100"*);
//@ requires(*Example 2:*);
//@ requires(*Input: a = "1010", b = "1011"*);
//@ requires(*Output: "10101"*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= a.length, b.length <= 10<sup>4</sup></code>*);
//@ requires(*param_a and param_b consist only of `'0'` or `'1'` characters.*);
//@ requires(*Each string does not contain leading zeros except for the zero itself.*);
//@ ensures(*Given two binary strings param_a and param_b, the result is their sum as a binary string.*);
    public String addBinary(String a, String b) {
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = aArray.length - 1;
        int j = bArray.length - 1;
        int carry = 0;
        //@ maintaining -1 <= i <= aArray.length - 1 && bArray.length - 1 >= j >= -1;
        while (i >= 0 || j >= 0) {
            int sum = (i >= 0 ? aArray[i] - '0' : 0) + (j >= 0 ? bArray[j] - '0' : 0) + carry;
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