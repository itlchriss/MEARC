package g2601_2700.s2678_number_of_senior_citizens;

// #Easy #Array #String #2023_09_11_Time_0_ms_(100.00%)_Space_40.7_MB_(97.65%)

public class Solution {
	//@ requires(*The input array `details` is not null.*);
	//@ requires(*The length of each string in `details` is - Each string in `details` consists of digits from '0' to '9'.*);
	//@ requires(*The character at index 10 in each string in `details` is either 'M' or 'F' or 'O'.*);
	//@ requires(*The phone numbers and seat numbers of the passengers in `details` are distinct.*);
	//@ ensures(*The method returns an integer representing the number of passengers who are strictly more than 60 years old.*);
    public int countSeniors(String[] details) {
        int count = 0;
        for (String detail : details) {
            if (((detail.charAt(11) - '0' == 6) && (detail.charAt(12) - '0' > 0))
                    || (detail.charAt(11) - '0' > 6)) {
                count++;
            }
        }
        return count;
    }
}