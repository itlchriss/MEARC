package g2401_2500.s2444_count_subarrays_with_fixed_bounds;

// #Hard #Array #Sliding_Window #Queue #Monotonic_Queue
// #2022_12_14_Time_9_ms_(83.94%)_Space_56.5_MB_(97.01%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of `nums` is at least 2.*);
//@ ensures(*The values in `nums` are integers.*);
//@ ensures(*The value of `minK` is an integer.*);
//@ ensures(*The value of `maxK` is an integer.*);
//@ ensures(*The value of `minK` is less than or equal to the value of `maxK`.*);
//@ ensures(*The value of `minK` is within the range of values in `nums`.*);
//@ ensures(*The value of `maxK` is within the range of values in `nums`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the number of fixed-bound subarrays.*);
//@ ensures(*The returned value is non-negative.*);
//@ ensures(*The returned value is the correct count of fixed-bound subarrays according to the given conditions.*);
//@ ensures(*The method does not modify the input array `nums`.*);
//@ ensures(*The method does not modify the values of `minK` and `maxK`.*);
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] >= minK && nums[i] <= maxK) {
                int a = i;
                int b = i;
                int mini = 0;
                int maxi = 0;
                while (i != nums.length && (nums[i] >= minK && nums[i] <= maxK)) {
                    i++;
                }
                while (true) {
                    for (; b != i && (mini == 0 || maxi == 0); b++) {
                        if (nums[b] == minK) {
                            mini++;
                        }
                        if (nums[b] == maxK) {
                            maxi++;
                        }
                    }
                    if (mini == 0 || maxi == 0) {
                        break;
                    }
                    for (; mini != 0 && maxi != 0; ans += 1 + (i - b), a++) {
                        if (nums[a] == minK) {
                            mini--;
                        }
                        if (nums[a] == maxK) {
                            maxi--;
                        }
                    }
                }
            }
            i++;
        }
        return ans;
    }
}