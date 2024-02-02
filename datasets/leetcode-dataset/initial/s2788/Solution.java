package g2701_2800.s2788_split_strings_by_separator;

// #Easy #Array #String #2023_09_15_Time_3_ms_(99.98%)_Space_44.8_MB_(68.11%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*In this example the resulting split of "|||" will contain only empty strings, so we return an empty array [].
Return _an array of strings containing the new strings formed after the splits, **excluding empty strings**._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
