package g0401_0500.s0402_remove_k_digits;

// #Medium #String #Greedy #Stack #Monotonic_Stack
// #2022_07_15_Time_4_ms_(99.66%)_Space_42.3_MB_(99.36%)

public class Solution {
	//@ requires(*The input `num` is a non-negative integer represented as a string.*);
	//@ requires(*The input `k` is a positive integer.*);
	//@ requires(*The length of `num` is greater than or equal to `k`.*);
	//@ requires(*`num` consists of only digits.*);
	//@ requires(*`num` does not have any leading zeros except for the zero itself.*);
	//@ ensures(*The output is a string representing the smallest possible integer after removing `k` digits from `num`.*);
	//@ ensures(*The output does not contain leading zeros.*);
    public String removeKdigits(String num, int k) {
        char[] list = new char[num.length()];
        int len = num.length() - k;
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            while (top > 0 && k > 0 && num.charAt(i) < list[top - 1]) {
                top--;
                k--;
            }
            list[top++] = num.charAt(i);
        }
        int number = 0;
        while (number < len && list[number] == '0') {
            number++;
        }
        return number == len ? "0" : new String(list, number, len - number);
    }
}