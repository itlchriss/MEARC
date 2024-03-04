package g2501_2600.s2564_substring_xor_queries;

// #Medium #Array #String #Hash_Table #Bit_Manipulation
// #2023_08_21_Time_26_ms_(95.83%)_Space_84.1_MB_(80.56%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` must be a binary string.*);
//@ ensures(*The length of `s` must be between 1 and 10^4.*);
//@ ensures(*Each character in `s` must be either '0' or '1'.*);
//@ ensures(*The input array `queries` must be a 2D integer array.*);
//@ ensures(*The length of `queries` must be between 1 and 10^5.*);
//@ ensures(*Each element in `queries` must be an array of length 2.*);
//@ ensures(*The first element of each query must be a non-negative integer.*);
//@ ensures(*The second element of each query must be a non-negative integer.*);
//@ ensures(*The first element of each query must be less than or equal to 10^9.*);
//@ ensures(*The second element of each query must be less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `ans` must be a 2D integer array.*);
//@ ensures(*The length of `ans` must be equal to the length of `queries`.*);
//@ ensures(*Each element in `ans` must be an array of length 2.*);
//@ ensures(*The first element of each element in `ans` must be an integer representing the left endpoint of the substring.*);
//@ ensures(*The second element of each element in `ans` must be an integer representing the right endpoint of the substring.*);
//@ ensures(*If there is no substring that satisfies the condition, the corresponding element in `ans` must be [-1, -1].*);
//@ ensures(*If there are multiple substrings that satisfy the condition, the corresponding element in `ans` must be the one with the minimum left endpoint.*);
    public int[][] substringXorQueries(String s, int[][] queries) {
        int[][] ans = new int[queries.length][2];
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                map.putIfAbsent(0, new int[] {i, i});
                continue;
            }
            int value = 0;
            for (int j = i; j <= Math.min(i + 30, s.length() - 1); j++) {
                value = (value << 1) + (s.charAt(j) - '0');
                map.putIfAbsent(value, new int[] {i, j});
            }
        }
        int i = 0;
        for (int[] q : queries) {
            ans[i++] = map.getOrDefault(q[0] ^ q[1], new int[] {-1, -1});
        }
        return ans;
    }
}