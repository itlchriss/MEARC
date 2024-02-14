package g1501_1600.s1528_shuffle_string;

// #Easy #Array #String #2022_04_09_Time_2_ms_(54.77%)_Space_45_MB_(34.67%)

public class Solution {
	//@ requires(*1. The length of string `s` is equal to the length of the `indices` array.*);
	//@ requires(*2. The length of string `s` is greater than or equal to 1.*);
	//@ requires(*3. The length of string `s` is less than or equal to 100.*);
	//@ requires(*4. The `indices` array contains unique values.*);
	//@ requires(*5. Each value in the `indices` array is greater than or equal to 0.*);
	//@ requires(*6. Each value in the `indices` array is less than the length of string `s`.*);
	//@ ensures(*1. The returned string is the shuffled string where the character at the i-th position in the original string `s` is moved to the position specified by `indices[i]` in the shuffled string.*);
	//@ ensures(*2. The length of the returned string is equal to the length of the original string `s`.*);
	//@ ensures(*3. The returned string consists of only lowercase English letters.*);
    public String restoreString(String s, int[] indices) {
        char[] c = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            int index = findIndex(indices, i);
            c[i] = s.charAt(index);
        }
        return new String(c);
    }

    private static int findIndex(int[] indices, int i) {
        for (int j = 0; j < indices.length; j++) {
            if (indices[j] == i) {
                return j;
            }
        }
        return 0;
    }
}