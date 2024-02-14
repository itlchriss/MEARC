package g1101_1200.s1122_relative_sort_array;

// #Easy #Array #Hash_Table #Sorting #Counting_Sort
// #2023_06_01_Time_0_ms_(100.00%)_Space_41.4_MB_(83.65%)

public class Solution {
	//@ requires(*The input arrays `arr1` and `arr2` are not null.*);
	//@ requires(*The lengths of `arr1` and `arr2` are between 1 and 1000, inclusive.*);
	//@ requires(*The elements of `arr2` are distinct.*);
	//@ requires(*Each element in `arr2` is also present in `arr1`.*);
	//@ ensures(*The relative ordering of items in `arr1` is the same as in `arr2`.*);
	//@ ensures(*Elements that do not appear in `arr2` are placed at the end of `arr1` in ascending order.*);
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] map = new int[1001];
        for (int a : arr1) {
            map[a]++;
        }
        int i = 0;
        for (int b : arr2) {
            while (map[b] != 0) {
                map[b]--;
                arr1[i] = b;
                i++;
            }
        }
        for (int j = 0; j < map.length; j++) {
            while (map[j] != 0) {
                arr1[i] = j;
                map[j]--;
                i++;
            }
        }
        return arr1;
    }
}