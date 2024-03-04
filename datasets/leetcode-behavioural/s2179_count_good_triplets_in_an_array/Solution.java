package g2101_2200.s2179_count_good_triplets_in_an_array;

// #Hard #Array #Binary_Search #Ordered_Set #Divide_and_Conquer #Segment_Tree #Binary_Indexed_Tree
// #Merge_Sort #2022_06_09_Time_16_ms_(92.94%)_Space_78.1_MB_(77.65%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `nums1` and `nums2` are not null.*);
//@ ensures(*The length of `nums1` is equal to the length of `nums2`.*);
//@ ensures(*The length of `nums1` is at least 3.*);
//@ ensures(*The values in `nums1` and `nums2` are integers.*);
//@ ensures(*The values in `nums1` and `nums2` are between 0 and n-1, inclusive.*);
//@ ensures(*`nums1` and `nums2` are permutations of `[0, 1, ..., n - 1]`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the total number of good triplets.*);
//@ ensures(*The returned value is non-negative.*);
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] idx = new int[n];
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            idx[nums2[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = idx[nums1[i]];
        }
        Tree tree = new Tree(n);
        long res = 0L;
        for (int i = 0; i < n; i++) {
            int smaller = tree.query(arr[i]);
            int bigger = n - (arr[i] + 1) - (i - smaller);
            res += (long) smaller * bigger;
            tree.update(arr[i] + 1, 1);
        }
        return res;
    }

    private static class Tree {
        int[] array;
        int n;

        public Tree(int n) {
            this.n = n;
            array = new int[n + 1];
        }

        int lowbit(int x) {
            return x & (-x);
        }

        void update(int i, int delta) {
            while (i <= n) {
                array[i] += delta;
                i += lowbit(i);
            }
        }

        int query(int k) {
            int ans = 0;
            while (k > 0) {
                ans += array[k];
                k -= lowbit(k);
            }
            return ans;
        }
    }
}