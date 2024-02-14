package g1101_1200.s1105_filling_bookcase_shelves;

// #Medium #Array #Dynamic_Programming #2023_09_03_Time_0_ms_(100.00%)_Space_40.7_MB_(85.99%)

public class Solution {
	//@ requires(*The input array `books` is not null.*);
	//@ requires(*The input array `books` has at least one element.*);
	//@ requires(*The input integer `shelfWidth` is greater than or equal to 1.*);
	//@ requires(*The thickness and height of each book in the input array `books` are greater than or equal to 1.*);
	//@ requires(*The thickness of each book in the input array `books` is less than or equal to `shelfWidth`.*);
	//@ requires(*The height of each book in the input array `books` is less than or equal to 1000.*);
	//@ ensures(*The output is an integer representing the minimum possible height of the total bookshelf.*);
	//@ ensures(*The sum of the heights of the shelves is equal to the output.*);
	//@ ensures(*The order of the books on each shelf is the same as the given sequence of books.*);
	//@ ensures(*The sum of the thicknesses of the books on each shelf is less than or equal to `shelfWidth`.*);
	//@ ensures(*The total height of the bookcase has increased by the maximum height of the books placed on each shelf.*);
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int[] heights = new int[books.length];
        heights[0] = books[0][1];
        for (int i = 1; i < books.length; i++) {
            int width = books[i][0];
            heights[i] = books[i][1] + heights[i - 1];
            int height = books[i][1];
            int l = i - 1;
            while (l >= 0 && width + books[l][0] <= shelfWidth) {
                width += books[l][0];
                height = Math.max(height, books[l][1]);
                heights[i] = Math.min(heights[i], (l > 0 ? heights[l - 1] : 0) + height);
                l--;
            }
        }
        return heights[books.length - 1];
    }
}