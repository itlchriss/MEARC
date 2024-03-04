package g2601_2700.s2672_number_of_adjacent_elements_with_the_same_color;

// #Medium #Array #2023_09_10_Time_4_ms_(100.00%)_Space_96_MB_(23.28%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` has a length of `n`.*);
//@ ensures(*All elements in the array `nums` are initially uncolored and have a value of 0.*);
//@ ensures(*The input 2D array `queries` has a length of `m`.*);
//@ ensures(*Each query in `queries` is represented by a 2-element array `[index, color]`.*);
//@ ensures(*The index `index` in each query is a valid index in the array `nums` (0 <= index < n).*);
//@ ensures(*The color `color` in each query is a valid color (1 <= color <= 10^5).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `answer` has a length of `m`.*);
//@ ensures(*Each element in the array `answer` represents the number of adjacent elements with the same color after the corresponding query.*);
//@ ensures(*The elements in the array `nums` are updated according to the queries in `queries`.*);
//@ ensures(*After each query, the element at index `index` in `nums` is colored with the corresponding color.*);
//@ ensures(*After each query, the count of adjacent elements with the same color is calculated and stored in the corresponding element of `answer`.*);
//@ ensures(*The elements in `nums` that are not affected by the queries remain unchanged.*);
//@ ensures(*The elements in `answer` that are not affected by the queries remain unchanged.*);
    public int[] colorTheArray(int n, int[][] q) {
        if (n == 1) {
            return new int[q.length];
        }
        int[] ans = new int[q.length];
        int[] color = new int[n];
        int cnt = 0;
        for (int i = 0; i < q.length; i++) {
            int ind = q[i][0];
            int assColor = q[i][1];
            int leftColor = 0;
            int rytColor = 0;
            if (ind - 1 >= 0) {
                leftColor = color[ind - 1];
            }
            if (ind + 1 < n) {
                rytColor = color[ind + 1];
            }
            if (color[ind] != 0 && leftColor == color[ind]) {
                cnt--;
            }
            if (color[ind] != 0 && rytColor == color[ind]) {
                cnt--;
            }
            color[ind] = assColor;
            if (color[ind] != 0 && leftColor == color[ind]) {
                cnt++;
            }
            if (color[ind] != 0 && rytColor == color[ind]) {
                cnt++;
            }
            ans[i] = cnt;
        }
        return ans;
    }
}