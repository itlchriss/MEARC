package g0001_0100.s0071_simplify_path;

// #Medium #String #Stack #2023_08_11_Time_2_ms_(99.80%)_Space_41.7_MB_(99.37%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
//@ ensures(*The string parameter `path` is an absolute path starting with a slash `'/'`.*);
//@ ensures(*The string result is the simplified canonical path.*);
//@ ensures(*The canonical path starts with a single slash `'/'`.*);
//@ ensures(*Any two directories in the canonical path are separated by a single slash `'/'`.*);
//@ ensures(*The canonical path does not end with a trailing `'/'`.*);
//@ ensures(*The canonical path only contains directories on the path from the root directory to the target file or directory, excluding periods `'.'` or double periods `'..'`.*);
//@ ensures(*Multiple consecutive slashes in the canonical path are replaced by a single slash.*);
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