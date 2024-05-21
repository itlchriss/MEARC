package g1001_1100.s1013_partition_array_into_three_parts_with_equal_sum;

// #Easy #Array #Greedy #2022_05_02_Time_1_ms_(100.00%)_Space_60.6_MB_(25.47%)

public class Solution {
//@ requires(*Formally, we can partition the array if we can find indexes `i + 1 < j` with `(arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])`*);
//@ requires(*Example 1:*);
//@ requires(*Input: arr = [0,2,1,-6,6,-7,9,1,2,0,1]*);
//@ requires(*Output: true*);
//@ requires(*Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1*);
//@ requires(*Example 2:*);
//@ requires(*Input: arr = [0,2,1,-6,6,7,9,-1,2,0,1]*);
//@ requires(*Output: false*);
//@ requires(*Example 3:*);
//@ requires(*Input: arr = [3,3,6,5,-2,2,5,1,-9,4]*);
//@ requires(*Output: true*);
//@ requires(*Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4*);
//@ requires(*Constraints:*);
//@ requires(*<code>3 <= arr.length <= 5  10<sup>4</sup></code>*);
//@ requires(*<code>-10<sup>4</sup> <= arr[i] <= 10<sup>4</sup></code>*);
//@ ensures(*Given an array of integers param_arr, the result is `true` if we can partition the array into three non-empty parts with equal sums.*);
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        // 1. Base condition , the sum should be equally divided into 3 parts
        if (sum % 3 != 0) {
            return false;
        }
        int eq = sum / 3;
        // to keep track of occurences of sum in the sub array
        int count = 0;
        int temp = 0;
        for (int j : arr) {
            // 2. Base / Break condition for loop , i.e. if the count is 2,
            // i.e. sum has been achieved twice ( and there is more indices
            // to go through since we are in the loop again ) then return true
            if (count == 2) {
                return true;
            }
            // 3. Adding to temp array
            temp += j;
            // 4. If sum is achieved , increase the count
            if (temp == eq) {
                count++;
                // put temp=0 to start summing up from the next indices
                temp = 0;
            }
        }
        // 5. If the above conditoin fails , result is false
        return false;
    }
}