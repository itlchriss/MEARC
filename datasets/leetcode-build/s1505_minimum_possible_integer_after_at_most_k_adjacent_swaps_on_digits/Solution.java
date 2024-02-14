package g1501_1600.s1505_minimum_possible_integer_after_at_most_k_adjacent_swaps_on_digits;

// #Hard #String #Greedy #Segment_Tree #Binary_Indexed_Tree
// #2022_04_07_Time_12_ms_(96.77%)_Space_45.8_MB_(80.65%)

import java.util.Arrays;

public class Solution {
	//@ requires(*1. The input string `num` must not be null.*);
	//@ requires(*2. The input string `num` must not be empty.*);
	//@ requires(*3. The input string `num` must consist of only digits.*);
	//@ requires(*4. The input string `num` must not contain leading zeros.*);
	//@ requires(*5. The input integer `k` must be greater than or equal to 1.*);
	//@ requires(*6. The input integer `k` must be less than or equal to 10^4.*);
	//@ ensures(*1. The output string must not be null.*);
	//@ ensures(*2. The output string must not be empty.*);
	//@ ensures(*3. The output string must consist of only digits.*);
	//@ ensures(*4. The output string must not contain leading zeros.*);
	//@ ensures(*5. The output string must represent the minimum possible integer that can be obtained by swapping adjacent digits at most `k` times.*);
    public String minInteger(String num, int k) {
        StringBuilder sb = new StringBuilder();
        int[] digitPos = new int[10];
        int[] reduceMove = new int[10];
        int matchAmount = 0;
        char[] chars = num.toCharArray();
        Arrays.fill(digitPos, chars.length);
        for (int i = 0; i < chars.length; i++) {
            int cur = chars[i] - '0';
            if (digitPos[cur] == chars.length) {
                digitPos[cur] = i;
                matchAmount++;
                if (matchAmount == 10) {
                    break;
                }
            }
        }
        int curIndex = 0;
        while (k > 0 && curIndex < chars.length) {
            for (int digit = 0; digit <= 9; digit++) {
                if (digitPos[digit] < chars.length && digitPos[digit] - reduceMove[digit] <= k) {
                    sb.append(chars[digitPos[digit]]);
                    k -= (digitPos[digit] - reduceMove[digit]);
                    curIndex++;
                    reduceMove[digit]++;
                    for (int j = 0; j <= 9; j++) {
                        if (j != digit && digitPos[j] > digitPos[digit]) {
                            reduceMove[j]++;
                        }
                    }
                    boolean find = false;
                    for (int next = digitPos[digit] + 1; next < chars.length; next++) {
                        int cur = chars[next] - '0';
                        if (cur == digit) {
                            find = true;
                            digitPos[digit] = next;
                            break;
                        }
                        if (next < digitPos[cur]) {
                            reduceMove[digit]++;
                        }
                    }
                    if (!find) {
                        digitPos[digit] = chars.length;
                    }
                    break;
                }
            }
        }
        int start = Arrays.stream(digitPos).min().getAsInt();
        for (int i = start; i < chars.length; i++) {
            if (digitPos[chars[i] - '0'] <= i) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
}