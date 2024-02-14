package g2201_2300.s2215_find_the_difference_of_two_arrays;

// #Easy #Array #Hash_Table #2022_06_09_Time_11_ms_(87.39%)_Space_43.2_MB_(77.06%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The lengths of `nums1` and `nums2` are between 1 and 1000, inclusive.*);
	//@ requires(*The elements of `nums1` and `nums2` are integers between -1000 and 1000, inclusive.*);
	//@ ensures(*The returned list `answer` is not null.*);
	//@ ensures(*The size of `answer` is 2.*);
	//@ ensures(*`answer[0]` is a list of distinct integers from `nums1` that are not present in `nums2`.*);
	//@ ensures(*`answer[1]` is a list of distinct integers from `nums2` that are not present in `nums1`.*);
	//@ ensures(*The integers in `answer[0]` and `answer[1]` may be in any order.*);
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = createSet(nums1);
        Set<Integer> set2 = createSet(nums2);
        return Arrays.asList(getMissing(set1, set2), getMissing(set2, set1));
    }

    private Set<Integer> createSet(int[] array) {
        Set<Integer> set = new HashSet<>();
        for (int x : array) {
            set.add(x);
        }
        return set;
    }

    private List<Integer> getMissing(Set<Integer> first, Set<Integer> second) {
        List<Integer> list = new ArrayList<>();
        for (int x : first) {
            if (!second.contains(x)) {
                list.add(x);
            }
        }
        return list;
    }
}