package g0801_0900.s0845_longest_mountain_in_array;

// #Medium #Array #Dynamic_Programming #Two_Pointers #Enumeration
// #2022_03_24_Time_2_ms_(94.37%)_Space_51.9_MB_(54.85%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 3.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the length of the longest mountain subarray.*);
//@ ensures(*If there is no mountain subarray, the method returns 0.*);
    public int longestMountain(int[] arr) {
        int s = 0;
        int i = 1;
        while (i < arr.length - 1) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                int j = i;
                int tem = 1;
                while (j > 0 && arr[j] > arr[j - 1]) {
                    j--;
                    tem++;
                }
                j = i;
                while (j < arr.length - 1 && arr[j] > arr[j + 1]) {
                    j++;
                    tem++;
                }
                s = Math.max(s, tem);
                i = j;
            }
            i++;
        }
        return s;
    }
}