package g2001_2100.s2075_decode_the_slanted_ciphertext;

// #Medium #String #Simulation #2022_05_29_Time_48_ms_(68.23%)_Space_77.9_MB_(64.71%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `encodedText` is a valid encoding of some `originalText` that does not have trailing spaces.*);
//@ ensures(*The input `rows` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a string that is the original text encoded in `encodedText`.*);
//@ ensures(*The returned string does not have trailing spaces.*);
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) {
            return encodedText;
        }
        int total = encodedText.length();
        int cols = total / rows;
        char[][] grid = new char[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = encodedText.charAt(index++);
            }
        }
        StringBuilder sb = new StringBuilder();
        int colIndex = 0;
        while (colIndex < cols) {
            for (int j = colIndex, i = 0; j < cols && i < rows; j++, i++) {
                sb.append(grid[i][j]);
            }
            colIndex++;
        }
        int i = sb.length() - 1;
        while (i >= 0 && sb.charAt(i) == ' ') {
            i--;
        }
        return sb.substring(0, i + 1);
    }
}