package g1201_1300.s1232_check_if_it_is_a_straight_line;

// #Easy #Array #Math #Geometry #Programming_Skills_I_Day_5_Function
// #2022_03_12_Time_0_ms_(100.00%)_Space_44.2_MB_(26.46%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input array `coordinates` is not null.*);
//@ ensures(*2. The length of the input array `coordinates` is at least 2.*);
//@ ensures(*3. Each element in the input array `coordinates` is an array of length 2.*);
//@ ensures(*4. The x and y coordinates of each point in the input array `coordinates` are within the range of -10^4 to 10^4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method returns a boolean value indicating whether the points in the input array `coordinates` make a straight line in the XY plane.*);
//@ ensures(*2. If the points make a straight line, the method returns true.*);
//@ ensures(*3. If the points do not make a straight line, the method returns false.*);
    public boolean checkStraightLine(int[][] coordinates) {
        int deltaX1 = coordinates[0][0] - coordinates[1][0];
        int deltaY1 = coordinates[0][1] - coordinates[1][1];
        int[] prev = coordinates[1];
        for (int i = 2; i < coordinates.length; ++i) {
            int[] point = coordinates[i];
            int deltaX2 = point[0] - prev[0];
            int deltaY2 = point[1] - prev[1];
            if (deltaX1 * deltaY2 != deltaX2 * deltaY1) {
                return false;
            }
        }
        return true;
    }
}