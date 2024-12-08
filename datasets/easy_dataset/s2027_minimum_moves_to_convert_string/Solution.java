package g2001_2100.s2027_minimum_moves_to_convert_string;

// #Easy #String #Greedy #2022_05_25_Time_0_ms_(100.00%)_Space_40_MB_(97.32%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` must have a length greater than or equal to 3.*);
//@ ensures(*The characters in the input string `s` must be either 'X' or 'O'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output integer represents the minimum number of moves required to convert all characters in `s` to 'O'.*);
//@ ensures(*The output integer is non-negative.*);
//@ ensures(**);
//@ ensures(*Additional requirements:*);
//@ ensures(*The method should handle the case when there are no 'X's in `s` and return 0 as the output.*);
//@ ensures(*The method should handle the case when all characters in `s` are already 'O' and return 0 as the output.*);
//@ ensures(*The method should handle the case when there are multiple possible ways to convert `s` to all 'O's and return the minimum number of moves.*);
    public int minimumMoves(String s) {
        int r = 0;
        int i = 0;
        char[] sArray = s.toCharArray();
        while (i < sArray.length) {
            if (sArray[i] == 'X') {
                r++;
                i += 2;
            }
            i++;
        }
        return r;
    }
}