package g0201_0300.s0299_bulls_and_cows;

// #Medium #String #Hash_Table #Counting #Level_1_Day_13_Hashmap
// #2022_07_06_Time_6_ms_(86.69%)_Space_42.7_MB_(72.27%)

public class Solution {
// requires (\forall int i; 0 <= i && i < secret.length(); Character.isDigit(secret.charAt(i)))
// requires secret.length() == guess.length()
// ensures (\forall int i; 0 <= i && i < secret.length(); secret.charAt(i) == guess.charAt(i) ==> \result.substring(0,1).equals("1"))
// ensures \result.matches("\\dA\\dB")
// requires secret.length() >= 1 && secret.length() <= 1000
// ensures Integer.parseInt(\result.substring(0,1)) + Integer.parseInt(\result.substring(2,3)) <= secret.length()
// ensures (\forall int i; 0 <= i && i < secret.length(); secret.charAt(i) != guess.charAt(i) ==> \result.substring(2,3).equals("1"))
// requires secret != null && guess != null
// requires guess.length() >= 1 && guess.length() <= 1000
// requires pure
// requires (\forall int i; 0 <= i && i < guess.length(); Character.isDigit(guess.charAt(i)))
// ensures \result != null
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
                if (ans[g] >= 0) {
                    cows++;
                }
                ans[s]++;
                ans[g]--;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
