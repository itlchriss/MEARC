package g2001_2100.s2042_check_if_numbers_are_ascending_in_a_sentence;

// #Easy #String #2022_05_26_Time_2_ms_(75.46%)_Space_42.7_MB_(29.81%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return `true` _if so, or_ `false` _otherwise_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public boolean areNumbersAscending(String s) {
        String[] words = s.split("\\ ");
        int prev = 0;
        for (String word : words) {
            if (Character.isDigit(word.charAt(0))) {
                if (Integer.parseInt(word) <= prev) {
                    return false;
                } else {
                    prev = Integer.parseInt(word);
                }
            }
        }
        return true;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
