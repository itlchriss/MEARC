package g0901_1000.s0917_reverse_only_letters;

// #Easy #String #Two_Pointers #2022_03_29_Time_0_ms_(100.00%)_Space_39.9_MB_(99.08%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is between 1 and 100.*);
//@ ensures(*The characters in the input string `s` have ASCII values in the range [33, 122].*);
//@ ensures(*The input string `s` does not contain the characters '"' or '\'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string is not null.*);
//@ ensures(*The length of the output string is the same as the input string.*);
//@ ensures(*The characters in the output string that are not English letters remain in the same position as in the input string.*);
//@ ensures(*The English letters in the output string are reversed compared to the input string.*);
    public String reverseOnlyLetters(String s) {
        char[] array = s.toCharArray();
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            if (Character.isLetter(array[i]) && Character.isLetter(array[j])) {
                char temp = array[i];
                array[i++] = array[j];
                array[j--] = temp;
            } else if (Character.isLetter(array[i])) {
                j--;
            } else if (Character.isLetter(array[j])) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        return new String(array);
    }
}