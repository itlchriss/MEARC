package g2301_2400.s2301_match_substring_after_replacement;

// #Hard #Array #String #Hash_Table #String_Matching
// #2022_06_16_Time_205_ms_(94.20%)_Space_52.1_MB_(54.60%)

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unchecked")
public class Solution {
    private char[] c1;
    private char[] c2;
    private Set<Character>[] al;
	//@ requires(*1. The length of `sub` is at least 1 and at most the length of `s`.*);
	//@ requires(*2. The length of `mappings` is at most 1000.*);
	//@ requires(*3. Each element in `mappings` has a length of 2.*);
	//@ requires(*4. The characters in `sub` cannot be replaced more than once.*);
	//@ requires(*5. The characters in `s`, `sub`, `mappings`, `old_i`, and `new_i` are all valid English letters or digits.*);
	//@ ensures(*1. The method returns `true` if it is possible to make `sub` a substring of `s` by replacing zero or more characters according to `mappings`.*);
	//@ ensures(*2. The method returns `false` if it is not possible to make `sub` a substring of `s` by replacing zero or more characters according to `mappings`.*);

    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        c1 = s.toCharArray();
        c2 = sub.toCharArray();
        al = new Set[75];
        for (int i = 0; i < 75; i++) {
            Set<Character> temp = new HashSet<>();
            al[i] = temp;
        }
        for (char[] mapping : mappings) {
            al[mapping[0] - '0'].add(mapping[1]);
        }
        return ans(c1.length, c2.length) == 1;
    }

    private int ans(int m, int n) {
        if (m == 0) {
            return 0;
        }
        if (ans(m - 1, n) == 1) {
            return 1;
        }
        if (m >= n && (c1[m - 1] == c2[n - 1] || al[c2[n - 1] - '0'].contains(c1[m - 1]))) {
            while (n >= 1 && (c1[m - 1] == c2[n - 1] || al[c2[n - 1] - '0'].contains(c1[m - 1]))) {
                n--;
                m--;
            }
            if (n == 0) {
                return 1;
            }
        }
        return 0;
    }
}