package g2101_2200.s2129_capitalize_the_title;

// #Easy #String #2022_06_03_Time_2_ms_(94.22%)_Space_42.5_MB_(71.58%)

public class Solution {
	//@ requires(*The input `title` is a non-empty string.*);
	//@ requires(*The input `title` consists of one or more words separated by a single space.*);
	//@ requires(*Each word in the input `title` consists of English letters.*);
	//@ requires(*The length of each word in the input `title` is at least 1.*);
	//@ ensures(*The output is a string.*);
	//@ ensures(*The output string is the capitalized version of the input `title`.*);
	//@ ensures(*Each word in the output string has the first letter capitalized and the remaining letters lowercase, except for words with a length of 1 or 2, which are all lowercase.*);
    public String capitalizeTitle(String title) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int j = 0;
        while (i < title.length()) {
            while (j < title.length() && title.charAt(j) != ' ') {
                sb.append(Character.toLowerCase(title.charAt(j)));
                j++;
            }
            int len = j - i;
            if (len > 2) {
                sb.setCharAt(i, Character.toUpperCase(title.charAt(i)));
            }
            if (j == title.length()) {
                break;
            }
            sb.append(title.charAt(j));
            i = ++j;
        }
        return sb.toString();
    }
}