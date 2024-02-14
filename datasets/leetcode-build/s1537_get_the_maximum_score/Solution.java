package g1501_1600.s1537_get_the_maximum_score;

// #Hard #Array #Dynamic_Programming #Greedy #Two_Pointers
// #2022_04_10_Time_6_ms_(58.14%)_Space_77.5_MB_(37.68%)

public class Solution {
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The input arrays `nums1` and `nums2` are sorted in ascending order.*);
	//@ requires(*The input arrays `nums1` and `nums2` contain distinct integers.*);
	//@ requires(*The lengths of `nums1` and `nums2` are between 1 and 10^5 (inclusive).*);
	//@ requires(*The values in `nums1` and `nums2` are between 1 and 10^7 (inclusive).*);
	//@ requires(*The arrays `nums1` and `nums2` are strictly increasing.*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned value is the maximum score obtained from all possible valid paths.*);
	//@ ensures(*The returned value is the sum of unique values in a valid path.*);
	//@ ensures(*The returned value is modulo 10^9 + 7.*);
    public int maxSum(int[] nums1, int[] nums2) {
        int mod = 1000000007;
        long result = 0;
        int start1 = 0;
        int start2 = 0;
        long sum1 = 0;
        long sum2 = 0;
        while (start1 < nums1.length && start2 < nums2.length) {
            if (nums1[start1] < nums2[start2]) {
                sum1 += nums1[start1];
                start1++;
            } else if (nums1[start1] > nums2[start2]) {
                sum2 += nums2[start2];
                start2++;
            } else {
                result += Math.max(sum1, sum2) + nums1[start1];
                start1++;
                start2++;
                sum1 = 0;
                sum2 = 0;
            }
        }
        while (start1 < nums1.length) {
            sum1 += nums1[start1];
            start1++;
        }
        while (start2 < nums2.length) {
            sum2 += nums2[start2];
            start2++;
        }
        return (int) ((Math.max(sum1, sum2) + result) % mod);
    }
}