package g2301_2400.s2381_shifting_letters_ii;

// #Medium #Array #String #Prefix_Sum #2022_08_25_Time_10_ms_(75.00%)_Space_82.7_MB_(75.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The input string `s` is not empty.*);
//@ ensures(*The input array `shifts` is not null.*);
//@ ensures(*The length of the input array `shifts` is not zero.*);
//@ ensures(*The length of the input string `s` is greater than or equal to the maximum value of `end_i` in the `shifts` array.*);
//@ ensures(*The values of `start_i` and `end_i` in each element of the `shifts` array are valid indices within the input string `s`.*);
//@ ensures(*The values of `direction_i` in each element of the `shifts` array are either 0 or 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string is not null.*);
//@ ensures(*The length of the output string is equal to the length of the input string `s`.*);
//@ ensures(*The characters in the output string are shifted according to the specified shifts in the `shifts` array.*);
//@ ensures(*Shifting a character forward means replacing it with the next letter in the alphabet, wrapping around from 'z' to 'a'.*);
//@ ensures(*Shifting a character backward means replacing it with the previous letter in the alphabet, wrapping around from 'a' to 'z'.*);
//@ ensures(*The output string is the final result after applying all the shifts specified in the `shifts` array.*);
    public String shiftingLetters(String s, int[][] shifts) {
        int[] diff = new int[s.length() + 1];
        int l;
        int r;
        for (int[] shift : shifts) {
            l = shift[0];
            r = shift[1] + 1;
            diff[l] += 26;
            diff[r] += 26;
            if (shift[2] == 0) {
                diff[l]--;
                diff[r]++;
            } else {
                diff[l]++;
                diff[r]--;
            }
            diff[l] %= 26;
            diff[r] %= 26;
        }
        StringBuilder sb = new StringBuilder();
        int current = 0;
        int val;
        for (int i = 0; i < s.length(); ++i) {
            current += diff[i];
            val = s.charAt(i) - 'a';
            val += current;
            val %= 26;
            sb.append((char) ('a' + val));
        }
        return sb.toString();
    }
}