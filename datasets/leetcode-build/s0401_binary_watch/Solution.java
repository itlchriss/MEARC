package g0401_0500.s0401_binary_watch;

// #Easy #Bit_Manipulation #Backtracking #2022_07_15_Time_1_ms_(99.26%)_Space_43.1_MB_(42.54%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input `turnedOn` must be an integer between 0 and 10 (inclusive).*);
	//@ ensures(*The method returns a List of Strings representing all possible times the watch could represent.*);
	//@ ensures(*The returned List may be empty if there are no valid times for the given `turnedOn` value.*);
	//@ ensures(*Each time in the returned List must be in the format "h:mm" where h is the hour (0-11) and mm is the minute (00-59).*);
	//@ ensures(*The hour in each time must not contain a leading zero.*);
	//@ ensures(*The minute in each time must consist of two digits and may contain a leading zero.*);
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> times = new ArrayList<>();
        for (int hour = 0; hour <= 11; hour++) {
            for (int minutes = 0; minutes <= 59; minutes++) {
                readBinaryWatchHelper(turnedOn, times, hour, minutes);
            }
        }
        return times;
    }

    private void readBinaryWatchHelper(
            int turnedOn, List<String> selectedTimes, int hour, int minutes) {
        if (isValidTime(turnedOn, hour, minutes)) {
            selectedTimes.add(getTimeString(hour, minutes));
        }
    }

    private String getTimeString(int hour, int minutes) {
        StringBuilder time = new StringBuilder();
        time.append(hour);
        time.append(':');
        if (minutes < 10) {
            time.append('0');
        }
        time.append(minutes);
        return time.toString();
    }

    private boolean isValidTime(int turnedOn, int hour, int minutes) {
        int counter = 0;
        while (hour != 0) {
            if ((hour & 1) == 1) {
                counter++;
            }
            hour >>>= 1;
        }
        if (counter > turnedOn) {
            return false;
        }
        while (minutes != 0) {
            if ((minutes & 1) == 1) {
                counter++;
            }
            minutes >>>= 1;
        }
        return counter == turnedOn;
    }
}