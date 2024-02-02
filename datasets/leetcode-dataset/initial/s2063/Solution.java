package g2001_2100.s2063_vowels_of_all_substrings;

// #Medium #String #Dynamic_Programming #Math #Combinatorics
// #2022_05_29_Time_21_ms_(34.49%)_Space_48.9_MB_(68.06%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given a string `word`, return _the **sum of the number of vowels** (_`'a'`, `'e'`_,_ `'i'`_,_ `'o'`_, and_ `'u'`_)_ _in every substring of_ `word`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public long countVowels(String word) {
        long ans = 0;
        for (int i = 0; i < word.length(); i++) {
            if (isVowel(word.charAt(i))) {
                long right = word.length() - (long) i - 1;
                ans += ((long) i + 1) * (right + 1);
            }
        }
        return ans;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
