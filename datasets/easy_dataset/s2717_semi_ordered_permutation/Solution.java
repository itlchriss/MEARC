package g2701_2800.s2717_semi_ordered_permutation;

// #Easy #Array #Simulation #2023_09_15_Time_3_ms_(100.00%)_Space_43.3_MB_(98.28%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is at least 2.*);
//@ ensures(*The elements in the input array `nums` are unique.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are between 1 and 50 (inclusive).*);
//@ ensures(*The input array `nums` is a permutation.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum number of operations required to make `nums` a semi-ordered permutation.*);
//@ ensures(*The input array `nums` is not modified.*);
//@ ensures(*The first element of the input array `nums` is 1.*);
//@ ensures(*The last element of the input array `nums` is `n`, where `n` is the length of the input array `nums`.*);
//@ ensures(*The input array `nums` is a semi-ordered permutation after the minimum number of operations.*);
    public int semiOrderedPermutation(int[] nums) {
        int a = 1;
        int b = nums.length;
        int idxA = 0;
        int idxB = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == a) {
                idxA = i;
            }
            if (nums[i] == b) {
                idxB = i;
            }
        }
        b = b - idxB - 1;
        if (idxB < idxA) {
            return idxA + b - 1;
        } else {
            return idxA + b;
        }
    }
}