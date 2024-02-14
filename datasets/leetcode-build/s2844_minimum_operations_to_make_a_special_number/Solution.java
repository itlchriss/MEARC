package g2801_2900.s2844_minimum_operations_to_make_a_special_number;

// #Medium #String #Math #Greedy #Enumeration #2023_12_13_Time_1_ms_(100.00%)_Space_41_MB_(68.09%)

public class Solution {
	//@ requires(*The input string `num` must not be null.*);
	//@ requires(*The length of `num` must be between 1 and 100 (inclusive).*);
	//@ requires(*`num` must only consist of digits '0' through '9'.*);
	//@ requires(*`num` must not contain any leading zeros.*);
	//@ ensures(*The method returns an integer representing the minimum number of operations required to make `num` special.*);
	//@ ensures(*The resulting number after deleting digits from `num` is divisible by 25.*);
	//@ ensures(*The resulting number after deleting digits from `num` is the smallest possible number that is divisible by 25.*);
    public int minimumOperations(String num) {
        char[] number = num.toCharArray();
        int n = number.length;
        int zero = 0;
        int five = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (number[i] == '0') {
                if (zero == 1) {
                    return n - i - 2;
                } else {
                    zero++;
                }
            } else if (number[i] == '5') {
                if (zero == 1) {
                    return n - i - 2;
                }
                if (five == 0) {
                    five++;
                }
            } else if ((number[i] == '2' || number[i] == '7') && five == 1) {
                return n - i - 2;
            }
        }
        if (zero == 1) {
            return n - 1;
        }
        return n;
    }
}