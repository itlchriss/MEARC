package g1801_1900.s1861_rotating_the_box;

// #Medium #Array #Matrix #Two_Pointers #2022_05_09_Time_8_ms_(92.84%)_Space_145.6_MB_(5.03%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `box` is a 2D character array.*);
//@ ensures(*The dimensions of `box` are `m x n`, where `m` is the number of rows and `n` is the number of columns.*);
//@ ensures(*Each cell of `box` contains one of the following characters: `'#'`, `'*'`, or `'.'`.*);
//@ ensures(*Each stone in `box` rests on an obstacle, another stone, or the bottom of the box.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a new `n x m` matrix representing the box after the rotation described above.*);
//@ ensures(*The dimensions of the returned matrix are `n x m`, where `n` is the number of columns and `m` is the number of rows.*);
//@ ensures(*The cells of the returned matrix contain the same characters as the corresponding cells in the input `box`, but rotated 90 degrees clockwise.*);
//@ ensures(*The stones in the returned matrix fall down until they land on an obstacle, another stone, or the bottom of the box.*);
//@ ensures(*The obstacles' positions remain unchanged.*);
//@ ensures(*The stones' horizontal positions remain unchanged.*);
    public char[][] rotateTheBox(char[][] box) {
        int n = box.length;
        int m = box[0].length;
        char[][] result = new char[m][n];
        for (int i = 0; i < n; i++) {
            int j = m - 1;
            int idx = m - 1;
            while (j >= 0) {
                if (box[i][j] == '#') {
                    result[j--][n - i - 1] = '.';
                    result[idx--][n - i - 1] = '#';
                } else {
                    char c = box[i][j];
                    result[j--][n - i - 1] = c;
                    if (c == '*') {
                        idx = j;
                    }
                }
            }
        }
        return result;
    }
}