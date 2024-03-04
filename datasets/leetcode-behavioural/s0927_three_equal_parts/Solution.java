package g0901_1000.s0927_three_equal_parts;

// #Hard #Array #Math #2022_03_29_Time_2_ms_(90.20%)_Space_47.4_MB_(96.08%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of `arr` is at least 3.*);
//@ ensures(*Each element in `arr` is either 0 or 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*If it is possible to divide `arr` into three non-empty parts such that all three parts represent the same binary value, return an array `[i, j]` where `i + 1 < j`. `i` represents the index of the last element in the first part, and `j` represents the index of the first element in the third part.*);
//@ ensures(*If it is not possible to divide `arr` into three non-empty parts with equal binary values, return `[-1, -1]`.*);
    public int[] threeEqualParts(int[] arr) {
        int ones = 0;
        for (int num : arr) {
            ones += num;
        }
        if (ones == 0) {
            return new int[] {0, 2};
        } else if (ones % 3 != 0) {
            return new int[] {-1, -1};
        }
        ones /= 3;
        int index1 = -1;
        int index2 = -1;
        int index3 = -1;
        int totalOnes = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                continue;
            }
            totalOnes += arr[i];
            if (totalOnes == 1) {
                index1 = i;
            } else if (totalOnes == ones + 1) {
                index2 = i;
            } else if (totalOnes == 2 * ones + 1) {
                index3 = i;
            }
        }
        while (index3 < arr.length) {
            if (arr[index1] == arr[index3] && arr[index2] == arr[index3]) {
                ++index1;
                ++index2;
                ++index3;
            } else {
                return new int[] {-1, -1};
            }
        }
        return new int[] {index1 - 1, index2};
    }
}