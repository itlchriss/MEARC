package g0201_0300.s0299_bulls_and_cows;

// #Medium #String #Hash_Table #Counting #Level_1_Day_13_Hashmap
// #2022_07_06_Time_6_ms_(86.69%)_Space_42.7_MB_(72.27%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((secret.length() <= 1000) && (secret.length() >= 1));
//@ requires((guess.length() <= 1000) && (guess.length() >= 1));
//@ ensures(((secret.equals("1807")) && (guess.equals("7810"))) ==> (\result.equals("1A3B")));
//@ ensures(((secret.equals("1")) && (guess.equals("0"))) ==> (\result.equals("0A0B")));
//@ ensures(((secret.equals("1123")) && (guess.equals("0111"))) ==> (\result.equals("1A1B")));
//@ ensures(((secret.equals("1")) && (guess.equals("1"))) ==> (\result.equals("1A0B")));
    public String getHint(String secret, String guess) {
        final int[] ans = new int[10];
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secret.length(); i++) {
            final int s = Character.getNumericValue(secret.charAt(i));
            final int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) {

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
