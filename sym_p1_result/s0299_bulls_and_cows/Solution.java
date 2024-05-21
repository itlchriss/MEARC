package g0201_0300.s0299_bulls_and_cows;

// #Medium #String #Hash_Table #Counting #Level_1_Day_13_Hashmap
// #2022_07_06_Time_6_ms_(86.69%)_Space_42.7_MB_(72.27%)

public class Solution {
//@ requires(*You are playing the [Bulls and Cows](https://en.wikipedia.org/wiki/BullsandCows) game with your friend.*);
//@ requires(*You write down a secret number and ask your friend to guess what the number is.*);
//@ requires(*When your friend makes a guess, you provide a hint with the following info:*);
//@ requires(*The number of "bulls", which are digits in the guess that are in the correct position.*);
//@ requires(*The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position.*);
//@ requires(*Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.*);
//@ requires(*The hint should be formatted as `"xAyB"`, where `x` is the number of bulls and `y` is the number of cows.*);
//@ requires(*Note that both param_secret and param_guess may contain duplicate digits.*);
//@ requires(*Example 1:*);
//@ requires(*Input: secret = "1807", guess = "7810"*);
//@ requires(*Output: "1A3B"*);
//@ requires(*Explanation:*);
//@ requires(*Bulls are connected with a '|' and cows are underlined:*);
//@ requires(*"1807"*);
//@ requires(*|*);
//@ requires(*"7810"*);
//@ requires(*Example 2:*);
//@ requires(*Input: secret = "1123", guess = "0111"*);
//@ requires(*Output: "1A1B"*);
//@ requires(*Explanation:*);
//@ requires(*Bulls are connected with a '|' and cows are underlined:*);
//@ requires(*"1123"       "1123"*);
//@ requires(*|      or    |*);
//@ requires(*"0111"       "0111"*);
//@ requires(*Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.*);
//@ requires(*Example 3:*);
//@ requires(*Input: secret = "1", guess = "0"*);
//@ requires(*Output: "0A0B"*);
//@ requires(*Example 4:*);
//@ requires(*Input: secret = "1", guess = "1"*);
//@ requires(*Output: "1A0B"*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= secret.length, guess.length <= 1000`*);
//@ requires(*`secret.length == guess.length`*);
//@ requires(*param_secret and param_guess consist of digits only.*);
//@ ensures(*Given the secret number param_secret and your friend's guess param_guess, the result is the hint for your friend's guess.*);
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