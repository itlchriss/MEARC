package g1101_1200.s1154_day_of_the_year;

// #Easy #String #Math #2023_06_01_Time_15_ms_(31.55%)_Space_44.4_MB_(22.53%)

public class Solution {
	//@ requires(*The input string `date` must be in the format "YYYY-MM-DD".*);
	//@ requires(*The length of the input string `date` must be 10.*);
	//@ requires(*The characters at indices 4 and 7 of the input string `date` must be '-'.*);
	//@ requires(*All other characters in the input string `date` must be digits.*);
	//@ requires(*The input string `date` must represent a calendar date between Jan 1st, 1900 and Dec 31st, 2019.*);
	//@ ensures(*The method should return an integer representing the day number of the year for the given input date.*);
    public int dayOfYear(String date) {
        int[] monthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] dateArr = date.split("-");
        int year = Integer.parseInt(dateArr[0]);
        int month = Integer.parseInt(dateArr[1]);
        int day = Integer.parseInt(dateArr[2]);
        int dayCount = 0;
        boolean leapYear = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
        for (int i = 1; i < month; i++) {
            dayCount += monthDays[i];
        }
        dayCount += day;
        if (leapYear && month > 2) {
            dayCount++;
        }
        return dayCount;
    }
}