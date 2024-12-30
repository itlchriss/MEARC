package g0001_0100.s0055_jump_game;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Dynamic_Programming #Greedy
// #Algorithm_II_Day_12_Dynamic_Programming #Dynamic_Programming_I_Day_4 #Udemy_Arrays
// #Big_O_Time_O(n)_Space_O(1) #2023_08_11_Time_2_ms_(79.47%)_Space_44.8_MB_(22.14%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 100000 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1346\. Check If N and Its Double Exist*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given an array `arr` of integers, write a function to check if there exists two integers `N` and its double (2 * N) that appear **exactly twice** in the array.*);
//@ requires(**);
//@ requires(*Return `true` if such a pair exists, otherwise, return `false`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** arr = \[10,2,5,3\]*);
//@ requires(**);
//@ requires(***Output:** true*);
//@ requires(**);
//@ requires(***Explanation:** N = 10 is the double of 5, that appears exactly twice.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** arr = \[7,1,14,11\]*);
//@ requires(**);
//@ requires(***Output:** true*);
//@ requires(**);
//@ requires(***Explanation:** N = 7 is the double of 3.5, that appears exactly twice.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** arr = \[3,1,7,11\]*);
//@ requires(**);
//@ requires(***Output:** false*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `2 <= arr.length <= 500`*);
//@ requires(**   `-10^3 <= arr[i] <= 10^3`*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve the problem in `O(n)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public boolean checkIfExist(int[] arr)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `arr` is less than or equal to 500 and is greater than or equal to 2.*);
//@ requires(*All values in the integer array parameter `arr` are less than or equal to 1000 and is greater than or equal to -1000.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 1287\. Element Appearing More Than 25% In Sorted Array*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given an integer array `sortedInAscendingOrder`, return `true` _if every element appears at least twice and is divisible by_ `25`.*);
//@ requires(**);
//@ requires(*Return `false` _otherwise_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** sortedInAscendingOrder = \[2,2,1,1,1\]*);
//@ requires(**);
//@ requires(***Output:** true*);
//@ requires(**);
//@ requires(***Explanation:***);
//@ requires(**);
//@ requires(*The element 2 appears three times, divisible by 25 (25/1 = 25).*);
//@ requires(**);
//@ requires(*Every other element appears twice.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** sortedInAscendingOrder = \[1,1,1,3,3,7\]*);
//@ requires(**);
//@ requires(***Output:** false*);
//@ requires(**);
//@ requires(***Explanation:***);
//@ requires(**);
//@ requires(*The element 1 appears three times, but not divisible by 25 (25/1!= 25).*);
//@ requires(**);
//@ requires(*The element 3 appears twice, but not divisible by 25 (25/3!= 8.333...).*);
//@ requires(**);
//@ requires(*The element 7 appears only once.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `5 <= sortedIn*);
//@ ensures(*If the integer result is true, it means that it is possible to reach the last index of the integer array parameter `nums`.*);
//@ ensures(*If the integer result is false, it means that it is not possible to reach the last index of the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [2,3,1,1,4], the integer result is equal to true.*);
//@ ensures(*If the integer array parameter `nums` is equal to [3,2,1,0,4], the integer result is equal to false.*);
//@ ensures(*If the integer result is true, it means that there exists two integers `N` and its double (2 * N) that appear exactly twice in the array.*);
//@ ensures(*If the integer result is false, it means that there does not exist two integers `N` and its double (2 * N) that appear exactly twice in the array.*);
//@ ensures(*If the integer array parameter `arr` is equal to [10,2,5,3], the integer result is equal to true.*);
//@ ensures(*If the integer array parameter `arr` is equal to [7,1,14,11], the integer result is equal to true.*);
//@ ensures(*If the integer array parameter `arr` is equal to [3,1,7,11], the integer result is equal to false.*);
    public boolean canJump(int[] nums) {
        int sz = nums.length;
        // we set 1 so it won't break on the first iteration
        int tmp = 1;
        //@ loop_invariant 0 <= i <= sz;
        for (int i = 0; i < sz; i++) {
            // we always deduct tmp for every iteration
            tmp--;
            if (tmp < 0) {
                // if from previous iteration tmp is already 0, it will be <0 here
                // leading to false value
                return false;
            }
            // we get the maximum value because this value is supposed
            // to be our iterator, if both values are 0, then the next
            // iteration we will return false
            // if either both or one of them are not 0 then we will keep doing this and check.

            // We can stop the whole iteration with this condition. without this condition the code
            // runs in 2ms 79.6%, adding this condition improves the performance into 1ms 100%
            // because if the test case jump value is quite large, instead of just iterate, we can
            // just check using this condition
            // example: [10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0] -> we can just jump to the end without
            // iterating whole array
            tmp = Math.max(tmp, nums[i]);
            if (i + tmp >= sz - 1) {
                return true;
            }
        }
        // we can just return true at the end, because if tmp is 0 on previous
        // iteration,
        // even though the next iteration index is the last one, it will return false under the
        // tmp<0 condition
        return true;
    }
}