package g0501_0600.s0551_student_attendance_record_i;

// #Easy #String #2022_08_02_Time_0_ms_(100.00%)_Space_40.2_MB_(96.36%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` only contains the characters 'A', 'L', or 'P'.*);
//@ ensures(*If the boolean result is true, the total number of 'A' characters in the string parameter `s` is less than 2 and there are no sequences of 3 or more consecutive 'L' characters.*);
//@ ensures(*If the boolean result is false, the total number of 'A' characters in the string parameter `s` is greater than or equal to 2 or there is at least one sequence of 3 or more consecutive 'L' characters.*);
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