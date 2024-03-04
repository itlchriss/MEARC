package g0301_0400.s0318_maximum_product_of_word_lengths;

// #Medium #Array #String #Bit_Manipulation #2022_07_08_Time_12_ms_(74.92%)_Space_50.5_MB_(25.88%)

public class Solution {
//@ ensures(*The string array parameter `words` must not be null.*);
//@ ensures(*The integer result is the maximum value of the product of the lengths of two strings in the string array parameter `words` where the two strings do not share any common letters.*);
//@ ensures(*If no such two strings exist in the string array parameter `words`, the integer result is 0.*);
    public int maxProduct(String[] words) {
        int n = words.length;
        int res = 0;
        int[] masks = new int[n];
        for (int i = 0; i < n; i++) {
            masks[i] = getMask(words[i]);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }

    private int getMask(String s) {
        int mask = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            mask = mask | (1 << (c - 'a'));
        }
        return mask;
    }
}