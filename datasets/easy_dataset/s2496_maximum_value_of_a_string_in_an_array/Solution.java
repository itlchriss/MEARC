package g2401_2500.s2496_maximum_value_of_a_string_in_an_array;

// #Easy #Array #String #2023_01_27_Time_1_ms_(92.00%)_Space_41.7_MB_(54.56%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `strs` is not null.*);
//@ ensures(*The length of the input array `strs` is greater than 0.*);
//@ ensures(*Each element in the input array `strs` is not null.*);
//@ ensures(*Each element in the input array `strs` is not an empty string.*);
//@ ensures(*Each element in the input array `strs` consists of only lowercase English letters and digits.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value is the maximum value of any string in the input array `strs`.*);
//@ ensures(*The return value is the numeric representation of the string in base 10 if it comprises of digits only.*);
//@ ensures(*The return value is the length of the string if it does not comprise of digits only.*);
    public int maximumValue(String[] strs) {
        int maxVal = 0;
        for (String s : strs) {
            maxVal = Math.max(maxVal, value(s));
        }
        return maxVal;
    }

    private int value(String s) {
        int total = 0;
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                total = total * 10 + (ch - '0');
            } else {
                return s.length();
            }
        }
        return total;
    }
}