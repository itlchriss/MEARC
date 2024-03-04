package g2201_2300.s2274_maximum_consecutive_floors_without_special_floors;

// #Medium #Array #Sorting #2022_06_15_Time_33_ms_(99.36%)_Space_56.9_MB_(92.31%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The `bottom` and `top` values must be valid floor numbers, where `bottom` is less than or equal to `top`.*);
//@ ensures(*The `special` array must not be empty.*);
//@ ensures(*The values in the `special` array must be valid floor numbers, where each value is greater than or equal to `bottom` and less than or equal to `top`.*);
//@ ensures(*All the values in the `special` array must be unique.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer value representing the maximum number of consecutive floors without a special floor.*);
//@ ensures(*The returned value should be non-negative.*);
//@ ensures(*If all the floors between `bottom` and `top` (inclusive) are special floors, the method should return 0.*);
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int start = bottom;
        int ans = 0;
        for (int j : special) {
            if (j - start > ans) {
                ans = j - start;
                start = j + 1;
            } else {
                start = j + 1;
            }
        }
        if (ans < top - special[special.length - 1]) {
            ans = top - special[special.length - 1];
        }
        return ans;
    }
}