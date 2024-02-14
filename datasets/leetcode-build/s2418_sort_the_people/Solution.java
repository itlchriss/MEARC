package g2401_2500.s2418_sort_the_people;

// #Easy #Array #String #Hash_Table #Sorting #2022_11_18_Time_3_ms_(99.81%)_Space_54.6_MB_(49.36%)

public class Solution {
	//@ requires(*The input arrays `names` and `heights` are not null.*);
	//@ requires(*The lengths of `names` and `heights` are equal.*);
	//@ requires(*The length of `names` is greater than 0.*);
	//@ requires(*The length of `names` is less than or equal to 1000.*);
	//@ requires(*The length of each string in `names` is greater than 0.*);
	//@ requires(*The length of each string in `names` is less than or equal to 20.*);
	//@ requires(*Each element in `heights` is a positive integer.*);
	//@ requires(*Each element in `heights` is distinct.*);
	//@ ensures(*The returned array `names` is sorted in descending order based on the corresponding heights in `heights`.*);
	//@ ensures(*The order of elements in `names` with the same height is preserved.*);
    public String[] sortPeople(String[] names, int[] heights) {
        quicksort(names, heights, 0, heights.length - 1);
        return names;
    }

    private void quicksort(String[] names, int[] heights, int low, int high) {
        if (low >= high) {
            return;
        }
        int start = low;
        int end = high;
        int mid = start + (end - start) / 2;
        int pivot = heights[mid];
        while (start < end) {
            while (heights[start] > pivot) {
                start++;
            }
            while (heights[end] < pivot) {
                end--;
            }
            int tempHeight = heights[start];
            heights[start] = heights[end];
            heights[end] = tempHeight;
            String tempName = names[start];
            names[start] = names[end];
            names[end] = tempName;
        }
        quicksort(names, heights, low, end - 1);
        quicksort(names, heights, start + 1, high);
    }
}