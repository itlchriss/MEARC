package g2101_2200.s2138_divide_a_string_into_groups_of_size_k;

// #Easy #String #Simulation #2022_06_05_Time_2_ms_(70.97%)_Space_42.6_MB_(65.21%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given the string `s`, the size of each group `k` and the character `fill`, return _a string array denoting the **composition of every group**_ `s` _has been divided into, using the above procedure_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public String[] divideString(String s, int k, char fill) {
        String[] ans = new String[(s.length() % k != 0) ? (s.length() / k) + 1 : s.length() / k];
        int t = k;
        List<String> str = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (t > 0) {
                sb.append(s.charAt(i));
                t--;
            } else {
                t = k;
                str.add(sb.toString());
                sb.setLength(0);
                i--;
            }
            i++;
        }
        if (t > 0) {
            while (t-- > 0) {
                sb.append(fill);
            }
        }
        str.add(sb.toString());
        for (int j = 0; j < str.size(); j++) {
            ans[j] = str.get(j);
        }
        return ans;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
