package g1701_1800.s1736_latest_time_by_replacing_hidden_digits;

// #Easy #String #Greedy #2022_04_29_Time_3_ms_(35.96%)_Space_42.5_MB_(22.81%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the latest valid time you can get from_ `time` _by replacing the hidden_ _digits_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public String maximumTime(String time) {
        StringBuilder sb = new StringBuilder();
        String[] strs = time.split(":");
        String hour = strs[0];
        String min = strs[1];
        if (hour.charAt(0) == '?') {
            if (hour.charAt(1) == '?') {
                sb.append("23");
            } else if (hour.charAt(1) > '3') {
                sb.append("1");
                sb.append(hour.charAt(1));
            } else {
                sb.append("2");
                sb.append(hour.charAt(1));
            }
        } else if (hour.charAt(0) == '0' || hour.charAt(0) == '1') {
            if (hour.charAt(1) == '?') {
                sb.append(hour.charAt(0));
                sb.append("9");
            } else {
                sb.append(hour);
            }
        } else if (hour.charAt(0) == '2') {
            if (hour.charAt(1) == '?') {
                sb.append("23");
            } else {
                sb.append(hour);
            }
        }
        sb.append(":");
        if (min.charAt(0) == '?') {
            if (min.charAt(1) == '?') {
                sb.append("59");
            } else {
                sb.append("5");
                sb.append(min.charAt(1));
            }
            return sb.toString();
        }
        sb.append(min.charAt(0));
        if (min.charAt(1) == '?') {
            sb.append("9");
        } else {
            sb.append(min.charAt(1));
        }
        return sb.toString();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
