package g0201_0300.s0217_contains_duplicate;

// #Easy #Top_Interview_Questions #Array #Hash_Table #Sorting #Data_Structure_I_Day_1_Array
// #Programming_Skills_I_Day_11_Containers_and_Libraries #Udemy_Arrays
// #2022_07_02_Time_6_ms_(96.68%)_Space_54.4_MB_(94.38%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is greater than or equal to 1 and is less than or equal to 100000.*);
//@ requires(*All values in the integer array parameter `nums` are greater than or equal to -1000000000 and are less than or equal to 1000000000.*);
//@ ensures(*If any value in the integer array parameter `nums` appears at least twice, the boolean result is equal to the true literal.*);
//@ ensures(*If every element in the integer array parameter `nums` is distinct, the boolean result is equal to the false literal.*);
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