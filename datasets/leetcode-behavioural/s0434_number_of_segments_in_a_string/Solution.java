package g0401_0500.s0434_number_of_segments_in_a_string;

// #Easy #String #2022_07_16_Time_0_ms_(100.00%)_Space_42.5_MB_(5.49%)

public class Solution {
//@ ensures(*The string parameter `s` must not be null.*);
//@ ensures(*The integer result is the number of segments in the string parameter `s`.*);
//@ ensures(*A segment is defined as a contiguous sequence of non-space characters.*);
//@ ensures(*The integer result is 0 if the string parameter `s` is empty.*);
//@ ensures(*The integer result is 1 if the string parameter `s` contains only one segment.*);
//@ ensures(*The integer result is 5 if the string parameter `s` is "Hello, my name is John".*);
//@ ensures(*The integer result is 1 if the string parameter `s` is "Hello".*);
    public int countSegments(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        String[] splitted = s.split(" ");
        int result = 0;
        for (String value : splitted) {
            if (value.length() > 0) {
                result++;
            }
        }
        return result;
    }
}