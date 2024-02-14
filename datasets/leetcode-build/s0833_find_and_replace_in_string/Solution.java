package g0801_0900.s0833_find_and_replace_in_string;

// #Medium #Array #String #Sorting #2022_03_24_Time_3_ms_(70.10%)_Space_43.1_MB_(44.04%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of `s` is between 1 and 1000.*);
	//@ requires(*The arrays `indices`, `sources`, and `targets` are not null.*);
	//@ requires(*The length of `indices`, `sources`, and `targets` is equal to `k`.*);
	//@ requires(*The value of `k` is between 1 and 100.*);
	//@ requires(*Each element in `indices` is a valid index in the string `s`.*);
	//@ requires(*Each element in `sources` and `targets` is not null.*);
	//@ requires(*The length of each element in `sources` and `targets` is between 1 and 50.*);
	//@ requires(*The string `s` consists of only lowercase English letters.*);
	//@ requires(*Each element in `sources` and `targets` consists of only lowercase English letters.*);
	//@ ensures(*The method returns a string.*);
	//@ ensures(*The length of the returned string is the same as the length of the input string `s`.*);
	//@ ensures(*The returned string is the result of performing all replacement operations on `s`.*);
	//@ ensures(*For each replacement operation, if the substring `sources[i]` occurs at index `indices[i]` in `s`, it is replaced with `targets[i]`.*);
	//@ ensures(*If a replacement operation does not occur (the substring does not occur at the specified index), the corresponding character in the returned string remains the same as the original string `s`.*);
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, Integer> stringIndexToKIndex = new HashMap<>();
        for (int i = 0; i < indices.length; ++i) {
            stringIndexToKIndex.put(indices[i], i);
        }
        int indexIntoS = 0;
        while (indexIntoS < s.length()) {
            if (stringIndexToKIndex.containsKey(indexIntoS)) {
                String substringInSources = sources[stringIndexToKIndex.get(indexIntoS)];
                if (indexIntoS + substringInSources.length() <= s.length()) {
                    String substringInS =
                            s.substring(indexIntoS, indexIntoS + substringInSources.length());
                    if (substringInS.equals(substringInSources)) {
                        sb.append(targets[stringIndexToKIndex.get(indexIntoS)]);
                        indexIntoS += substringInS.length() - 1;
                    } else {
                        sb.append(s.charAt(indexIntoS));
                    }
                } else {
                    sb.append(s.charAt(indexIntoS));
                }
            } else {
                sb.append(s.charAt(indexIntoS));
            }
            indexIntoS++;
        }
        return sb.toString();
    }
}