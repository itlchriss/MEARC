package g0701_0800.s0733_flood_fill;

// #Easy #Array #Depth_First_Search #Breadth_First_Search #Matrix
// #Algorithm_I_Day_7_Breadth_First_Search_Depth_First_Search
// #Graph_Theory_I_Day_1_Matrix_Related_Problems #Level_1_Day_9_Graph/BFS/DFS
// #2022_03_25_Time_1_ms_(85.36%)_Space_48.1_MB_(38.02%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input image grid `image` is not null.*);
//@ ensures(*2. The input image grid `image` is a valid 2D integer grid.*);
//@ ensures(*3. The input integers `sr` and `sc` are valid indices within the image grid.*);
//@ ensures(*4. The input integer `newColor` is a valid pixel value.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The modified image grid is returned as a 2D integer grid.*);
//@ ensures(*2. The modified image grid has the same dimensions as the original image grid.*);
//@ ensures(*3. The pixel at position `(sr, sc)` in the modified image grid has been changed to the new color.*);
//@ ensures(*4. All pixels connected to the starting pixel `(sr, sc)` by a path of the same color have been changed to the new color.*);
//@ ensures(*5. Pixels that are not 4-directionally connected to the starting pixel are not changed to the new color.*);
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int o = image[sr][sc];
        helper(image, sr, sc, newColor, o);
        return image;
    }

    private void helper(int[][] img, int r, int c, int n, int o) {
        if (r >= img.length
                || c >= img[0].length
                || r < 0
                || c < 0
                || img[r][c] == n
                || img[r][c] != o) {
            return;
        }
        img[r][c] = n;
        helper(img, r + 1, c, n, o);
        helper(img, r - 1, c, n, o);
        helper(img, r, c + 1, n, o);
        helper(img, r, c - 1, n, o);
    }
}