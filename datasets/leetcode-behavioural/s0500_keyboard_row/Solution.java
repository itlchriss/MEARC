package g0401_0500.s0500_keyboard_row;

// #Easy #Array #String #Hash_Table #2022_07_24_Time_1_ms_(82.00%)_Space_42_MB_(48.14%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private boolean check(String str, String word) {
        for (char ch : word.toCharArray()) {
            if (str.indexOf(ch) < 0) {
                return false;
            }
        }
        return true;
    }
//@ ensures(*The string array parameter `words` must not be null.*);
//@ ensures(*The string array result contains only the words that can be typed using letters of the alphabet on only one row of the American keyboard.*);
//@ ensures(*The words in the string array result are in the same order as they appear in the string array parameter `words`.*);
//@ ensures(*The words in the string array result are in lowercase.*);

    public String[] findWords(String[] words) {
        List<String> arr = new ArrayList<>();
        for (String word : words) {
            String w = word.toLowerCase();
            if (check("qwertyuiop", w) || check("asdfghjkl", w) || check("zxcvbnm", w)) {
                arr.add(word);
            }
        }
        String[] ans = new String[arr.size()];
        ans = arr.toArray(ans);
        return ans;
    }
}