package g1601_1700.s1640_check_array_formation_through_concatenation;

// #Easy #Array #Hash_Table #2022_04_21_Time_1_ms_(87.62%)_Space_42.2_MB_(60.28%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The input array `pieces` is not null.*);
//@ ensures(*The length of `pieces` is greater than or equal to 1.*);
//@ ensures(*The length of `arr` is greater than or equal to the length of `pieces`.*);
//@ ensures(*The sum of the lengths of all arrays in `pieces` is equal to the length of `arr`.*);
//@ ensures(*The integers in `arr` are distinct.*);
//@ ensures(*The integers in `pieces` are distinct.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if it is possible to form the array `arr` from `pieces`, otherwise it returns `false`.*);
    public boolean canFormArray(int[] arr, int[][] pieces) {
        for (int[] piece : pieces) {
            int first = piece[0];
            int index = findIndex(arr, first);
            if (index == -1) {
                return false;
            }
            int i = 0;
            for (int j = index; i < piece.length && j < arr.length; i++, j++) {
                if (arr[j] != piece[i]) {
                    return false;
                }
            }
            if (i != piece.length) {
                return false;
            }
        }
        return true;
    }

    private int findIndex(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}