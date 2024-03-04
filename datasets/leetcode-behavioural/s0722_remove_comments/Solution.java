package g0701_0800.s0722_remove_comments;

// #Medium #Array #String #2022_03_24_Time_1_ms_(80.24%)_Space_42.3_MB_(53.67%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S135")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `source` is a non-null array of strings.*);
//@ ensures(*Each string in `source` represents a line of code in a C++ program.*);
//@ ensures(*The length of each string in `source` is between 0 and 80 characters.*);
//@ ensures(*Each character in `source` is a printable ASCII character.*);
//@ ensures(*There are no single-quote or double-quote characters in `source`.*);
//@ ensures(*Every open block comment in `source` is eventually closed.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned list of strings represents the source code after removing comments.*);
//@ ensures(*Each string in the returned list is non-empty.*);
//@ ensures(*The returned list has the same format as the input `source`.*);
    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean multiComment = false;
        for (String line : source) {
            int n = line.length();
            int index = 0;
            while (index < n) {
                char ch = line.charAt(index);
                if (!multiComment && ch == '/') {
                    index++;
                    if (index >= n) {
                        sb.append(ch);
                        continue;
                    }
                    if (line.charAt(index) == '/') {
                        break;
                    } else if (line.charAt(index) == '*') {
                        multiComment = true;
                    } else {
                        sb.append(ch).append(line.charAt(index));
                    }
                } else if (multiComment && ch == '*') {
                    index++;
                    if (index >= n) {
                        continue;
                    }
                    if (line.charAt(index) == '/') {
                        multiComment = false;
                    } else {
                        index--;
                    }
                } else if (!multiComment) {
                    sb.append(ch);
                }
                index++;
            }
            if (sb.length() > 0 && !multiComment) {
                result.add(sb.toString());
                sb.setLength(0);
            }
        }
        return result;
    }
}