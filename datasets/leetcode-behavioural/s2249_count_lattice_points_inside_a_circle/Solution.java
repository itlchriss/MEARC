package g2201_2300.s2249_count_lattice_points_inside_a_circle;

// #Medium #Array #Hash_Table #Math #Enumeration #Geometry
// #2022_06_13_Time_54_ms_(92.07%)_Space_41.4_MB_(91.10%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input circles array is not null.*);
//@ ensures(*The length of the circles array is at least 1 and at most 200.*);
//@ ensures(*Each circle in the circles array is represented by an array of length 3.*);
//@ ensures(*The x-coordinate and y-coordinate of each circle are integers between 1 and 100 (inclusive).*);
//@ ensures(*The radius of each circle is an integer between 1 and the minimum of its x-coordinate and y-coordinate (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of lattice points that are present inside at least one circle.*);
//@ ensures(*A lattice point is a point with integer coordinates.*);
//@ ensures(*Points that lie on the circumference of a circle are also considered to be inside it.*);
    public int countLatticePoints(int[][] circles) {
        int xMin = 200;
        int xMax = -1;
        int yMin = 200;
        int yMax = -1;
        for (int[] c : circles) {
            xMin = Math.min(xMin, c[0] - c[2]);
            xMax = Math.max(xMax, c[0] + c[2]);
            yMin = Math.min(yMin, c[1] - c[2]);
            yMax = Math.max(yMax, c[1] + c[2]);
        }
        int ans = 0;
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (int[] c : circles) {
                    if ((c[0] - x) * (c[0] - x) + (c[1] - y) * (c[1] - y) <= c[2] * c[2]) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }
}