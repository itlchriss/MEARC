package g1901_2000.s1959_minimum_total_space_wasted_with_k_resizing_operations;

// #Medium #Array #Dynamic_Programming #2022_05_20_Time_42_ms_(95.45%)_Space_43_MB_(66.67%)

@SuppressWarnings("java:S135")
public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is greater than or equal to 1 and less than or equal to 200.*);
	//@ requires(*Each element in `nums` is an integer greater than or equal to 1 and less than or equal to 10^6.*);
	//@ requires(*The value of `k` is greater than or equal to 0 and less than or equal to the length of `nums` minus 1.*);
	//@ ensures(*The method returns an integer representing the minimum total space wasted.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
    public int minSpaceWastedKResizing(int[] arr, int k) {
        int n = arr.length;
        k++;
        int[][] dp = new int[n][k + 1];
        for (int j = 1; j <= k; j++) {
            for (int i = n - 1; i >= 0; i--) {
                int ele = n - i;
                if (j == ele) {
                    dp[i][j] = 0;
                    continue;
                }
                if (j == 1) {
                    int sum = 0;
                    int maxEle = -1;
                    for (int l = i; l < n; l++) {
                        maxEle = Math.max(maxEle, arr[l]);
                        sum += arr[l];
                    }
                    dp[i][j] = (maxEle * (n - i)) - sum;
                    continue;
                }
                int maxEle = -1;
                int sum = 0;
                int ans = Integer.MAX_VALUE;
                for (int cut = i; cut <= n - j; cut++) {
                    maxEle = Math.max(maxEle, arr[cut]);
                    sum += arr[cut];
                    int recAns = dp[cut + 1][j - 1];
                    int myAns = (maxEle * (cut - i + 1)) - (sum);
                    ans = Math.min(ans, recAns + myAns);
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][k];
    }
}