package g0001_0100.s0071_simplify_path;

// #Medium #String #Stack #2023_08_11_Time_2_ms_(99.80%)_Space_41.7_MB_(99.37%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	//@ requires(*1. The input `path` is a non-null string.*);
	//@ requires(*2. The input `path` starts with a slash `'/'`.*);
	//@ requires(*3. The input `path` is a valid absolute Unix path.*);
	//@ ensures(*1. The output is a non-null string.*);
	//@ ensures(*2. The output starts with a single slash `'/'`.*);
	//@ ensures(*3. The output does not end with a trailing slash `'/'`.*);
	//@ ensures(*4. The output only contains the directories on the path from the root directory to the target file or directory.*);
	//@ ensures(*5. The output does not contain any periods `'.'` or double periods `'..'`.*);
	//@ ensures(*6. Any multiple consecutive slashes in the output are replaced by a single slash `'/'`.*);
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