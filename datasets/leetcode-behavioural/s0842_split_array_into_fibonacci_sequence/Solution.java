package g0801_0900.s0842_split_array_into_fibonacci_sequence;

// #Medium #String #Backtracking #2022_03_24_Time_4_ms_(62.81%)_Space_43.6_MB_(32.65%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S5413")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `num` must not be null.*);
//@ ensures(*The input string `num` must contain only digits.*);
//@ ensures(*The length of the input string `num` must be between 1 and 200 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a list of integers.*);
//@ ensures(*The list of integers represents a Fibonacci-like sequence.*);
//@ ensures(*Each integer in the list is non-negative and fits in a 32-bit signed integer type.*);
//@ ensures(*The length of the list is at least 3.*);
//@ ensures(*The sum of any two consecutive integers in the list is equal to the next integer in the list.*);
//@ ensures(*The list is a valid split of the input string `num`.*);
//@ ensures(*If a valid split is not possible, an empty list is returned.*);
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> res = new ArrayList<>();
        solve(num, res, 0);
        return res;
    }

    private boolean solve(String s, List<Integer> res, int idx) {
        if (idx == s.length() && res.size() >= 3) {
            return true;
        }
        for (int i = idx; i < s.length(); i++) {
            if (s.charAt(idx) == '0' && i > idx) {
                return false;
            }
            long num = Long.parseLong(s.substring(idx, i + 1));
            if (num > Integer.MAX_VALUE) {
                return false;
            }
            int size = res.size();
            if (size >= 2 && num > res.get(size - 1) + res.get(size - 2)) {
                return false;
            }
            if (size <= 1 || num == res.get(size - 1) + res.get(size - 2)) {
                res.add((int) num);
                if (solve(s, res, i + 1)) {
                    return true;
                }
                res.remove(res.size() - 1);
            }
        }
        return false;
    }
}