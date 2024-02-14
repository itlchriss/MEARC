package g0501_0600.s0551_student_attendance_record_i;

// #Easy #String #2022_08_02_Time_0_ms_(100.00%)_Space_40.2_MB_(96.36%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is between 1 and 1000 (inclusive).*);
	//@ requires(*Each character in the input string `s` is either 'A', 'L', or 'P'.*);
	//@ ensures(*The method returns a boolean value indicating whether the student is eligible for an attendance award.*);
	//@ ensures(*If the student is eligible for an attendance award, the method returns true.*);
	//@ ensures(*If the student is not eligible for an attendance award, the method returns false.*);
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