package g0901_1000.s0937_reorder_data_in_log_files;

// #Easy #Array #String #Sorting #2022_03_30_Time_4_ms_(92.15%)_Space_46_MB_(66.46%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `logs` is not null.*);
//@ ensures(*The length of the input array `logs` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `logs` is a non-null string.*);
//@ ensures(*Each element in the input array `logs` has a length greater than or equal to 3.*);
//@ ensures(*Each element in the input array `logs` is a space-delimited string of words.*);
//@ ensures(*The first word in each element of the input array `logs` is the identifier.*);
//@ ensures(*The identifier in each element of the input array `logs` is a non-empty string.*);
//@ ensures(*Each word in the elements of the input array `logs` after the identifier is separated by a single space.*);
//@ ensures(*Each word in the elements of the input array `logs` after the identifier is either a lowercase English letter or a digit.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `reorderedLogs` is not null.*);
//@ ensures(*The length of the output array `reorderedLogs` is equal to the length of the input array `logs`.*);
//@ ensures(*Each element in the output array `reorderedLogs` is a non-null string.*);
//@ ensures(*Each element in the output array `reorderedLogs` has a length greater than or equal to 3.*);
//@ ensures(*Each element in the output array `reorderedLogs` is a space-delimited string of words.*);
//@ ensures(*The first word in each element of the output array `reorderedLogs` is the identifier.*);
//@ ensures(*The identifier in each element of the output array `reorderedLogs` is a non-empty string.*);
//@ ensures(*Each word in the elements of the output array `reorderedLogs` after the identifier is separated by a single space.*);
//@ ensures(*The letter-logs in the output array `reorderedLogs` come before all digit-logs.*);
//@ ensures(*The letter-logs in the output array `reorderedLogs` are sorted lexicographically by their contents.*);
//@ ensures(*If the contents of two letter-logs in the output array `reorderedLogs` are the same, they are sorted lexicographically by their identifiers.*);
//@ ensures(*The digit-logs in the output array `reorderedLogs` maintain their relative ordering.*);
    public String[] reorderLogFiles(String[] logs) {
        String[] res = new String[logs.length];
        List<String> letterLogs = new ArrayList<>();
        List<String> digitLogs = new ArrayList<>();
        for (String log : logs) {
            if (Character.isLetter(log.charAt(log.indexOf(" ") + 1))) {
                letterLogs.add(log);
            } else {
                digitLogs.add(log);
            }
        }
        letterLogs.sort(
                (o1, o2) -> {
                    int cmp =
                            o1.substring(o1.indexOf(" ") + 1)
                                    .compareTo(o2.substring(o2.indexOf(" ") + 1));
                    if (cmp == 0) {
                        return o1.compareTo(o2);
                    }
                    return cmp;
                });
        int i = 0;
        for (String log : letterLogs) {
            res[i++] = log;
        }
        for (String log : digitLogs) {
            res[i++] = log;
        }
        return res;
    }
}