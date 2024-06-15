package g0401_0500.s0415_add_strings;

// #Easy #String #Math #Simulation #Data_Structure_II_Day_6_String
// #2022_07_16_Time_3_ms_(82.41%)_Space_43.1_MB_(66.56%)

public class Solution {
// requires !num1.startsWith("0") || num1.equals("0")
// requires !num2.startsWith("0") || num2.equals("0")
// requires num1.length() <= 10000
// ensures \result != null
// ensures \result.matches("\\d+")
// requires num2.length() <= 10000
// ensures \result.length() == num1.length() || \result.length() == num1.length() + 1
// ensures Integer.parseInt(\result) == Integer.parseInt(num1) + Integer.parseInt(num2)
// requires num1 != null && num2 != null
// requires num1.matches("\\d+")
// requires num2.matches("\\d+")
    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
                i >= 0 || j >= 0 || carry != 0;
                i--, j--) {
            int sum = carry;
            if (i >= 0) {
                sum += Character.digit(num1.charAt(i), 10);
            }
            if (j >= 0) {
                sum += Character.digit(num2.charAt(j), 10);
            }
            carry = sum - 10;
            result.append(sum % 10);
        }
        return result.reverse().toString();
    }
}
