package g0201_0300.s0217_contains_duplicate;

// #Easy #Top_Interview_Questions #Array #Hash_Table #Sorting #Data_Structure_I_Day_1_Array
// #Programming_Skills_I_Day_11_Containers_and_Libraries #Udemy_Arrays
// #2022_07_02_Time_6_ms_(96.68%)_Space_54.4_MB_(94.38%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000000000 and are greater than or equal to -1000000000.*);
//@ ensures(*If the boolean result is equal to the true literal, at least one value in the integer array parameter `nums` appears more than once.*);
//@ ensures(*If the boolean result is equal to the false literal, all values in the integer array parameter `nums` are unique.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,1], the boolean result is equal to the true literal.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,4], the boolean result is equal to the false literal.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,1,1,3,3,4,3,2,4,2], the boolean result is equal to the true literal.*);
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }
}