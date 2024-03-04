package g1801_1900.s1887_reduction_operations_to_make_the_array_elements_equal;

// #Medium #Array #Sorting #2022_05_10_Time_26_ms_(94.56%)_Space_117_MB_(20.41%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` must be integers.*);
//@ ensures(*The elements in the input array `nums` must be greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` must be less than or equal to 5 * 10^4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output of the method must be an integer.*);
//@ ensures(*The output of the method must represent the number of operations required to make all elements in `nums` equal.*);
//@ ensures(*The elements in the input array `nums` must be modified such that all elements are equal.*);
    public int reductionOperations(int[] nums) {
        int[] arr = new int[100001];
        for (int i : nums) {
            arr[i]++;
        }
        int val = 0;
        int curr = 0;
        for (int i = 100000; i >= 0; i--) {
            if (arr[i] != 0) {
                val += curr;
                curr += arr[i];
            }
        }
        return val;
    }
}