package g2801_2900.s2833_furthest_point_from_origin;

// #Easy #Array #Counting #2023_12_11_Time_1_ms_(100.00%)_Space_41.4_MB_(50.89%)

public class Solution {
	//@ requires(*The input string `moves` is not null.*);
	//@ requires(*The length of the input string `moves` is greater than or equal to 1 and less than or equal to 50.*);
	//@ requires(*The input string `moves` consists only of characters `'L'`, `'R'`, and `'_'`.*);
	//@ ensures(*The method returns an integer representing the distance from the origin of the furthest point reached after `n` moves.*);
	//@ ensures(*The furthest point can be reached by moving either to the left or to the right based on the characters in the input string `moves`.*);
	//@ ensures(*If the character in `moves` is `'L'`, the method moves to the left.*);
	//@ ensures(*If the character in `moves` is `'R'`, the method moves to the right.*);
	//@ ensures(*If the character in `moves` is `'_'`, the method can choose to move either to the left or to the right.*);
	//@ ensures(*The method calculates the distance from the origin by keeping track of the current position and updating it based on the moves made.*);
	//@ ensures(*The method returns the maximum distance reached from the origin after `n` moves.*);
    public int furthestDistanceFromOrigin(String m) {
        int count = 0;
        int res = 0;
        for (int i = 0; i < m.length(); i++) {
            if (m.charAt(i) == 'L') {
                res -= 1;
            } else if (m.charAt(i) == 'R') {
                res += 1;
            } else {
                count++;
            }
        }
        return Math.abs(res) + count;
    }
}