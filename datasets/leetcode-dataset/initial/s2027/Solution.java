package g2001_2100.s2027_minimum_moves_to_convert_string;

// #Easy #String #Greedy #2022_05_25_Time_0_ms_(100.00%)_Space_40_MB_(97.32%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the **minimum** number of moves required so that all the characters of_ `s` _are converted to_ `'O'`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
