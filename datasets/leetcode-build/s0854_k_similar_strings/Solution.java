package g0801_0900.s0854_k_similar_strings;

// #Hard #String #Breadth_First_Search #2022_03_27_Time_2_ms_(99.58%)_Space_42.6_MB_(97.17%)

public class Solution {
	//@ requires(*The input strings `s1` and `s2` are not null.*);
	//@ requires(*The lengths of `s1` and `s2` are equal.*);
	//@ requires(*`s1` and `s2` contain only lowercase letters from the set `{'a', 'b', 'c', 'd', 'e', 'f'}`.*);
	//@ requires(*`s2` is an anagram of `s1`.*);
	//@ ensures(*The method returns an integer `k`.*);
	//@ ensures(*`k` is the smallest number of swaps required to make `s1` and `s2` k-similar.*);
    public int kSimilarity(String a, String b) {
        int ans = 0;
        char[] achars = a.toCharArray();
        char[] bchars = b.toCharArray();
        ans += getAllPerfectMatches(achars, bchars);
        for (int i = 0; i < achars.length; i++) {
            if (achars[i] == bchars[i]) {
                continue;
            }
            return ans + checkAllOptions(achars, bchars, i, b);
        }
        return ans;
    }

    private int checkAllOptions(char[] achars, char[] bchars, int i, String b) {
        int ans = Integer.MAX_VALUE;
        for (int j = i + 1; j < achars.length; j++) {
            if (achars[j] == bchars[i] && achars[j] != bchars[j]) {
                swap(achars, i, j);
                ans = Math.min(ans, 1 + kSimilarity(new String(achars), b));
                swap(achars, i, j);
            }
        }
        return ans;
    }

    private int getAllPerfectMatches(char[] achars, char[] bchars) {
        int ans = 0;
        for (int i = 0; i < achars.length; i++) {
            if (achars[i] == bchars[i]) {
                continue;
            }
            for (int j = i + 1; j < achars.length; j++) {
                if (achars[j] == bchars[i] && bchars[j] == achars[i]) {
                    swap(achars, i, j);
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    private void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}