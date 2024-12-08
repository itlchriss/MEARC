package g1001_1100.s1078_occurrences_after_bigram;

// #Easy #String #2022_02_26_Time_0_ms_(100.00%)_Space_40.4_MB_(48.38%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input text is not null.*);
//@ ensures(*The input first and second strings are not null.*);
//@ ensures(*The input text length is between 1 and 1000.*);
//@ ensures(*The input first and second strings length is between 1 and 10.*);
//@ ensures(*The input text consists of lowercase English letters and spaces.*);
//@ ensures(*All the words in the input text are separated by a single space.*);
//@ ensures(*The input first and second strings consist of lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array is not null.*);
//@ ensures(*The output array contains all the words "third" for each occurrence of "first second third" in the input text.*);
    public String[] findOcurrences(String text, String first, String second) {
        List<String> list = new ArrayList<>();
        String[] str = text.split(" ");
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(first) && str.length - 1 >= i + 2 && str[i + 1].equals(second)) {
                list.add(str[i + 2]);
            }
        }
        String[] s = new String[list.size()];
        int j = 0;
        for (String ele : list) {
            s[j++] = ele;
        }
        return s;
    }
}