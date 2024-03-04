package g0501_0600.s0591_tag_validator;

// #Hard #String #Stack #2022_08_25_Time_2_ms_(94.35%)_Space_43_MB_(13.56%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*If the input string parameter `code` is wrapped in a valid closed tag, the boolean result is true.*);
//@ ensures(*The valid closed tag has the format `<TAG_NAME>TAG_CONTENT</TAG_NAME>`, where TAG_NAME and TAG_CONTENT are valid.*);
//@ ensures(*A valid TAG_NAME contains only upper-case letters and has a length in the range [1,9].*);
//@ ensures(*A valid TAG_CONTENT may contain other valid closed tags, cdata, and any characters except unmatched `<`, unmatched start and end tags, and unmatched or closed tags with an invalid TAG_NAME.*);
//@ ensures(*An unmatched start tag exists if no end tag with the same TAG_NAME is found, and vice versa, considering the issue of unbalanced nested tags.*);
//@ ensures(*An unmatched `<` is found if a subsequent `>` cannot be found, and all characters until the next `>` should be parsed as TAG_NAME.*);
//@ ensures(*CDATA has the format `<![CDATA[CDATA_CONTENT]]>`, where CDATA_CONTENT is the characters between `<![CDATA[` and the first subsequent `]]>`.*);
//@ ensures(*CDATA_CONTENT may contain any characters and should be treated as regular characters, not parsed as tags.*);
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