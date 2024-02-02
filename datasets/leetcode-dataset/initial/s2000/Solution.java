package g1901_2000.s2000_reverse_prefix_of_word;

// #Easy #String #Two_Pointers #2022_05_09_Time_0_ms_(100.00%)_Space_40.7_MB_(79.75%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the resulting string_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public String reversePrefix(String word, char ch) {
        int i = 0;
        int j = word.indexOf(ch);
        char[] charArr = word.toCharArray();
        while (i < j) {
            char temp = charArr[i];
            charArr[i] = charArr[j];
            charArr[j] = temp;
            i++;
            j--;
        }
        return String.valueOf(charArr);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
