package g1001_1100.s1089_duplicate_zeros;

// #Easy #Array #Two_Pointers #2022_02_24_Time_2_ms_(67.91%)_Space_46.1_MB_(15.10%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*The elements of the input array `arr` are integers.*);
//@ ensures(*The elements of the input array `arr` are within the range of 0 to 9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*Each occurrence of zero in the input array `arr` is duplicated.*);
//@ ensures(*The duplicated zeros are shifted to the right.*);
//@ ensures(*The length of the modified array is the same as the original array.*);
//@ ensures(*Elements beyond the length of the original array are not written.*);
    public void duplicateZeros(int[] arr) {
        int countZero = 0;
        for (int k : arr) {
            if (k == 0) {
                countZero++;
            }
        }
        int len = arr.length + countZero;
        // We just need O(1) space if we scan from back
        // i point to the original array, j point to the new location
        int i = arr.length - 1;
        int j = len - 1;
        while (i < j) {
            // copy twice when hit '0'
            if (arr[i] == 0) {
                if (j < arr.length) {
                    arr[j] = arr[i];
                }
                j--;
            }
            if (j < arr.length) {
                arr[j] = arr[i];
            }
            i--;
            j--;
        }
    }
}