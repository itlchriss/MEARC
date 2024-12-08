package g1401_1500.s1450_number_of_students_doing_homework_at_a_given_time;

// #Easy #Array #2022_03_28_Time_0_ms_(100.00%)_Space_40.3_MB_(86.45%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of `startTime` and `endTime` arrays must be the same.*);
//@ ensures(*The length of `startTime` and `endTime` arrays must be at least 1.*);
//@ ensures(*Each element in the `startTime` array must be between 1 and 1000 (inclusive).*);
//@ ensures(*Each element in the `endTime` array must be between 1 and 1000 (inclusive).*);
//@ ensures(*Each element in the `startTime` array must be less than or equal to the corresponding element in the `endTime` array.*);
//@ ensures(*The `queryTime` must be between 1 and 1000 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of students doing their homework at the `queryTime`.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
//@ ensures(*The returned value is less than or equal to the length of the `startTime` and `endTime` arrays.*);
//@ ensures(*The returned value is the number of students where `queryTime` is within the interval `[startTime[i], endTime[i]]` inclusive.*);
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int count = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) {
                count++;
            }
        }
        return count;
    }
}