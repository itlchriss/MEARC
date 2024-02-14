package g0201_0300.s0205_isomorphic_strings;

// #Easy #String #Hash_Table #Level_1_Day_2_String
// #2022_06_28_Time_2_ms_(99.97%)_Space_43.3_MB_(32.68%)

public class Solution {
	//@ requires(*The input strings `s` and `t` are not null.*);
	//@ requires(*The lengths of `s` and `t` are equal.*);
	//@ requires(*The lengths of `s` and `t` are not greater than 5 * 10^`s` and `t` consist of valid ASCII characters.*);
	//@ ensures(*The method returns `true` if `s` and `t` are isomorphic, and `false` otherwise.*);
	//@ ensures(*All occurrences of a character in `s` are replaced with another character in `t`.*);
	//@ ensures(*The order of characters in `s` is preserved in `t`.*);
	//@ ensures(*No two characters in `s` map to the same character in `t`.*);
	//@ ensures(*A character in `s` may map to itself in `t`.*);
    public boolean isIsomorphic(String s, String t) {
        int[] map = new int[128];
        char[] str = s.toCharArray();
        char[] tar = t.toCharArray();
        int n = str.length;
        for (int i = 0; i < n; i++) {
            if (map[tar[i]] == 0) {
                if (search(map, str[i], tar[i]) != -1) {
                    return false;
                }
                map[tar[i]] = str[i];
            } else {
                if (map[tar[i]] != str[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int search(int[] map, int tar, int skip) {
        for (int i = 0; i < 128; i++) {
            if (i == skip) {
                continue;
            }
            if (map[i] != 0 && map[i] == tar) {
                return i;
            }
        }
        return -1;
    }
}