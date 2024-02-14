package g1301_1400.s1346_check_if_n_and_its_double_exist;

// #Easy #Array #Hash_Table #Sorting #Binary_Search #Two_Pointers #Binary_Search_I_Day_9
// #2022_03_21_Time_1_ms_(99.64%)_Space_41.9_MB_(70.36%)

public class Solution {
	//@ requires(*The input array `arr` must not be null.*);
	//@ requires(*The length of the input array `arr` must be at least 2.*);
	//@ requires(*The elements of the input array `arr` must be integers.*);
	//@ requires(*The elements of the input array `arr` must be within the range of -10^3 to 10^3.*);
	//@ ensures(*The method should return a boolean value indicating whether there exist two integers `N` and `M` such that `N` is the double of `M`.*);
	//@ ensures(*If there exist two indices `i` and `j` such that `i != j`, `0 <= i, j < arr.length`, and `arr[i] == 2 * arr[j]`, the method should return true.*);
	//@ ensures(*If there does not exist two indices `i` and `j` satisfying the above conditions, the method should return false.*);
    public boolean checkIfExist(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j && (arr[i] * 2 == arr[j] || arr[i] == arr[j] * 2)) {
                    return true;
                }
            }
        }
        return false;
    }
}