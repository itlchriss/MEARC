package g2001_2100.s2053_kth_distinct_string_in_an_array;

// #Easy #Array #String #Hash_Table #Counting #2022_05_24_Time_7_ms_(65.75%)_Space_44.7_MB_(58.42%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to `k`.*);
//@ ensures(*The value of `k` is greater than or equal to 1.*);
//@ ensures(*Each string in the input array `arr` consists of lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string is either a distinct string from the input array `arr` or an empty string.*);
//@ ensures(*If there are fewer than `k` distinct strings in the input array `arr`, the returned string is an empty string.*);
//@ ensures(*The returned string is one of the distinct strings from the input array `arr` in the order in which they appear.*);
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> m = new HashMap<>();
        for (String value : arr) {
            m.put(value, m.getOrDefault(value, 0) + 1);
        }
        int c = 0;
        for (String s : arr) {
            if (m.get(s) == 1) {
                ++c;
                if (c == k) {
                    return s;
                }
            }
        }
        return "";
    }
}