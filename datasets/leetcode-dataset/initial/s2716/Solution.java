package g2701_2800.s2716_minimize_string_length;

// #Easy #String #Hash_Table #2023_09_15_Time_3_ms_(100.00%)_Space_43.3_MB_(98.28%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _an integer denoting the length of the **minimized** string._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int minimizedStringLength(String s) {
        int[] arr = new int[26];
        int count = 0;
        for (char c : s.toCharArray()) {
            arr[c - 'a']++;
        }
        for (int n : arr) {
            if (n != 0) {
                count++;
            }
        }
        return count;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
