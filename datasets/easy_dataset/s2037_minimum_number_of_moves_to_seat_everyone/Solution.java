package g2001_2100.s2037_minimum_number_of_moves_to_seat_everyone;

// #Easy #Array #Sorting #2022_08_22_Time_2_ms_(99.31%)_Space_41.7_MB_(98.89%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of the `seats` array is equal to the length of the `students` array.*);
//@ ensures(*The length of the `seats` array is greater than or equal to 1.*);
//@ ensures(*The length of the `seats` array is less than or equal to 100.*);
//@ ensures(*The length of the `students` array is greater than or equal to 1.*);
//@ ensures(*The length of the `students` array is less than or equal to 100.*);
//@ ensures(*Each element in the `seats` array is greater than or equal to 1.*);
//@ ensures(*Each element in the `seats` array is less than or equal to 100.*);
//@ ensures(*Each element in the `students` array is greater than or equal to 1.*);
//@ ensures(*Each element in the `students` array is less than or equal to 100.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of moves required to move each student to a seat such that no two students are in the same seat.*);
    public int minMovesToSeat(int[] seats, int[] students) {
        int ans = 0;
        Arrays.sort(seats);
        Arrays.sort(students);
        for (int i = 0; i < seats.length; i++) {
            ans += Math.abs(seats[i] - students[i]);
        }
        return ans;
    }
}