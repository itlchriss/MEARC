package g1101_1200.s1111_maximum_nesting_depth_of_two_valid_parentheses_strings;

// #Medium #String #Stack #2023_06_01_Time_1_ms_(100.00%)_Space_43.8_MB_(23.00%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return an `answer` array (of length `seq.length`) that encodes such a choice of `A` and `B`: `answer[i] = 0` if `seq[i]` is part of `A`, else `answer[i] = 1`. Note that even though multiple answers may exist, you may return any of them.
Return an `answer` array (of length `seq.length`) that encodes such a choice of `A` and `B`: `answer[i] = 0` if `seq[i]` is part of `A`, else `answer[i] = 1`. Note that even though multiple answers may exist, you may return any of them.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        char[] chars = seq.toCharArray();
        int depth = 0;
        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                depth++;
                if (depth % 2 == 0) {
                    ans[i] = 0;
                } else {
                    ans[i] = 1;
                }
            } else {
                if (depth % 2 == 0) {
                    ans[i] = 0;
                } else {
                    ans[i] = 1;
                }
                depth--;
            }
        }
        return ans;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
