package g2101_2200.s2109_adding_spaces_to_a_string;

// #Medium #Array #String #Simulation #2022_05_31_Time_24_ms_(89.33%)_Space_94.5_MB_(48.03%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The input array `spaces` is not null.*);
//@ ensures(*The length of `s` is greater than or equal to 1.*);
//@ ensures(*The length of `spaces` is greater than or equal to 1.*);
//@ ensures(*The values in `spaces` are strictly increasing.*);
//@ ensures(*The values in `spaces` are within the range of indices in `s`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The modified string with spaces inserted before the characters at the indices specified in `spaces` is returned.*);
//@ ensures(*The length of the modified string is equal to the length of `s` plus the number of spaces inserted.*);
    public String addSpaces(String string, int[] spaces) {
        char[] stringChars = new char[string.length() + spaces.length];
        for (int i = 0; i < spaces.length; i++) {
            stringChars[spaces[i] + i] = ' ';
        }

        int equivalentIndex = -1;
        int i = 0;
        while (i < string.length()) {
            equivalentIndex++;
            if (stringChars[equivalentIndex] == ' ') {
                i--;
            } else {
                stringChars[equivalentIndex] = string.charAt(i);
            }
            i++;
        }
        return new String(stringChars);
    }
}