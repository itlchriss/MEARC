package g0701_0800.s0777_swap_adjacent_in_lr_string;

// #Medium #String #Two_Pointers #2022_03_26_Time_4_ms_(85.13%)_Space_44.1_MB_(54.82%)

public class Solution {
	//@ requires(*The length of `start` and `end` strings should be the same.*);
	//@ requires(*Both `start` and `end` strings should only consist of characters `'L'`, `'R'`, and `'X'`.*);
	//@ ensures(*The method should return `true` if there exists a sequence of moves to transform `start` string to `end` string.*);
	//@ ensures(*The method should return `false` if there does not exist a sequence of moves to transform `start` string to `end` string.*);
    public boolean canTransform(String start, String end) {
        int i1 = 0;
        int i2 = 0;
        char[] s1 = start.toCharArray();
        char[] s2 = end.toCharArray();
        while (i1 < s1.length && i2 < s2.length) {
            while (i1 < s1.length && s1[i1] == 'X') {
                i1++;
            }
            while (i2 < s2.length && s2[i2] == 'X') {
                i2++;
            }
            if (i1 == s1.length && i2 == s2.length) {
                return true;
            }
            if (i1 == s1.length || i2 == s2.length) {
                return false;
            }
            char c1 = s1[i1];
            char c2 = s2[i2];
            if (c1 != c2) {
                return false;
            }
            if (c1 == 'L' && i1 < i2) {
                return false;
            }
            if (c1 == 'R' && i1 > i2) {
                return false;
            }
            i1++;
            i2++;
        }
        while (i1 < s1.length && start.charAt(i1) == 'X') {
            i1++;
        }
        while (i2 < s2.length && end.charAt(i2) == 'X') {
            i2++;
        }
        return i1 == s1.length && i2 == s2.length;
    }
}