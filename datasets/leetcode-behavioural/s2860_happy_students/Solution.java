package g2801_2900.s2860_happy_students;

// #Medium #Array #Sorting #Enumeration #2023_12_19_Time_33_ms_(91.76%)_Space_55.3_MB_(82.94%)

import java.util.Collections;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input list `nums` is not null.*);
//@ ensures(*The length of `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in `nums` are non-negative integers.*);
//@ ensures(*The elements in `nums` are less than the length of `nums`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer representing the number of ways to select a group of students.*);
//@ ensures(*The return value is greater than or equal to 0.*);
    public int countWays(List<Integer> nums) {
        Collections.sort(nums);
        int cnt = 0;
        int n = nums.size();
        if (nums.get(0) != 0) {
            cnt++;
        }
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) < (i + 1) && (nums.get(i + 1) > (i + 1))) {
                cnt++;
            }
        }
        if (n > nums.get(n - 1)) {
            cnt++;
        }
        return cnt;
    }
}