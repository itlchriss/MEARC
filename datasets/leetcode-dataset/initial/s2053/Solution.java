package g2001_2100.s2053_kth_distinct_string_in_an_array;

// #Easy #Array #String #Hash_Table #Counting #2022_05_24_Time_7_ms_(65.75%)_Space_44.7_MB_(58.42%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Given an array of strings `arr`, and an integer `k`, return _the_ <code>k<sup>th</sup></code> _**distinct string** present in_ `arr`. If there are **fewer** than `k` distinct strings, return _an **empty string**_ `""`. Since k == 2, "a" is returned. All strings in arr are distinct, so the 1<sup>st</sup> string "aaa" is returned. **Explanation:** The only distinct string is "b". Since there are fewer than 3 distinct strings, we return an empty string "".*);

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