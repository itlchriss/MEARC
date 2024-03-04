package g1901_2000.s1991_find_the_middle_index_in_array;

// #Easy #Array #Prefix_Sum #2022_05_18_Time_0_ms_(100.00%)_Space_40.2_MB_(97.29%)

public class Solution {
    // TC : O(1), SC: (1)
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least 1 and at most - Each element in the input array `nums` is an integer.*);
//@ ensures(*Each element in the input array `nums` is between -1000 and *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the leftmost middle index that satisfies the condition.*);
//@ ensures(*If there is no such index, the method returns -1.*);
    public int findMiddleIndex(int[] nums) {
        // find the sum of all numbers in the array
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        // consider leftSum = 0, rightSum = sum
        int leftSum = 0;
        int rightSum = sum;
        /*
         Traverse the array: At each index, subtract the element from rightSum and
         check if leftSum equals rightSum. If they do, return the index.
         Otherwise, add the number at current index to the leftSum and traverse further.
        */
        for (int i = 0; i < nums.length; i++) {
            rightSum -= nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        // index not found, return -1
        return -1;
    }
}