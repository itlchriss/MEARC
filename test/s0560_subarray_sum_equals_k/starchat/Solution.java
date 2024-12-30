package g0501_0600.s0560_subarray_sum_equals_k;

// #Medium #Top_100_Liked_Questions #Array #Hash_Table #Prefix_Sum #Data_Structure_II_Day_5_Array
// #Big_O_Time_O(n)_Space_O(n) #2022_08_03_Time_21_ms_(98.97%)_Space_46.8_MB_(88.27%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 20000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000 and is greater than or equal to -1000.*);
//@ requires(*The integer parameter `k` is less than or equal to 1000000000 and is greater than or equal to -1000000000.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2528\. Count Substrings That Differ by One Character*);
//@ requires(*Medium*);
//@ requires(*Given two strings `s` and `t`, return _the number of substrings in_ `s` _that differ from some substring in_ `t` _by exactly one character_.*);
//@ requires(**);
//@ requires(*In other words, return the number of substrings in `s` and `t` such that replacing exactly one character in `s` with a different character in `t` makes them equal.*);
//@ requires(**);
//@ requires(*A substring is a contiguous sequence of characters within a string.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aba ", t =  "abb "*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** The 2 substrings are  "aba " and  "abb ". *);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "abe ", t =  "bbc "*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** The 3 substrings are  "abe ",  "bbe ", and  "bbc ". *);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length, t.length <= 100`*);
//@ requires(**   `s` and `t` consist of only lowercase English letters.*);
//@ requires(**);
//@ requires(*Method signature: public int countSubstrings(String s, String t)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `t` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` and `t` consist of only lowercase English letters.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,1,1] and the integer parameter `k` is equal to 2, the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3] and the integer parameter `k` is equal to 3, the integer result is equal to 2.*);
//@ ensures(*We replace the first 'a' from  "aba " to turn  "bba ", resulting in  "bba " which is different from  "abb ".*);
//@ ensures(*We replace the second 'b' from  "aba " to turn  "aab ", resulting in  "aab " which is different from  "abb ".*);
//@ ensures(*We replace the first 'a' from  "abe " to turn  "bbe ", resulting in  "bbe " which is different from  "bbc ".*);
//@ ensures(*We replace the first 'b' from  "abe " to turn  "aae ", resulting in  "aae " which is different from  "bbc ".*);
//@ ensures(*We replace both 'b' from  "abe " to turn  "abe ", resulting in  "abe " which is different from  "bbc ".*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "aba" and the string parameter `t` is equal to "abb", the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to "abe" and the string parameter `t` is equal to "bbc", the integer result is equal to 3.*);
    public int subarraySum(int[] nums, int k) {
        int tempSum = 0;
        int ret = 0;
        Map<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1);
        for (int i : nums) {
            tempSum += i;
            if (sumCount.containsKey(tempSum - k)) {
                ret += sumCount.get(tempSum - k);
            }
            if (sumCount.get(tempSum) != null) {
                sumCount.put(tempSum, sumCount.get(tempSum) + 1);
            } else {
                sumCount.put(tempSum, 1);
            }
        }
        return ret;
    }
}