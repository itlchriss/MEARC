package g0001_0100.s0028_find_the_index_of_the_first_occurrence_in_a_string;

// #Easy #Top_Interview_Questions #String #Two_Pointers #String_Matching
// #Programming_Skills_II_Day_1 #2023_08_09_Time_0_ms_(100.00%)_Space_40.5_MB_(71.14%)

public class Solution {
//@ requires(*The length of the string parameter `haystack` is less than or equal to 50000 and is greater than or equal to 0.*);
//@ requires(*The length of the string parameter `needle` is less than or equal to 50000 and is greater than or equal to 0.*);
//@ ensures(*The integer result is less than or equal to the length of the string parameter `haystack` and is greater than or equal to -1.*);
//@ ensures(*If the string parameter `needle` is an empty string, the integer result is equal to 0.*);
//@ ensures(*If the string parameter `needle` is not part of the string parameter `haystack`, the integer result is equal to -1.*);
//@ ensures(*If the string parameter `needle` is part of the string parameter `haystack`, the integer result is equal to the index of the first occurrence of `needle` in `haystack`.*);
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int m = haystack.length();
        int n = needle.length();
        //@ decreases (m - n + 1) - start;
        for (int start = 0; start < m - n + 1; start++) {
            if (haystack.substring(start, start + n).equals(needle)) {
                return start;
            }
        }
        return -1;
    }
}