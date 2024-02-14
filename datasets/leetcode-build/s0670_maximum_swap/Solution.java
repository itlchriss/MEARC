package g0601_0700.s0670_maximum_swap;

// #Medium #Math #Greedy #2022_03_22_Time_1_ms_(80.14%)_Space_40.7_MB_(57.21%)

public class Solution {
	//@ requires(*The input `num` is a non-negative integer.*);
	//@ requires(*The input `num` is less than or equal to 10^*);
	//@ ensures(*The output is the maximum valued number that can be obtained by swapping two digits at most once.*);
	//@ ensures(*The output is an integer.*);
    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = chars.length - 1;
            int indx = i;
            char c = chars[i];
            while (j > i) {
                if (chars[j] > c) {
                    c = chars[j];
                    indx = j;
                }
                j--;
            }
            if (indx != i) {
                char temp = chars[i];
                chars[i] = chars[indx];
                chars[indx] = temp;
                return Integer.parseInt(String.valueOf(chars));
            }
        }
        return num;
    }
}