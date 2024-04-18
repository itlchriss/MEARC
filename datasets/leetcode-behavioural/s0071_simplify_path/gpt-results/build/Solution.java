package g0001_0100.s0071_simplify_path;

// #Medium #String #Stack #2023_08_11_Time_2_ms_(99.80%)_Space_41.7_MB_(99.37%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
// @requires path != null && path.length() >= 1 && path.length() <= 3000;
// @requires path.startsWith("/");
// @ensures \result.startsWith("/");
// @ensures !\result.endsWith("/");
// @ensures \result.contains("/");
// @ensures !\result.contains("..");
// @ensures !\result.contains(".");
// @ensures !\result.contains("//");
// @ensures \result.equals("/home") ==> path.equals("/home/");
// @ensures \result.equals("/") ==> path.equals("/../");
// @ensures \result.equals("/home/foo") ==> path.equals("/home//foo/");
// @ensures \result.equals("/c") ==> path.equals("/a/./b/../../c/");
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