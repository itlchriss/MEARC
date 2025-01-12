package g0501_0600.s0551_student_attendance_record_i;

// #Easy #String #2022_08_02_Time_0_ms_(100.00%)_Space_40.2_MB_(96.36%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists only of the characters 'A', 'L', and 'P'.*);
//@ ensures(*If the boolean result is equal to the true literal, the string parameter `s` contains fewer than 2 occurrences of the character 'A'.*);
//@ ensures(*If the boolean result is equal to the true literal, the string parameter `s` does not contain the substring "LLL".*);
//@ ensures(*If the boolean result is equal to the false literal, the string parameter `s` contains 2 or more occurrences of the character 'A' or contains the substring "LLL".*);
//@ ensures(*If the string parameter `s` is equal to "PPALLP", the boolean result is equal to the true literal.*);
//@ ensures(*If the string parameter `s` is equal to "PPALLL", the boolean result is equal to the false literal.*);
    public boolean checkRecord(String s) {
        int aCount = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == 'A') {
                aCount++;
                if (aCount > 1) {
                    return false;
                }
            } else if (s.charAt(i) == 'L') {
                int continuousLCount = 0;
                while (i < s.length() && s.charAt(i) == 'L') {
                    i++;
                    continuousLCount++;
                    if (continuousLCount > 2) {
                        return false;
                    }
                }
                i--;
            }
            i++;
        }
        return true;
    }
}