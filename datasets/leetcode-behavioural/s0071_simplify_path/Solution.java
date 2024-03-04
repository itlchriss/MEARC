package g0001_0100.s0071_simplify_path;

// #Medium #String #Stack #2023_08_11_Time_2_ms_(99.80%)_Space_41.7_MB_(99.37%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
//@ ensures(*If the string parameter `path` is an absolute path starting with a slash `'/'`, the string result is the simplified canonical path.*);
//@ ensures(*Any period `'.'` in the string parameter `path` refers to the current directory and should be removed in the string result.*);
//@ ensures(*Any double period `'..'` in the string parameter `path` refers to the directory up a level and should be removed along with the previous directory in the string result.*);
//@ ensures(*Any multiple consecutive slashes (i.e. `'//'`) in the string parameter `path` should be treated as a single slash `'/'` in the string result.*);
//@ ensures(*The string result should start with a single slash `'/'`.*);
//@ ensures(*Any two directories in the string result should be separated by a single slash `'/'`.*);
//@ ensures(*The string result should not end with a trailing `'/'`.*);
//@ ensures(*The string result should only contain the directories on the path from the root directory to the target file or directory, without any period `'.'` or double period `'..'`.*);
    public String simplifyPath(String path) {
        Deque<String> stk = new ArrayDeque<>();
        int start = 0;
        while (start < path.length()) {
            while (start < path.length() && path.charAt(start) == '/') {
                start++;
            }
            int end = start;
            while (end < path.length() && path.charAt(end) != '/') {
                end++;
            }
            String s = path.substring(start, end);
            if (s.equals("..")) {
                if (!stk.isEmpty()) {
                    stk.pop();
                }
            } else if (!s.equals(".") && !s.equals("")) {
                stk.push(s);
            }
            start = end + 1;
        }
        StringBuilder ans = new StringBuilder();
        while (!stk.isEmpty()) {
            ans.insert(0, stk.pop());
            ans.insert(0, "/");
        }
        return ans.length() > 0 ? ans.toString() : "/";
    }
}