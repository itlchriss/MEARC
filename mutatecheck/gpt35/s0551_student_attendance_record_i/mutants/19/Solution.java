package g0501_0600.s0551_student_attendance_record_i;

// #Easy #String #2022_08_02_Time_0_ms_(100.00%)_Space_40.2_MB_(96.36%)

public class Solution {
// ensures \result == true || \result == false
// ensures \result >= 0
// ensures \result == count + countAbsencesHelper(s, index + 1, count) if index < s.length()
// ensures \result == count if index == s.length() - private boolean hasConsecutiveLate(String s)
// requires public boolean checkRecord(String s)
// ensures \result == true ==> (\exists int i; 0 <= i && i < s.length() - 2; s.charAt(i) == 'L' && s.charAt(i + 1) == 'L' && s.charAt(i + 2) == 'L')
// requires 0 <= index && index <= s.length()
// ensures \result == countAbsencesHelper(s, 0, 0) - private int countAbsencesHelper(String s, int index, int count)
// requires s != null && s.length() >= 1 && s.length() <= 1000
// requires (\forall int i; 0 <= i && i < s.length(); s.charAt(i) == 'A' || s.charAt(i) == 'L' || s.charAt(i) == 'P')
// ensures \result == false ==> (countAbsences(s) >= 2 || hasConsecutiveLate(s)) - private int countAbsences(String s)
// ensures \result == true ==> (countAbsences(s) < 2 && !hasConsecutiveLate(s))
// ensures \result == false ==> (\forall int i; 0 <= i && i < s.length() - 2; s.charAt(i) != 'L' || s.charAt(i + 1) != 'L' || s.charAt(i + 2) != 'L')
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
                while (false) {
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
