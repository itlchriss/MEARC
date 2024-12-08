package g0501_0600.s0551_student_attendance_record_i;

// #Easy #String #2022_08_02_Time_0_ms_(100.00%)_Space_40.2_MB_(96.36%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((s.length() <= 1000) && (s.length() >= 1));
//@ requires(\forall int i; 0 <= i < s.length(); ('A' == (s.charAt(i))) ||  ('L' == (s.charAt(i))) ||  ('P' == (s.charAt(i))));
    public boolean checkRecord(String s) {
        int aCount = 0;
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) >= 'A') {
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
