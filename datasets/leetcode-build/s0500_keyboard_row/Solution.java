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
	//@ requires(*The input array `words` is not null.*);
	//@ requires(*The length of the input array `words` is greater than or equal to - Each element in the input array `words` is not null.*);
	//@ requires(*Each element in the input array `words` is a string consisting of English letters (both lowercase and uppercase).*);
	//@ ensures(*The output array is not null.*);
	//@ ensures(*The length of the output array is less than or equal to the length of the input array `words`.*);
	//@ ensures(*Each element in the output array is a string that can be typed using letters of the alphabet on only one row of the American keyboard.*);
	//@ ensures(*The order of the elements in the output array is the same as the order of the elements in the input array `words`.*);

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