package g1301_1400.s1394_find_lucky_integer_in_an_array;

// #Easy #Array #Hash_Table #Counting #2022_03_16_Time_2_ms_(82.56%)_Space_44.9_MB_(5.52%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `arr` is an integer.*);
//@ ensures(*Each element in the input array `arr` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `arr` is less than or equal to 500.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value is the largest lucky integer in the input array `arr`.*);
//@ ensures(*If there is no lucky integer in the input array `arr`, the return value is -1.*);
    public int findLucky(int[] arr) {
        int[] numbers = new int[501];
        for (int j : arr) {
            numbers[j]++;
        }
        for (int i = 500; i > 0; i--) {
            if (i == numbers[i]) {
                return i;
            }
        }
        return -1;
    }
}