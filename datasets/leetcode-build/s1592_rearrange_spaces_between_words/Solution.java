package g1501_1600.s1592_rearrange_spaces_between_words;

// #Easy #String #2022_04_11_Time_3_ms_(67.74%)_Space_40.3_MB_(93.33%)

public class Solution {
	//@ requires(*The input string `text` is not null.*);
	//@ requires(*The input string `text` contains at least one word.*);
	//@ ensures(*The returned string has an equal number of spaces between every pair of adjacent words.*);
	//@ ensures(*The number of spaces between every pair of adjacent words is maximized.*);
	//@ ensures(*If there are extra spaces after redistributing them equally, they are placed at the end of the string.*);
	//@ ensures(*The length of the returned string is the same as the length of the input string `text`.*);
    public String reorderSpaces(String text) {
        int spaceCount = 0;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                spaceCount++;
            }
        }
        String[] words = text.trim().split("\\s+");
        if (words.length == 1) {
            StringBuilder sb = new StringBuilder(words[0]);
            for (int i = 0; i < spaceCount; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
        int trailingSpaces = spaceCount % (words.length - 1);
        int newSpaces = spaceCount / (words.length - 1);
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < words.length; j++) {
            sb.append(words[j]);
            if (j < words.length - 1) {
                for (int i = 0; i < newSpaces; i++) {
                    sb.append(" ");
                }
            } else {
                for (int i = 0; i < trailingSpaces; i++) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
}