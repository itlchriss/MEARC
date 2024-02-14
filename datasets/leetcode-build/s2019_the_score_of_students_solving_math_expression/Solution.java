package g2001_2100.s2019_the_score_of_students_solving_math_expression;

// #Hard #Array #String #Dynamic_Programming #Math #Stack #Memoization
// #2022_05_24_Time_435_ms_(89.93%)_Space_47_MB_(87.77%)

import java.util.ArrayDeque;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class Solution {
    private HashSet<Integer>[][] dp;
	//@ requires(*1. The input string `s` must be a valid math expression containing only digits `0-9`, addition symbols `'+'`, and multiplication symbols `'*'`.*);
	//@ requires(*2. The length of the input string `s` must be between 3 and 31 (inclusive).*);
	//@ requires(*3. The input array `answers` must have a length `n` where `1 <= n <= 10^4`.*);
	//@ requires(*4. Each element in the input array `answers` must be between 0 and 1000 (inclusive).*);
	//@ requires(*5. The count of all operators (`'+'` and `'*'`) in the math expression must be between 1 and 15 (inclusive).*);
	//@ requires(*6. The correct answer of the expression must be in the range of 0 to 1000 (inclusive).*);
	//@ ensures(*1. The method should return an integer representing the sum of the points of the students.*);
	//@ ensures(*2. The sum of the points should be between 0 and 5 times the length of the input array `answers` (inclusive).*);
	//@ ensures(*3. If an answer equals the correct answer of the expression, the student will be rewarded 5 points.*);
	//@ ensures(*4. If the answer could be interpreted as if the student applied the operators in the wrong order but had correct arithmetic, the student will be rewarded 2 points.*);
	//@ ensures(*5. If the answer does not fall into the above two categories, the student will be rewarded 0 points.*);

    public int scoreOfStudents(String s, int[] answers) {
        ArrayDeque<Integer> st = new ArrayDeque<>();
        int n = s.length();
        int i = 0;
        dp = new HashSet[n][n];
        while (i < n) {
            if (s.charAt(i) - '0' >= 0 && s.charAt(i) - '9' <= 0) {
                st.push(s.charAt(i) - '0');
                i++;
            } else if (s.charAt(i) == '*') {
                int cur = st.pop() * (s.charAt(i + 1) - '0');
                i += 2;
                st.push(cur);
            } else {
                i++;
            }
        }
        int res = 0;
        int ret = 0;
        while (!st.isEmpty()) {
            res += st.pop();
        }
        HashSet<Integer> wrong = opts(0, n - 1, s);
        for (int ans : answers) {
            if (ans == res) {
                ret += 5;
            } else if (wrong.contains(ans)) {
                ret += 2;
            }
        }
        return ret;
    }

    private HashSet<Integer> opts(int i, int j, String s) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        if (i == j) {
            HashSet<Integer> res = new HashSet<>();
            res.add(s.charAt(i) - '0');
            dp[i][j] = res;
            return res;
        }
        HashSet<Integer> res = new HashSet<>();
        for (int x = i + 1; x < j; x += 2) {
            char op = s.charAt(x);
            HashSet<Integer> left = opts(i, x - 1, s);
            HashSet<Integer> right = opts(x + 1, j, s);
            if (op == '*') {
                for (int l : left) {
                    for (int r : right) {
                        if (l * r <= 1000) {
                            res.add(l * r);
                        }
                    }
                }
            } else {
                for (int l : left) {
                    for (int r : right) {
                        if (l + r <= 1000) {
                            res.add(l + r);
                        }
                    }
                }
            }
        }
        dp[i][j] = res;
        return res;
    }
}