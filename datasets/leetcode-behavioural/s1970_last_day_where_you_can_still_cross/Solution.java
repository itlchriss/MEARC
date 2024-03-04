package g1901_2000.s1970_last_day_where_you_can_still_cross;

// #Hard #Array #Depth_First_Search #Breadth_First_Search #Binary_Search #Matrix #Union_Find
// #2022_05_21_Time_14_ms_(95.24%)_Space_102.7_MB_(69.39%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input matrix `cells` must be a 2D array.*);
//@ ensures(*2. The dimensions of the input matrix `cells` must be equal to `row` x `col`.*);
//@ ensures(*3. The values in the input matrix `cells` must be unique.*);
//@ ensures(*4. The values in the input matrix `cells` must be within the range of 1 to `row` for the row indices and 1 to `col` for the column indices.*);
//@ ensures(*5. The input integers `row` and `col` must be greater than or equal to 2 and less than or equal to 20000.*);
//@ ensures(*6. The product of `row` and `col` must be greater than or equal to 4 and less than or equal to 20000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method should return an integer representing the last day where it is possible to walk from the top to the bottom by only walking on land cells.*);
//@ ensures(*2. The returned value should be greater than or equal to 0 and less than or equal to the number of cells in the matrix.*);
//@ ensures(*3. The returned value should be the maximum day number where it is possible to cross from top to bottom by only walking on land cells.*);
//@ ensures(*4. The method should not modify the input matrix `cells` or any other input parameters.*);
    public int latestDayToCross(int row, int col, int[][] cells) {
        Ends[][] ends = new Ends[row][col];
        for (int i = 0; i < cells.length; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            Ends curr = null;
            if (c > 0 && ends[r][c - 1] != null) {
                curr = calEnds(ends[r][c - 1], curr, c);
            }
            if (r > 0 && ends[r - 1][c] != null) {
                curr = calEnds(ends[r - 1][c], curr, c);
            }
            if (c < col - 1 && ends[r][c + 1] != null) {
                curr = calEnds(ends[r][c + 1], curr, c);
            }
            if (r < row - 1 && ends[r + 1][c] != null) {
                curr = calEnds(ends[r + 1][c], curr, c);
            }
            if (c > 0 && r > 0 && ends[r - 1][c - 1] != null) {
                curr = calEnds(ends[r - 1][c - 1], curr, c);
            }
            if (c > 0 && r < row - 1 && ends[r + 1][c - 1] != null) {
                curr = calEnds(ends[r + 1][c - 1], curr, c);
            }
            if (c < col - 1 && r > 0 && ends[r - 1][c + 1] != null) {
                curr = calEnds(ends[r - 1][c + 1], curr, c);
            }
            if (c < col - 1 && r < row - 1 && ends[r + 1][c + 1] != null) {
                curr = calEnds(ends[r + 1][c + 1], curr, c);
            }
            if (curr == null) {
                curr = new Ends(i, c, c);
            }
            if (curr.l == 0 && curr.r == col - 1) {
                return i;
            }
            ends[r][c] = curr;
        }
        return cells.length;
    }

    private Ends calEnds(Ends p, Ends curr, int c) {
        while (p.parent != null) {
            p = p.parent;
        }
        p.l = curr == null ? Math.min(p.l, c) : Math.min(p.l, curr.l);
        p.r = curr == null ? Math.max(p.r, c) : Math.max(p.r, curr.r);
        if (curr == null) {
            curr = p;
        } else if (curr.i != p.i) {
            curr.parent = p;
            curr = curr.parent;
        }
        return curr;
    }

    static class Ends {
        int i;
        int l;
        int r;
        Ends parent;

        public Ends(int i, int l, int r) {
            this.i = i;
            this.l = l;
            this.r = r;
        }
    }
}