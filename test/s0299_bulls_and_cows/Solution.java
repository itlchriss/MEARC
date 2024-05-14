package g0201_0300.s0299_bulls_and_cows;

// #Medium #String #Hash_Table #Counting #Level_1_Day_13_Hashmap
// #2022_07_06_Time_6_ms_(86.69%)_Space_42.7_MB_(72.27%)

public class Solution {
//@ requires(*The length of the string parameter `secret` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `guess` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `secret` and `guess` have the same length.*);
//@ requires(*The string parameter `secret` and `guess` consist of digits only.*);
//@ ensures(*The string result is formatted as "xAyB" where x is the number of bulls and y is the number of cows.*);
//@ ensures(*If the string parameter `secret` is equal to "1807" and the string parameter `guess` is equal to "7810", the string result is equal to "1A3B".*);
//@ ensures(*If the string parameter `secret` is equal to "1123" and the string parameter `guess` is equal to "0111", the string result is equal to "1A1B".*);
//@ ensures(*If the string parameter `secret` is equal to "1" and the string parameter `guess` is equal to "0", the string result is equal to "0A0B".*);
//@ ensures(*If the string parameter `secret` is equal to "1" and the string parameter `guess` is equal to "1", the string result is equal to "1A0B".*);
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