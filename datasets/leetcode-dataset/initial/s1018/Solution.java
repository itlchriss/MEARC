package g1001_1100.s1018_binary_prefix_divisible_by_5;

// #Easy #Array #2022_02_25_Time_3_ms_(84.58%)_Space_49.7_MB_(32.50%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _an array of booleans_ `answer` _where_ `answer[i]` _is_ `true` _if_ <code>x<sub>i</sub></code> _is divisible by_ `5`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> result = new ArrayList<>(nums.length);
        int remainder = 0;
        for (int j : nums) {
            remainder = (j + (remainder << 1)) % 5;
            result.add(remainder == 0);
        }
        return result;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
