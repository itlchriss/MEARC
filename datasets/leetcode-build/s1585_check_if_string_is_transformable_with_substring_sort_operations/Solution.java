package g1501_1600.s1585_check_if_string_is_transformable_with_substring_sort_operations;

// #Hard #String #Sorting #Greedy #2022_04_11_Time_20_ms_(97.22%)_Space_54.4_MB_(72.22%)

public class Solution {
	//@ requires(*The lengths of strings `s` and `t` are equal.*);
	//@ requires(*The lengths of strings `s` and `t` are between 1 and 10^5.*);
	//@ requires(*Strings `s` and `t` consist of only digits.*);
	//@ ensures(*The method returns `true` if it is possible to transform `s` into `t` using the given operation, otherwise it returns `false`.*);
    public boolean isTransformable(String s, String t) {
        int n = s.length();
        if (n != t.length()) {
            return false;
        }
        int[] cnt = new int[10];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - '0']++;
        }
        for (int i = 0; i < n; i++) {
            cnt[t.charAt(i) - '0']--;
        }
        for (int i = 0; i < 10; i++) {
            if (cnt[i] != 0) {
                return false;
            }
        }
        int[] sCnt = new int[10];
        int[] tCnt = new int[10];
        for (int i = 0; i < n; i++) {
            int sAsci = s.charAt(i) - '0';
            int tAsci = t.charAt(i) - '0';
            sCnt[sAsci]++;
            if (tCnt[sAsci] >= sCnt[sAsci] || (sAsci == tAsci && tCnt[sAsci] + 1 >= sCnt[sAsci])) {
                int rem = 0;
                for (int j = 0; j < sAsci; j++) {
                    if (sCnt[j] - tCnt[j] > 0) {
                        rem++;
                    }
                }
                if (rem > 0) {
                    return false;
                }
            }
            if (sCnt[tAsci] >= tCnt[tAsci] + 1) {
                int rem = 0;
                for (int j = tAsci; j < 10; j++) {
                    if (tCnt[j] - sCnt[j] > 0) {
                        rem++;
                    }
                }
                if (rem > 0) {
                    return false;
                }
            }
            tCnt[tAsci]++;
        }
        return true;
    }
}