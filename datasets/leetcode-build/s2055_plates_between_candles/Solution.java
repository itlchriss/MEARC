package g2001_2100.s2055_plates_between_candles;

// #Medium #Array #String #Binary_Search #Prefix_Sum
// #2022_05_26_Time_10_ms_(92.49%)_Space_164.8_MB_(5.53%)

public class Solution {
	//@ requires(*The input string `s` must be a valid string consisting of only `'*'` and `'|'` characters.*);
	//@ requires(*The input 2D integer array `queries` must be a valid array with each element `queries[i]` containing exactly two integers `left_i` and `right_i`.*);
	//@ requires(*The integers `left_i` and `right_i` in each query `queries[i]` must be valid indices within the range of the string `s`.*);
	//@ ensures(*The output array `answer` must be a valid integer array with each element `answer[i]` containing the number of plates between candles for the corresponding query `queries[i]`.*);
	//@ ensures(*The length of the output array `answer` must be equal to the length of the input array `queries`.*);
	//@ ensures(*The order of the elements in the output array `answer` must correspond to the order of the queries in the input array `queries`.*);
    public int[] platesBetweenCandles(String s, int[][] queries) {
        char[] sa = s.toCharArray();
        int n = sa.length;
        int[] nextCandle = new int[n + 1];
        nextCandle[n] = -1;
        for (int i = n - 1; i >= 0; i--) {
            nextCandle[i] = sa[i] == '|' ? i : nextCandle[i + 1];
        }
        int[] prevCandle = new int[n];
        int[] prevPlates = new int[n];
        int countPlate = 0;
        if (sa[0] == '*') {
            countPlate = 1;
            prevCandle[0] = -1;
        }
        for (int i = 1; i < n; i++) {
            if (sa[i] == '|') {
                prevCandle[i] = i;
                prevPlates[i] = countPlate;
            } else {
                prevCandle[i] = prevCandle[i - 1];
                countPlate++;
            }
        }
        int len = queries.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];
            int curPlates = 0;
            int endCandle = prevCandle[end];
            if (endCandle >= start) {
                int startCandle = nextCandle[start];

                curPlates = prevPlates[endCandle] - prevPlates[startCandle];
            }
            res[i] = curPlates;
        }
        return res;
    }
}