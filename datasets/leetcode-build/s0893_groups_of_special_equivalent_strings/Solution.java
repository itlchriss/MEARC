package g0801_0900.s0893_groups_of_special_equivalent_strings;

// #Medium #Array #String #Hash_Table #2022_03_28_Time_3_ms_(99.09%)_Space_43.3_MB_(79.09%)

import java.util.HashSet;

public class Solution {
	//@ requires(*The input array `words` is not null.*);
	//@ requires(*The length of `words` is greater than or equal to - The length of each string in `words` is greater than or equal to 1 and less than or equal to - Each string in `words` consists of lowercase English letters.*);
	//@ requires(*All the strings in `words` have the same length.*);
	//@ ensures(*The method returns an integer representing the number of groups of special-equivalent strings from `words`.*);
    public int numSpecialEquivGroups(String[] words) {
        HashSet<String> set = new HashSet<>();
        int result = 0;
        for (String str : words) {
            if (set.add(getHashBySwap(str.toCharArray()))) {
                result++;
            }
        }
        return result;
    }

    private String getHashBySwap(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            int j = i + 2;
            while (j < chars.length) {
                if (chars[i] > chars[j]) {
                    char temp = chars[j];
                    chars[j] = chars[i];
                    chars[i] = temp;
                }
                j += 2;
            }
        }
        return String.valueOf(chars);
    }
}