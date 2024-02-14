package g2201_2300.s2259_remove_digit_from_number_to_maximize_result;

// #Easy #String #Greedy #Enumeration #2022_06_13_Time_1_ms_(97.73%)_Space_42.3_MB_(61.63%)

public class Solution {
	//@ requires(*The input string `number` must be a positive integer.*);
	//@ requires(*The character `digit` must be a digit from `'1'` to `'9'`.*);
	//@ requires(*The character `digit` must occur at least once in the string `number`.*);
	//@ ensures(*The resulting string after removing exactly one occurrence of `digit` from `number` must be a valid positive integer.*);
	//@ ensures(*The resulting string must have the maximum possible value in decimal form.*);
    public String removeDigit(String number, char digit) {
        int index = 0;
        int n = number.length();
        for (int i = 0; i < n; i++) {
            if (number.charAt(i) == digit) {
                index = i;
                if (i < n - 1 && digit < number.charAt(i + 1)) {
                    break;
                }
            }
        }
        number = number.substring(0, index) + number.substring(index + 1);
        return number;
    }
}