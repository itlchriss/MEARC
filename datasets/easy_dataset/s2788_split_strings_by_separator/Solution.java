package g2701_2800.s2788_split_strings_by_separator;

// #Easy #Array #String #2023_09_15_Time_3_ms_(99.98%)_Space_44.8_MB_(68.11%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `words` must not be null.*);
//@ ensures(*The input list `words` must not be empty.*);
//@ ensures(*The input list `words` must contain strings.*);
//@ ensures(*The input character `separator` must not be null.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output list must not be null.*);
//@ ensures(*The output list must not contain empty strings.*);
//@ ensures(*The output list must contain strings formed after splitting each string in `words` by `separator`.*);
//@ ensures(*The output list must maintain the same order as the strings were initially given in `words`.*);
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> list = new ArrayList<>();
        for (String str : words) {
            int si = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == separator) {
                    if (i > si) {
                        list.add(str.substring(si, i));
                    }
                    si = i + 1;
                }
            }
            if (si != str.length()) {
                list.add(str.substring(si, str.length()));
            }
        }
        return list;
    }
}