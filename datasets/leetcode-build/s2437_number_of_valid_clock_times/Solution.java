package g2401_2500.s2437_number_of_valid_clock_times;

// #Easy #String #Enumeration #2022_12_07_Time_0_ms_(100.00%)_Space_42.2_MB_(29.58%)

public class Solution {
	//@ requires(*The input string `time` must be a valid string of length 5.*);
	//@ requires(*The format of the string `time` must be "hh:mm".*);
	//@ requires(*The hours represented by the first two characters of `time` must be between "00" and "23".*);
	//@ requires(*The minutes represented by the last two characters of `time` must be between "00" and "59".*);
	//@ requires(*The string `time` may contain one or more "?" symbols, representing unknown digits.*);
	//@ ensures(*The method should return an integer `answer` representing the number of valid clock times that can be created by replacing every "?" with a digit from 0 to 9.*);
	//@ ensures(*The valid clock times should be within the range of "00:00" to "23:59".*);
	//@ ensures(*The method should handle all possible combinations of replacing "?" symbols with digits from 0 to 9.*);
	//@ ensures(*The method should not consider invalid clock times, such as "25:00" or "99:99".*);
	//@ ensures(*The method should return 0 if there are no valid clock times that can be created.*);
    public int countTime(String time) {
        int[] counts = new int[] {3, 10, 0, 6, 10};
        char[] ch = time.toCharArray();
        int result = 1;
        if (ch[0] == '2') {
            counts[1] = 4;
        }
        if ((ch[1] - '0') > 3) {
            counts[0] = 2;
        }
        if (ch[0] == '?' && ch[1] == '?') {
            counts[0] = 1;
            counts[1] = 24;
        }
        for (int i = 0; i < 5; i++) {
            char ch1 = ch[i];
            if (ch1 == '?') {
                result *= counts[i];
            }
        }
        return result;
    }
}