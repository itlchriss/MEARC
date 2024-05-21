package g0001_0100.s0028_find_the_index_of_the_first_occurrence_in_a_string;

// #Easy #Top_Interview_Questions #String #Two_Pointers #String_Matching
// #Programming_Skills_II_Day_1 #2023_08_09_Time_0_ms_(100.00%)_Space_40.5_MB_(71.14%)

public class Solution {
//@ requires(*Implement [strStr()](http://www.cplusplus.com/reference/cstring/strstr/).*);
//@ requires(*Return the index of the first occurrence of needle in haystack, or `-1` if param_needle is not part of param_haystack.*);
//@ requires(*Clarification:*);
//@ requires(*This is a great question to ask during an interview.*);
//@ requires(*This is consistent to C's [strstr()](http://www.cplusplus.com/reference/cstring/strstr/) and Java's [indexOf()](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#indexOf(java.lang.*);
//@ requires(*String)).*);
//@ requires(*Example 1:*);
//@ requires(*Input: haystack = "hello", needle = "ll"*);
//@ requires(*Output: 2*);
//@ requires(*Example 2:*);
//@ requires(*Input: haystack = "aaaaa", needle = "bba"*);
//@ requires(*Output: -1*);
//@ requires(*Example 3:*);
//@ requires(*Input: haystack = "", needle = ""*);
//@ requires(*Output: 0*);
//@ requires(*Constraints:*);
//@ requires(*<code>0 <= haystack.length, needle.length <= 5  10<sup>4</sup></code>*);
//@ requires(*param_haystack and param_needle consist of only lower-case English characters.*);
//@ ensures(*What should we the result is when param_needle is an empty string?*);
//@ ensures(*For the purpose of this problem, we will the result is 0 when param_needle is an empty string.*);
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