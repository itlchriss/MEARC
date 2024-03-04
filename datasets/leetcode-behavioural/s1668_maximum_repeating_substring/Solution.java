package g1601_1700.s1668_maximum_repeating_substring;

// #Easy #String #String_Matching #2022_04_22_Time_1_ms_(93.15%)_Space_40.5_MB_(86.13%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input sequence and word are not null.*);
//@ ensures(*The input sequence and word are not empty.*);
//@ ensures(*The length of the input sequence is between 1 and 100 (inclusive).*);
//@ ensures(*The length of the input word is between 1 and 100 (inclusive).*);
//@ ensures(*The input sequence and word contain only lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the maximum k-repeating value of the word in the sequence.*);
//@ ensures(*If the word is not a substring of the sequence, the method returns 0.*);
//@ ensures(*If the word is a substring of the sequence, the method returns the highest value k where the word is k-repeating in the sequence.*);
    public int maxRepeating(String sequence, String word) {
        int k = 0;
        StringBuilder repeat = new StringBuilder(word);
        while (sequence.contains(repeat)) {
            k++;
            repeat.append(word);
        }
        return k;
    }
}