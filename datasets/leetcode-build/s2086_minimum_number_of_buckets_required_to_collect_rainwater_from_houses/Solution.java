package g2001_2100.s2086_minimum_number_of_buckets_required_to_collect_rainwater_from_houses;

// #Medium #String #Dynamic_Programming #Greedy
// #2022_05_27_Time_8_ms_(73.71%)_Space_50.4_MB_(40.57%)

@SuppressWarnings("java:S135")
public class Solution {
	//@ requires(*The input string `street` is not null.*);
	//@ requires(*The length of the input string `street` is greater than or equal to 1.*);
	//@ requires(*Each character in the input string `street` is either 'H' or '.'.*);
	//@ ensures(*The return value is an integer representing the minimum number of buckets needed.*);
	//@ ensures(*If it is impossible to collect rainwater from all the houses, the return value is -1.*);
	//@ ensures(*For every house in the input string `street`, there is at least one bucket collecting rainwater from it.*);
    public int minimumBuckets(String street) {
        // check if houses have space in between or not
        // eg:".HHH."
        // array formation
        char[] arr = street.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '.') {
                continue;
            }
            if (i + 1 < arr.length && arr[i + 1] == '.') {
                continue;
            }
            // H is present before curr character
            if (i - 1 >= 0 && arr[i - 1] == '.') {
                continue;
            }
            return -1;
        }
        int x = 0;
        for (int j = 0; j < arr.length; j++) {
            // point move next we only take care of H
            if (arr[j] == 'H') {
                if (j - 1 >= 0 && arr[j - 1] == 'X') {
                    continue;
                }
                if (j + 1 < arr.length && arr[j + 1] == '.') {
                    arr[j + 1] = 'X';
                } else {
                    arr[j - 1] = 'X';
                }
                x++;
            }
        }
        return x;
    }
}