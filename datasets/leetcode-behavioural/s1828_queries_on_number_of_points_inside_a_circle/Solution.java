package g1801_1900.s1828_queries_on_number_of_points_inside_a_circle;

// #Medium #Array #Math #Geometry #2022_05_07_Time_23_ms_(75.03%)_Space_50.5_MB_(46.96%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The `points` array is not null.*);
//@ ensures(*The `queries` array is not null.*);
//@ ensures(*The length of the `points` array is greater than or equal to 1.*);
//@ ensures(*The length of the `queries` array is greater than or equal to 1.*);
//@ ensures(*The length of each `points[i]` array is equal to 2.*);
//@ ensures(*The length of each `queries[j]` array is equal to 3.*);
//@ ensures(*The coordinates `x_i` and `y_i` of each point in the `points` array are integers between 0 and 500.*);
//@ ensures(*The coordinates `x_j` and `y_j` of each circle center in the `queries` array are integers between 0 and 500.*);
//@ ensures(*The radius `r_j` of each circle in the `queries` array is an integer between 1 and 500.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned `answer` array is not null.*);
//@ ensures(*The length of the returned `answer` array is equal to the length of the `queries` array.*);
//@ ensures(*Each element `answer[j]` in the returned `answer` array is an integer representing the number of points inside the `jth` circle.*);
//@ ensures(*The points on the border of each circle are considered inside.*);
//@ ensures(*The order of the elements in the returned `answer` array corresponds to the order of the circles in the `queries` array.*);
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] result = new int[queries.length];
        int i = 0;
        for (int[] query : queries) {
            int pts = 0;
            for (int[] point : points) {
                if ((point[0] - query[0]) * (point[0] - query[0])
                                + (point[1] - query[1]) * (point[1] - query[1])
                        <= query[2] * query[2]) {
                    pts++;
                }
            }
            result[i++] = pts;
        }
        return result;
    }
}