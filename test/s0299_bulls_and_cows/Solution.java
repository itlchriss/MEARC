package g0201_0300.s0299_bulls_and_cows;

// #Medium #String #Hash_Table #Counting #Level_1_Day_13_Hashmap
// #2022_07_06_Time_6_ms_(86.69%)_Space_42.7_MB_(72.27%)

public class Solution {
//@ requires(*The string parameter `secret` and the string parameter `guess` must consist of digits only.*);
//@ requires(*The length of the string parameter `secret` and the string parameter `guess` is greater than or equal to 1 and is less than or equal to 1000.*);
//@ requires(*The string parameter `secret` and the string parameter `guess` have the same length.*);
//@ requires(*The number of bulls represents the digits in the guess that are in the correct position.*);
//@ requires(*The number of cows represents the digits in the guess that are in the secret number but are located in the wrong position.*);
//@ requires(*Only one of the two unmatched digits is counted as a cow if the non-bull digits can only be rearranged to allow one digit to be a bull.*);
//@ ensures(*The string result is formatted as "xAyB" where x is the number of bulls and y is the number of cows.*);
    public String getHint(String secret, String guess) {
        final int[] ans = new int[10];
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            final int s = Character.getNumericValue(secret.charAt(i));
            final int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) {
                bulls++;
            } else {
                // digit s was already seen in guess, is being seen again in secret
                if (ans[s] < 0) {
                    cows++;
                }
                // digit was already seen in secret, now being seen again in guess
                if (ans[g] > 0) {
                    cows++;
                }
                ans[s]++;
                ans[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}