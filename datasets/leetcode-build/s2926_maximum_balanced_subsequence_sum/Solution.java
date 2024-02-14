package g2901_3000.s2926_maximum_balanced_subsequence_sum;

// #Hard #Array #Dynamic_Programming #Binary_Search #Segment_Tree #Binary_Indexed_Tree
// #2023_12_29_Time_37_ms_(99.23%)_Space_68.2_MB_(17.41%)

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of -10^9 to 10^9.*);
	//@ ensures(*The return value is an integer denoting the maximum possible sum of elements in a balanced subsequence of `nums`.*);
	//@ ensures(*The return value is -1 if there are no balanced subsequences in `nums`.*);
	//@ ensures(*The return value is the sum of all elements in `nums` if `nums` is a balanced subsequence.*);
	//@ ensures(*The return value is the maximum sum among all possible balanced subsequences of `nums`.*);
    public long maxBalancedSubsequenceSum(int[] nums) {
        int n = nums.length;
        int m = 0;
        int[] arr = new int[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            if (x > 0) {
                arr[m++] = x - i;
            } else if (x > max) {
                max = x;
            }
        }
        if (m == 0) {
            return max;
        }
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>(m << 1);
        int pre = Integer.MIN_VALUE;
        int index = 1;
        for (int x : arr) {
            if (x == pre) {
                continue;
            }
            map.put(x, index++);
            pre = x;
        }

        BIT bit = new BIT(index);
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                continue;
            }
            index = map.get(nums[i] - i);
            long cur = bit.query(index) + nums[i];
            bit.update(index, cur);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    private static class BIT {
        long[] tree;
        int n;

        public BIT(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        public int lowbit(int index) {
            return index & (-index);
        }

        public void update(int index, long v) {
            while (index <= n && tree[index] < v) {
                tree[index] = v;
                index += lowbit(index);
            }
        }

        public long query(int index) {
            long result = 0;
            while (index > 0) {
                result = Math.max(tree[index], result);
                index -= lowbit(index);
            }
            return result;
        }
    }
}