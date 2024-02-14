package g0501_0600.s0591_tag_validator;

// #Hard #String #Stack #2022_08_25_Time_2_ms_(94.35%)_Space_43_MB_(13.56%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	//@ requires(*The input code must be a non-null string.*);
	//@ requires(*The length of the code must be between 1 and 500 characters.*);
	//@ ensures(*The method should return a boolean value indicating whether the code is valid or not.*);
	//@ ensures(*Additional behavioural requirements:*);
	//@ ensures(*The code must be wrapped in a valid closed tag.*);
	//@ ensures(*A closed tag must have the format `<TAG_NAME>TAG_CONTENT</TAG_NAME>`.*);
	//@ ensures(*The start tag and end tag of a closed tag must have the same TAG_NAME.*);
	//@ ensures(*A valid TAG_NAME must only contain uppercase letters and have a length between 1 and 9.*);
	//@ ensures(*A valid TAG_CONTENT may contain other valid closed tags, cdata, and any characters except unmatched `<`, unmatched start and end tags, and unmatched or closed tags with invalid TAG_NAME.*);
	//@ ensures(*A start tag is unmatched if no end tag exists with the same TAG_NAME, and vice versa.*);
	//@ ensures(*A `<` is unmatched if there is no subsequent `>`.*);
	//@ ensures(*When encountering `<` or `</`, all subsequent characters until the next `>` should be parsed as TAG_NAME.*);
	//@ ensures(*The cdata has the format `<![CDATA[CDATA_CONTENT]]>`.*);
	//@ ensures(*The range of CDATA_CONTENT is defined as the characters between `<![CDATA[` and the first subsequent `]]>`.*);
	//@ ensures(*CDATA_CONTENT may contain any characters.*);
	//@ ensures(*The function of cdata is to forbid the validator from parsing CDATA_CONTENT as tags.*);
	//@ ensures(*Even if CDATA_CONTENT has characters that can be parsed as tags, they should be treated as regular characters.*);
    public boolean isValid(String code) {
        Deque<String> stack = new ArrayDeque<>();
        int i = 0;
        while (i < code.length()) {
            if (i > 0 && stack.isEmpty()) {
                return false;
            }
            if (code.startsWith("<![CDATA[", i)) {
                // "<![CDATA[" length is 9
                int j = i + 9;
                i = code.indexOf("]]>", j);
                if (i < 0) {
                    return false;
                }
                // "]]>" length is 3
                i += 3;
            } else if (code.startsWith("</", i)) {
                int j = i + 2;
                i = code.indexOf(">", j);
                if (i < 0 || i == j || i - j > 9) {
                    return false;
                }
                for (int k = j; k < i; k++) {
                    if (!Character.isUpperCase(code.charAt(k))) {
                        return false;
                    }
                }
                String s = code.substring(j, i++);
                if (stack.isEmpty() || !stack.pop().equals(s)) {
                    return false;
                }
            } else if (code.startsWith("<", i)) {
                int j = i + 1;
                i = code.indexOf(">", j);
                if (i < 0 || i == j || i - j > 9) {
                    return false;
                }
                for (int k = j; k < i; k++) {
                    if (!Character.isUpperCase(code.charAt(k))) {
                        return false;
                    }
                }
                String s = code.substring(j, i++);
                stack.push(s);
            } else {
                i++;
            }
        }
        return stack.isEmpty();
    }
}