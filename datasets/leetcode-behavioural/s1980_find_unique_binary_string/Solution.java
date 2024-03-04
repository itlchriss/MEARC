package g1901_2000.s1980_find_unique_binary_string;

// #Medium #Array #String #Backtracking #2022_05_21_Time_7_ms_(31.88%)_Space_42_MB_(59.01%)

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of `nums` is equal to `n`.*);
//@ ensures(*Each string in `nums` is not null.*);
//@ ensures(*Each string in `nums` has a length of `n`.*);
//@ ensures(*Each string in `nums` consists of only `'0'` and `'1'`.*);
//@ ensures(*All strings in `nums` are unique.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string is not null.*);
//@ ensures(*The length of the output string is equal to `n`.*);
//@ ensures(*The output string does not appear in `nums`.*);
//@ ensures(*If there are multiple valid output strings, any of them can be returned.*);
    public String findDifferentBinaryString(String[] nums) {
        Set<String> set = new HashSet<>(Arrays.asList(nums));
        int len = nums[0].length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < len) {
            sb.append(1);
            i++;
        }
        int max = Integer.parseInt(sb.toString(), 2);
        for (int num = 0; num <= max; num++) {
            String binary = Integer.toBinaryString(num);
            if (binary.length() < len) {
                sb.setLength(0);
                sb.append(binary);
                while (sb.length() < len) {
                    sb.insert(0, "0");
                }
                binary = sb.toString();
            }
            if (!set.contains(binary)) {
                return binary;
            }
        }
        return null;
    }
}