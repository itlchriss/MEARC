package g0001_0100.s0038_count_and_say;

// #Medium #Top_Interview_Questions #String #2023_08_09_Time_2_ms_(97.68%)_Space_39.9_MB_(90.19%)

public class Solution {
//@ ensures(\result != null);
//@ ensures((\forall int i; 0 <= i && i < \result.length() - 1; Character.isDigit(\result.charAt(i))));
//@ ensures((\forall int i; 0 <= i && i < \result.length() - 1; \result.charAt(i + 1) == '1' || \result.charAt(i + 1) == '2' || \result.charAt(i + 1) == '3' || \result.charAt(i + 1) == '4' || \result.charAt(i + 1) == '5' || \result.charAt(i + 1) == '6' || \result.charAt(i + 1) == '7' || \result.charAt(i + 1) == '8' || \result.charAt(i + 1) == '9'));
//@ ensures((\forall int i; 0 <= i && i < \result.length() - 1; \result.charAt(i) == '1' || \result.charAt(i) == '2' || \result.charAt(i) == '3' || \result.charAt(i) == '4' || \result.charAt(i) == '5' || \result.charAt(i) == '6' || \result.charAt(i) == '7' || \result.charAt(i) == '8' || \result.charAt(i) == '9'));
//@ ensures(\result.length() > 0);
//@ ensures((\forall int i; 0 <= i && i < \result.length() - 1; Character.isDigit(\result.charAt(i + 1))));
//@ requires(n >= 1 && n <= 30);
//@ ensures();
//@ ensures((\forall int i; 0 <= i && i < \result.length() - 1; \result.charAt(i) != \result.charAt(i + 1)));
    public String countAndSay(int n) {
        if (n <= 1) {
            return "1";
        }
        StringBuilder res = new StringBuilder();
        String prev = countAndSay(n - 1);
        int count = 1;
        for (int i = 1; i < prev.length(); i++) {
            if (prev.charAt(i) == prev.charAt(i - 1)) {
                count++;
            } else {
                res.append(count).append(prev.charAt(i - 1));
                count = 1;
            }
        }
        res.append(count).append(prev.charAt(prev.length() - 1));
        return res.toString();
    }
}
