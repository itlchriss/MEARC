package g1201_1300.s1234_replace_the_substring_for_balanced_string;

// #Medium #String #Sliding_Window #2022_03_12_Time_7_ms_(89.84%)_Space_44.4_MB_(33.77%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is a multiple of 4.*);
//@ ensures(*The input string `s` contains only the characters 'Q', 'W', 'E', and 'R'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum length of the substring that can be replaced to make the string balanced.*);
//@ ensures(*If the input string `s` is already balanced, the output is 0.*);
    public int balancedString(String s) {
        int n = s.length();
        int ans = n;
        int excess = 0;
        int[] cnt = new int[128];
        cnt['Q'] = cnt['W'] = cnt['E'] = cnt['R'] = -n / 4;
        for (char ch : s.toCharArray()) {
            if (++cnt[ch] == 1) {
                excess++;
            }
        }
        if (excess == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        while (i < n) {
            if (--cnt[s.charAt(i)] == 0) {
                excess--;
            }
            while (excess == 0) {
                if (++cnt[s.charAt(j)] == 1) {
                    excess++;
                }
                ans = Math.min(i - j + 1, ans);
                j++;
            }
            i++;
        }

        return ans;
    }
}