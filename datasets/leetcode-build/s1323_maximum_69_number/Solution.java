package g1301_1400.s1323_maximum_69_number;

// #Easy #Math #Greedy #2022_03_19_Time_3_ms_(32.03%)_Space_39.5_MB_(67.69%)

import java.util.stream.IntStream;

public class Solution {
	//@ requires(*The input `num` is a positive integer.*);
	//@ requires(*The input `num` consists only of digits `6` and `9`.*);
	//@ ensures(*The output is the maximum number that can be obtained by changing at most one digit in `num`.*);
	//@ ensures(*The output is a positive integer.*);
	//@ ensures(*The output consists only of digits `6` and `9`.*);
	//@ ensures(*If no digit in `num` can be changed to obtain a larger number, the output is equal to the input `num`.*);
    public int maximum69Number(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        IntStream.range(0, chars.length)
                .filter(i -> chars[i] == '6')
                .findFirst()
                .ifPresent(i -> chars[i] = '9');
        return Integer.parseInt(new String(chars));
    }
}