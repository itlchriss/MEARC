package g1401_1500.s1461_check_if_a_string_contains_all_binary_codes_of_size_k;

// #Medium #String #Hash_Table #Bit_Manipulation #Hash_Function #Rolling_Hash
// #2022_03_29_Time_169_ms_(52.07%)_Space_95.1_MB_(49.18%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is greater than or equal to `k`.*);
	//@ requires(*The value of `k` is greater than or equal to 1.*);
	//@ ensures(*The method returns `true` if every binary code of length `k` is a substring of `s`.*);
	//@ ensures(*The method returns `false` if there is at least one binary code of length `k` that is not a substring of `s`.*);
    public boolean hasAllCodes(String s, int k) {
        int total = (int) Math.pow(2, k);
        int start = 0;
        int end = start + k;
        Set<String> st = new HashSet<>();
        while (end <= s.length()) {
            String sbStr = s.substring(start, end);
            st.add(sbStr);
            if (st.size() == total) {
                return true;
            }
            start++;
            end++;
        }
        return false;
    }
}