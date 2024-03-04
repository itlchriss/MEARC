package g2701_2800.s2708_maximum_strength_of_a_group;

// #Medium #Array #Sorting #Greedy #Backtracking
// #2023_09_15_Time_2_ms_(85.08%)_Space_43.1_MB_(57.14%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least - The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are within the range of -9 to *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the maximum strength of a group.*);
//@ ensures(*The returned maximum strength is a non-negative value.*);
    public long maxStrength(int[] nums) {
        List<Integer> filtered = new ArrayList<>();
        long product = 1L;
        boolean hasZero = false;
        for (int num : nums) {
            if (num == 0) {
                hasZero = true;
                continue;
            }
            filtered.add(num);
            product *= num;
        }
        if (filtered.isEmpty()) {
            return 0;
        }
        if (filtered.size() == 1 && filtered.get(0) <= 0) {
            return hasZero ? 0 : filtered.get(0);
        }
        long result = product;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            result = Math.max(result, product / num);
        }
        return result;
    }
}