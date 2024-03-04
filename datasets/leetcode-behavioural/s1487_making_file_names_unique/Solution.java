package g1401_1500.s1487_making_file_names_unique;

// #Medium #Array #String #Hash_Table #2022_04_01_Time_36_ms_(98.46%)_Space_50.7_MB_(97.99%)

import java.util.HashMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `names` is not null.*);
//@ ensures(*The length of the input array `names` is greater than 0.*);
//@ ensures(*Each element in the input array `names` is not null.*);
//@ ensures(*Each element in the input array `names` is a string of lowercase English letters, digits, and/or round brackets.*);
//@ ensures(*The length of each element in the input array `names` is greater than 0 and less than or equal to 20.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `ans` is not null.*);
//@ ensures(*The length of the output array `ans` is equal to the length of the input array `names`.*);
//@ ensures(*Each element in the output array `ans` is not null.*);
//@ ensures(*Each element in the output array `ans` is a string of lowercase English letters, digits, and/or round brackets.*);
//@ ensures(*The length of each element in the output array `ans` is greater than 0 and less than or equal to 20.*);
//@ ensures(*The names of the folders in the output array `ans` are unique.*);
//@ ensures(*If a folder name in the input array `names` is not already assigned, the corresponding folder name in the output array `ans` remains the same.*);
//@ ensures(*If a folder name in the input array `names` is already assigned, the corresponding folder name in the output array `ans` has a suffix addition in the form of `(k)`, where `k` is the smallest positive integer such that the obtained name remains unique.*);
    public String[] getFolderNames(String[] names) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            int prefix = map.getOrDefault(names[i], 0);
            if (prefix != 0) {
                String raw = names[i];
                while (map.getOrDefault(names[i], 0) != 0) {
                    names[i] = raw + "(" + prefix + ")";
                    prefix++;
                }
                map.put(raw, prefix);
            }
            map.put(names[i], 1);
        }
        return names;
    }
}