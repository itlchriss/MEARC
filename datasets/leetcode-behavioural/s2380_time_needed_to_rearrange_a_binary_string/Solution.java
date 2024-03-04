package g2301_2400.s2380_time_needed_to_rearrange_a_binary_string;

// #Medium #String #Dynamic_Programming #Simulation
// #2022_08_23_Time_3_ms_(70.00%)_Space_41.8_MB_(70.00%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is between 1 and 1000.*);
//@ ensures(*Each character in `s` is either '0' or '1'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the number of seconds needed to complete the process.*);
//@ ensures(*All occurrences of "01" in `s` have been replaced with "10".*);
//@ ensures(*There are no occurrences of "01" in the final string.*);
//@ ensures(*The final string is the result of applying the replacement process repeatedly until no occurrences of "01" exist.*);
    public int secondsToRemoveOccurrences(String s) {
        int lastOne = -1;
        int result = 0;
        int prevResult = 0;
        int curResult = 0;
        int countOne = 0;
        int countZero = 0;
        int diff;
        int pTarget;
        int pWait;
        int cTarget;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                ++countZero;
                continue;
            }
            ++countOne;
            diff = i - lastOne - 1;
            prevResult = curResult;
            cTarget = countOne - 1;
            pTarget = cTarget - 1;
            pWait = prevResult - (lastOne - pTarget);
            if (diff > pWait) {
                curResult = countZero;
            } else {
                curResult = countZero == 0 ? 0 : pWait - diff + 1 + countZero;
            }
            result = curResult;
            lastOne = i;
        }
        return result;
    }
}