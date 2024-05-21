package g0501_0600.s0551_student_attendance_record_i;

// #Easy #String #2022_08_02_Time_0_ms_(100.00%)_Space_40.2_MB_(96.36%)

public class Solution {
//@ requires(*You are given a string param_s representing an attendance record for a student where each character signifies whether the student was absent, late, or present on that day.*);
//@ requires(*The record only contains the following three characters:*);
//@ requires(*`'A'`: Absent.*);
//@ requires(*`'L'`: Late.*);
//@ requires(*`'P'`: Present.*);
//@ requires(*The student is eligible for an attendance award if they meet both of the following criteria:*);
//@ requires(*The student was absent (`'A'`) for strictly fewer than 2 days total.*);
//@ requires(*The student was never late (`'L'`) for 3 or more consecutive days.*);
//@ requires(*Return `true` if the student is eligible for an attendance award, or `false` otherwise.*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "PPALLP"*);
//@ requires(*Output: true*);
//@ requires(*Explanation: The student has fewer than 2 absences and was never late 3 or more consecutive days.*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "PPALLL"*);
//@ requires(*Output: false*);
//@ requires(*Explanation: The student was late 3 consecutive days in the last 3 days, so is not eligible for the award.*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= s.length <= 1000`*);
//@ requires(*`s[i]` is either `'A'`, `'L'`, or `'P'`.*);
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